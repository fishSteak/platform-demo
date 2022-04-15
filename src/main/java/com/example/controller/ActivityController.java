package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Activity;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Resource
    private ActivityService activityService;
    @Resource
    private HttpServletRequest request;
    @Autowired
    HttpServletRequest httpServletRequest;

    public User getUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            throw new CustomException("-1", "请登录");
        }
        return user;
    }

    @PostMapping
    public Result<?> save(@RequestBody Activity activity) {
        return Result.success(activityService.save(activity));
    }

    @PutMapping
    public Result<?> update(@RequestBody Activity activity) {
        return Result.success(activityService.updateById(activity));
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        activityService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@PathVariable Long id) {
        return Result.success(activityService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(activityService.list());
    }

    @GetMapping("/page")
    public Result<?> findPage(@RequestParam(required = false, defaultValue = "") String name,
                              @RequestParam(required = false, defaultValue = "") Boolean state,
                              @NotNull @RequestParam(defaultValue = "1") Integer pageNum,
                              @NotNull @RequestParam(defaultValue = "10") Integer pageSize) {
        LambdaQueryWrapper<Activity> query = Wrappers.<Activity>lambdaQuery().orderByDesc(Activity::getId);
        if (StrUtil.isNotBlank(name)) {
            query.like(Activity::getName, name);
        }
        if (state != null) {
            query.eq(Activity::getState, state);
        }
        query.orderByDesc(Activity::getId);
        return Result.success(activityService.page(new Page<>(pageNum, pageSize), query));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        List<Map<String, Object>> list = CollUtil.newArrayList();

        List<Activity> all = activityService.list();
        for (Activity obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("内容", obj.getContent());
            row.put("结束时间", obj.getEndtime());
            row.put("ID", obj.getId());
            row.put("图片", obj.getImg());
            row.put("活动名", obj.getName());
            row.put("人数总量", obj.getNumber());
            row.put("开始时间", obj.getStarttime());
            row.put("状态 0关闭 1开启", obj.getState());
            row.put("举办人", obj.getUsername());
            row.put("乐观锁", obj.getVersion());

            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("活动信息", "UTF-8");
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
        List<Activity> saveList = new ArrayList<>();
        for (List<Object> row : lists) {
            Activity obj = new Activity();
            obj.setContent((String) row.get(1));
            obj.setEndtime(DateUtil.parseDateTime((String) row.get(2)));
            obj.setImg((String) row.get(3));
            obj.setName((String) row.get(4));
            obj.setNumber(Integer.valueOf((String) row.get(5)));
            obj.setStarttime(DateUtil.parseDateTime((String) row.get(6)));
//            obj.setState((String) row.get(7));
            obj.setUsername((String) row.get(8));
            obj.setVersion(Integer.valueOf((String) row.get(9)));
            saveList.add(obj);
        }
        activityService.saveBatch(saveList);
        return Result.success();
    }


}
