package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Activity;
import com.example.entity.Registration;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.service.ActivityService;
import com.example.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ActivityService activityService;
    private final HttpServletRequest request;

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

    /**
     * 根据活动id来拿到报名用户，分页查找。存在用户名字关键词就查找用户名字的用户;
     * @param activityId
     * @param username
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result<?> findPage(@NotNull @RequestParam(defaultValue = "", value = "activityId") String activityId,
                              @RequestParam(defaultValue = "", value = "name") String username,
                              @RequestParam(required = false,defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(required = false,defaultValue = "10", value = "pageSize") Integer pageSize) {
        LambdaQueryWrapper<Registration> query = Wrappers.<Registration>lambdaQuery().orderByDesc(Registration::getId);
        /*if (StrUtil.isNotBlank(activityName)) {
            //活动名字来搜索响应的数据
            query.like(Registration::getName, name);
        }*/
        if (username!=null){

        }
        //根据活动id查找对应的user-page封装
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

    /**
     * 报名接口
     *
     * @param activityId 活动ID
     * @return Result
     */
    @PostMapping("/enroll/{activityId}")
    public Result<?> enroll(@NotNull @PathVariable Long activityId) {
        User user = (User) request.getSession().getAttribute("user");
        Activity activity = activityService.getById(activityId);
        if (activity == null) return Result.error("500", "找不到对应活动");
        //重复报名活动
        LambdaQueryWrapper<Registration> queryWrapper = new QueryWrapper<Registration>().lambda();
        queryWrapper.eq(Registration::getActivityId, activityId)
                .eq(Registration::getUserId, user.getId());
        Registration one = registrationService.getOne(queryWrapper);
        if (one != null) return Result.success("已经报名了该活动");
        //
        Registration registration = new Registration();
        registration.setUserId(user.getId());
        registration.setActivityId(activity.getId());
        boolean save = registrationService.save(registration);
        String msg = save ? "报名成功" : "报名失败";
        return Result.success(msg);
    }
}
