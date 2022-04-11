package com.example.service;

import com.example.entity.Salary;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.SalaryMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SalaryService extends ServiceImpl<SalaryMapper, Salary> {

    @Resource
    private SalaryMapper salaryMapper;

}
