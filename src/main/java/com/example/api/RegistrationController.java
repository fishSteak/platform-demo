package com.example.api;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.Activity;
import com.example.entity.Registration;
import com.example.entity.User;
import com.example.entity.dto.PushPlusData;
import com.example.entity.dto.RegistrationDTO;
import com.example.entity.dto.UserDTO;
import com.example.exception.CustomException;
import com.example.service.ActivityService;
import com.example.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    public Result<?> delete(@NotNull @PathVariable Long id) {
        registrationService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> findById(@NotNull @PathVariable Long id) {
        return Result.success(registrationService.getById(id));
    }

    @GetMapping
    public Result<?> findAll() {
        return Result.success(registrationService.list());
    }

    /**
     * 根据活动id来拿到报名用户，分页查找。存在用户名字关键词就查找用户名字的用户;
     *
     * @param activityId 活动id
     * @param username   用户名
     * @param pageNum    数据页
     * @param pageSize   数据页大小
     * @return Result
     */
    @GetMapping("/page")
    public Result<?> findPage(@NotNull @RequestParam(defaultValue = "", value = "activityId") String activityId,
                              @RequestParam(defaultValue = "", value = "username") String username,
                              @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                              @RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize) {
        return Result.success(registrationService.getRegistrationDTOs(Long.valueOf(activityId), username, pageNum, pageSize));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response, @NotNull @RequestParam("activityId") Long activityId) throws IOException {

        List<RegistrationDTO> all = registrationService.getRegistrationByActivityId(activityId);
        Activity activity = activityService.getById(activityId);
        List<Map<String, Object>> list = CollUtil.newArrayList();

//        List<Registration> all = registrationService.list();
        for (RegistrationDTO obj : all) {
            Map<String, Object> row = new LinkedHashMap<>();
            row.put("活动id", obj.getActivityId());
            row.put("活动名", activity.getName());
            row.put("报名序号", obj.getId());
            row.put("通过审核", obj.getState());
            row.put("审核响应", obj.getContext());
            row.put("报名时间", obj.getTime());
            User user = obj.getUser();
            row.put("用户ID", user.getId());
            row.put("用户名", user.getUsername());
            row.put("昵称", user.getNickName());
            row.put("邮箱", user.getEmail());
            row.put("电话", user.getPhone());
            list.add(row);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode(activity.getName() + "用户报名信息表", "UTF-8");
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
        return registrationService.enrollActivity(getUser(), activityId);
    }

    @PostMapping("/pushMsg/{activityId}/{state}")
    public Result<?> pushMsgToPushPlus(@PathVariable @NotBlank Long activityId, @PathVariable @NotEmpty Boolean state,
                                       @NotBlank @RequestBody PushPlusData pushPlusData) {
        List<UserDTO> userDTOList = registrationService.sendMsg(activityId, state, pushPlusData);
        return Result.success("200", userDTOList, "发送消息成功");
    }
}
