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
                //?????????????????????
                userDTO.setSendState(false);
                userDTO.setSendStateMsg("????????????????????????????????????");
                userDTOArrayList.add(userDTO);
                continue;
            }
            //????????????????????????
            pushPlusData.setToken(user.getToken());
            Result<?> msg = MsgTools.sendPushPlusMsg(pushPlusData);
            assert msg != null;
            if (msg.getCode().equals("200")) {
                //????????????,????????????
                userDTO.setSendState(true);
                userDTO.setSendStateMsg("????????????");
            } else {
                userDTO.setSendState(false);
                userDTO.setSendStateMsg("??????????????????????????????");
            }
            userDTOArrayList.add(userDTO);
        }
        return userDTOArrayList;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Result<?> enrollActivity(User user, Long activityId) {
        Activity activity = activityService.getById(activityId);
        if (activity == null) return Result.error("500", "?????????????????????");
        //???????????????
        if (!activity.getState()) return Result.error("500", "???????????????");
        //??????????????????
        LambdaQueryWrapper<Registration> queryWrapper = new QueryWrapper<Registration>().lambda();
        queryWrapper.eq(Registration::getActivityId, activityId)
                .eq(Registration::getUserId, user.getId());
        Registration one = this.getOne(queryWrapper);
        if (one != null) return Result.success("????????????????????????");
        //
        Registration registration = new Registration();
        registration.setUserId(user.getId());
        registration.setActivityId(activity.getId());
        boolean save = this.save(registration);
        if (save) {
            //????????????????????????
            Activity activity1 = activityService.getById(activityId);
            if (activity1.getSurplus() <= 0) return Result.success("???????????????,????????????");
            activity1.setSurplus(activity1.getSurplus() - 1);
            boolean b = activityService.save(activity1);
            if (!b) {
                log.error("?????????????????????????????????,??????id{}", activityId);
                return Result.success("?????????????????????????????????");
            }
        }
        String msg = save ? "????????????" : "????????????";
        return Result.success(msg);
    }

    public List<RegistrationDTO> getRegistrationByActivityId(Long activityId) {
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(activityId, null, null, null);
        return (List<RegistrationDTO>) lists.get(0);
    }
}
