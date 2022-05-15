package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.Activity;
import com.example.entity.Registration;
import com.example.entity.User;
import com.example.entity.dto.PushPlusData;
import com.example.entity.dto.RegistrationDTO;
import com.example.entity.dto.UserDTO;
import com.example.entity.vo.PageVO;
import com.example.mapper.RegistrationMapper;
import com.example.mapper.UserMapper;
import com.example.utils.MsgTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class RegistrationService extends ServiceImpl<RegistrationMapper, Registration> {

    @Resource
    private RegistrationMapper registrationMapper;
    private final ActivityService activityService;
    private final UserMapper userMapper;

    public PageVO getRegistrationDTOs(Long activityId, String username, Integer pageSum, Integer pageSize) {
        if ((pageSum < 1)) return null;
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(activityId, username, pageSum - 1, pageSize);
        List<RegistrationDTO> dtoList = (List<RegistrationDTO>) lists.get(0);
        int total = Integer.parseInt(lists.get(1).get(0).toString());
        return new PageVO<>(pageSize, pageSize, total, dtoList);
    }

    public List<UserDTO> sendMsg(Long activityId, Boolean state, PushPlusData pushPlusData) {
        ArrayList<UserDTO> userDTOArrayList = new ArrayList<>();

        List<User> users = userMapper.getUsersByRegistration(activityId, state);

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            if (user.getToken() == null || user.getToken().equals("")) {
                //没有绑定微信号
                userDTO.setSendState(false);
                userDTO.setSendStateMsg("没有绑定微信号，令牌为空");
                userDTOArrayList.add(userDTO);
                continue;
            }
            //绑定了，发送消息
            pushPlusData.setToken(user.getToken());
            Result<?> msg = MsgTools.sendPushPlusMsg(pushPlusData);
            assert msg != null;
            if (msg.getCode().equals("200")) {
                //发送成功,封装消息
                userDTO.setSendState(true);
                userDTO.setSendStateMsg("发送成功");
            } else {
                userDTO.setSendState(false);
                userDTO.setSendStateMsg("发送失败，服务端错误");
            }
            userDTOArrayList.add(userDTO);
        }
        return userDTOArrayList;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<?> enrollActivity(User user, Long activityId) {
        Activity activity = activityService.getById(activityId);
        if (activity == null) return Result.error("500", "找不到对应活动");
        //活动已关闭
        if (!activity.getState()) return Result.error("500", "活动已关闭");
        //重复报名活动
        LambdaQueryWrapper<Registration> queryWrapper = new QueryWrapper<Registration>().lambda();
        queryWrapper.eq(Registration::getActivityId, activityId)
                .eq(Registration::getUserId, user.getId());
        Registration one = this.getOne(queryWrapper);
        if (one != null) return Result.success("已经报名了该活动");
        //
        Registration registration = new Registration();
        registration.setUserId(user.getId());
        registration.setActivityId(activity.getId());
        boolean save = this.save(registration);
        if (save) {
            //成功报名人数减一
            Activity activity1 = activityService.getById(activityId);
            if (activity1.getSurplus() <= 0) return Result.success("没有名额了,报名失败");
            activity1.setSurplus(activity1.getSurplus() - 1);
            boolean b = activityService.save(activity1);
            if (!b) {
                log.error("剩余人数不符，发生错误,活动id{}", activityId);
                return Result.success("剩余人数不符，发生错误");
            }
        }
        String msg = save ? "报名成功" : "报名失败";
        return Result.success(msg);
    }

    public List<RegistrationDTO> getRegistrationByActivityId(Long activityId) {
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(activityId, null, null, null);
        return (List<RegistrationDTO>) lists.get(0);
    }
}
