package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.CourseMapper;
import com.atguigu.ssm.pojo.c_course;
import com.atguigu.ssm.pojo.s_school;
import com.atguigu.ssm.pojo.user;
import com.atguigu.ssm.service.CourseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<c_course> showAll() {
        return courseMapper.showAll();
    }


//    void Insert(@Param("name")String name, @Param("hours")Integer hours, @Param("schoolName")String schoolName);
@Override
public  void Insert(@Param("name")String name, @Param("hours")Integer hours, @Param("schoolName")String schoolName) {
    courseMapper.Insert(name,hours,schoolName);
}

    @Override
    public c_course getCourseByName(String name) {
    c_course list= courseMapper.getCourseByName(name);
      return courseMapper.getCourseByName(name);
    }

    @Override
    public Integer getIdByName(String name) {
        return courseMapper.getIdByName(name);
    }

    @Override
    public void InsertNewUser(String username, String password) {
        //注册新用户
        courseMapper.InsertNewUser(username,password);
    }

    @Override
    public List<String> getAllUser() {
        //获得所有用户名
        return courseMapper.getAllUser();
    }

    @Override
    public user getUserByName(String username) {
        return courseMapper.getUserByName(username);
    }

    @Override
    public List<s_school> getAllSchool() {
        //得到所有院系
        return courseMapper.getAllSchool();
    }

    @Override
    public void InsertCourse(String name, Integer hours, Integer sid,String pid) {
        courseMapper.InsertCourse(name,hours,sid,pid);
    }

    @Override
    public void deleteByName(String name) {
        courseMapper.deleteByName(name);
    }

    @Override
    public String checkDeptByName(String schoolName) {
        return courseMapper.checkDeptByName(schoolName);
    }

    @Override
    public c_course getCourseById(Integer id) {
        return courseMapper.getCourseById(id);
    }

    @Override
    public void InsertNewDept(String newDept) {
        courseMapper.InsertNewDept(newDept);
    }

    @Override
    public void updatePicById(Integer id, String pid) {
        //更新图片路径
         courseMapper.updatePicById(id,pid);
    }

    @Override
    public void deleteDeptById(Integer id) {
        courseMapper.deleteDeptById(id);
    }

    @Override
    public void updateById(Integer id, String name, Integer hours, Integer sid) {
        courseMapper.updateById(id,name,hours,sid);

    }

    @Override
    public String CheckDeptById(Integer id) {
        return courseMapper.CheckDeptById(id);
    }
}
