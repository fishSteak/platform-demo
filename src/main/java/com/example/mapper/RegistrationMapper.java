package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.Registration;

import java.util.List;

public interface RegistrationMapper extends BaseMapper<Registration> {

// /*   *//**
//     * 页数0开始pageSum
//     *
//     * @param activityId
//     * @param username
//     * @param pageSum
//     * @param pageSize
//     * @return
//     */
//    List<List<?>> getActivityUsersByAId(Long activityId, String username, Integer pageSum, Integer pageSize);

    List<List<?>> getRegistrationAndUserByAId(Long activityId, String username, Integer pageSum, Integer pageSize);
}
