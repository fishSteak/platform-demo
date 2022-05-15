package com.example.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.LogService;
import com.example.service.UserService;
import com.example.utils.SessionContain;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/user")
public class UserController {
    public static final ConcurrentHashMap<String, User> MAP = new ConcurrentHashMap<>();

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;
    @Resource
    private HttpServletRequest request;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }


    /**
     * 登录
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Result<User> login(@NotNull @RequestBody User user, HttpServletRequest request) {
        User res = userService.login(user);
        request.getSession().setAttribute("user", res);
        MAP.put(res.getUsername(), res);

        logService.log(StrUtil.format("用户 {} 登录系统", user.getUsername()));
        return Result.success(res);
    }

    /**
     * 注册
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/register")
    public Result<User> register(@NotNull @RequestBody User user, HttpServletRequest request) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        if (user.getAvatar() == null) {
            user.setAvatar("1650007563001");
        }
        User dbUser = userService.register(user);
        request.getSession().setAttribute("user", user);

        logService.log(StrUtil.format("用户 {} 注册账号成功", user.getUsername()));
        return Result.success(dbUser);
    }

    @GetMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            logService.log(StrUtil.format("用户 {} 退出系统", user.getUsername()));
            request.getSession().removeAttribute("user");
            MAP.remove(user.getUsername());
        }
        return Result.success();
    }

    @GetMapping("/online")
    public Result<Collection<User>> online(HttpServletRequest request) {
        return Result.success(MAP.values());
    }

    @GetMapping("/session")
    public Result<User> session() {
        return Result.success(getUser());
    }

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        logService.log(StrUtil.format("新增用户：{} ", user.getUsername()));
        return Result.success(userService.save(user));
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        logService.log(StrUtil.format("更新用户：{} ", user.getUsername()));
        return Result.success(userService.updateById(user));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        User user = userService.getById(id);
        logService.log(StrUtil.format("删除用户 {} ", user.getUsername()));

        userService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @GetMapping("/detail/{username}")
    public Result<User> findByUsername(@PathVariable String username) {
        return Result.success(userService.getbyUsername(username));
    }

    @GetMapping
    public Result<List<User>> findAll() {
        return Result.success(userService.list(Wrappers.<User>lambdaQuery().ne(User::getUsername, "admin")));
    }

    @GetMapping("/page")
    public Result<IPage<User>> findPage(@RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().ne(User::getUsername, "admin").like(User::getUsername, name).orderByDesc(User::getId);
        return Result.success(userService.page(new Page<>(pageNum, pageSize), wrapper));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<User> all = userService.list();
        for (User user : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("名称", user.getUsername());
            row1.put("手机", user.getPhone());
            row1.put("邮箱", user.getEmail());
            list.add(row1);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

    @GetMapping("/upload/{fileId}")
    public Result<?> upload(@PathVariable String fileId) {
        String basePath = System.getProperty("user.dir") + "/src/main/resources/static/file/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        String file = fileNames.stream().filter(name -> name.contains(fileId)).findAny().orElse("");
        List<List<Object>> lists = ExcelUtil.getReader(basePath + file).read(1);
        List<User> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            User user = new User();
            user.setUsername((String) row.get(0));
            user.setNickName((String) row.get(1));
            user.setEmail((String) row.get(2));
            user.setPhone((String) row.get(3));
            saveList.add(user);
        }
        userService.saveBatch(saveList);
        return Result.success();
    }

    @PostMapping("/getCode")
    public Result<?> getTokenCode() {
        try {
            //这个请求的(图片a标签)等待60S就要刷新，否则失败
            Connection.Response response = Jsoup.connect("https://pushplus.hxtrip.com/login?redirectUrl=/message")
                    .followRedirects(true)
                    .method(Connection.Method.GET)
                    .header("referer", "https://pushplus.hxtrip.com/index?pushToken=642b1182bf374b95a31dea6991749112")
                    .execute();
            Document doc = response.parse();
            for (Element element : doc.select("img")) {
                String src = element.attr("src");
                if (src.contains("mp.weixin.qq.com")) {
                    Elements input = doc.select("input");
                    String code = input.get(0).attr("value");
                    String key = input.get(1).attr("value");
                    String url = "https://pushplus.hxtrip.com/common/wechat/confirmLogin";
                    url += "?code=" + code;
                    url += "&key=" + key;
                    request.getSession().setAttribute("codeUrl", url);
                    return Result.success(src);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error("500", "PushPlus令牌获取错误");
    }

    @PostMapping("/confirmCode")
    @SneakyThrows
    public Result<?> confirmCode() {
        HttpSession session = request.getSession();
        if (!SessionContain.haveAttribute(session, "codeUrl")) return Result.error("500", "没有请求code图片");
        String url = (String) session.getAttribute("codeUrl");

        Connection.Response response1 = Jsoup.connect(url)
                .followRedirects(true)
                .method(Connection.Method.GET)
                .ignoreContentType(true)
                .execute();
        if (response1.hasHeader("set-cookie")) {
            Matcher matcher = Pattern.compile("pushToken=[\\d\\w]{32}").matcher(response1.header("set-cookie"));
            if (matcher.find()) {
                String pushToken = matcher.group();
                Connection.Response response = Jsoup.connect("https://pushplus.hxtrip.com/message")
                        .header("cookie", pushToken)
                        .method(Connection.Method.GET)
                        .execute();
                String pushPlusToken = response.parse().body().getElementById("token").text();
                User user = getUser();
                user.setToken(pushPlusToken);
                userService.updateById(user);
                return Result.success("200", "绑定微信成功");
            }
            return Result.error("500", "获取失败");
        }
        return Result.error("500", "set-cookie不存在,等待扫码关注");
    }
}
