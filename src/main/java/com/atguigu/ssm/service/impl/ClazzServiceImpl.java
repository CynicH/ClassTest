package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.ClazzMapper;
import com.atguigu.ssm.mapper.CourseMapper;
import com.atguigu.ssm.pojo.c_course;
import com.atguigu.ssm.pojo.clazz;
import com.atguigu.ssm.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<clazz> getAll() {
        return clazzMapper.getAll();
    }
}
