package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Registration;
import com.example.service.ActivityService;
import com.example.service.RegistrationService;
import com.example.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import com.example.exception.CustomException;
import cn.hutool.core.util.StrUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Resource
    private RegistrationService registrationService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private ActivityService activityService;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody Registration registration) {
        return Result.success(registrationService.save(registration));
    }

    @PutMapping
    public Result<?> update(@RequestBody Registration registration) {
        return Result.success(registrationService.updateById(registration));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        registrationService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(registrationService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(registrationService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String activityName,
                              @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Registration> query = Wrappers.<Registration>lambdaQuery().orderByDesc(Registration::getId);
        /*if (StrUtil.isNotBlank(activityName)) {
            //活动名字来搜索响应的数据
            query.like(Registration::getName, name);
        }*/
        return Result.success(registrationService.page(new Page<>(pageNum, pageSize), query));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Registration> all = registrationService.list();
        for (Registration obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("活动id", obj.getActivityId());
            row.put("响应内容", obj.getContext());
            row.put("流水号", obj.getId());
            row.put("状态0不通过 1通过", obj.getState());
            row.put("申请时间(用户申请参加活动的时间)", obj.getTime());
            row.put("用户id", obj.getUserId());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("活动报名信息", "UTF-8");
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
        List<Registration> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Registration obj = new Registration();
            obj.setActivityId(Long.valueOf((String) row.get(1)));
            obj.setContext((String) row.get(2));
            obj.setState(Boolean.valueOf((String) row.get(3)));
            obj.setTime(DateUtil.parseDateTime((String) row.get(4)));
            obj.setUserId(Long.valueOf((String) row.get(5)));

            saveList.add(obj);
        }
        registrationService.saveBatch(saveList);
        return Result.success();
    }

}
