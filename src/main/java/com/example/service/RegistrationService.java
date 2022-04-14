package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Registration;
import com.example.entity.User;
import com.example.entity.dto.RegistrationDTO;
import com.example.entity.vo.PageVO;
import com.example.mapper.RegistrationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationService extends ServiceImpl<RegistrationMapper, Registration> {

    @Resource
    private RegistrationMapper registrationMapper;

    public PageVO getRegistrationDTOs(Long activityId, String username, Integer pageSum, Integer pageSize) {
        /*if (pageSum < 1) return null;
        List<List<?>> lists = registrationMapper.getActivityUsersByAId(activityId, username, pageSum - 1, pageSize);
        List<User> userList = (List<User>) lists.get(0);
        Integer total = Integer.valueOf(lists.get(1).get(0).toString());
        return new PageVO<User>(pageSize, pageSize, total, userList);*/
        if ((pageSum < 1)) return null;
        List<List<?>> lists = registrationMapper.getRegistrationAndUserByAId(activityId, username, pageSum - 1, pageSize);
        List<RegistrationDTO> dtoList = (List<RegistrationDTO>) lists.get(0);
        Integer total = Integer.valueOf(lists.get(1).get(0).toString());
        return new PageVO<>(pageSize, pageSize, total, dtoList);
    }
}
