package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.c_course;
import com.atguigu.ssm.pojo.s_school;
import com.atguigu.ssm.pojo.user;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    //查询所有课程信息
    List<c_course> showAll();
    //插入课程信息
    void Insert(@Param("name")String name, @Param("hours")Integer hours, @Param("schoolName")String schoolName);
    //查询课程ByName
    c_course getCourseByName(@Param("name")String name);
    //查询课程ById
    c_course getCourseById(@Param("id")Integer id);
    //查询已有课程名的id
    Integer getIdByName(@Param("name") String name);
    //插入课程
    void InsertCourse(@Param("name")String name, @Param("hours")Integer hours,@Param("sid")Integer sid,@Param("pid")String pid);
    //依照名称删除
    void deleteByName(@Param("name")String name);
    //根据id查询院系
    String CheckDeptById(@Param("id") Integer id);
    //根据id更新课程
    void updateById(@Param("id") Integer id,@Param("name")String name, @Param("hours")Integer hours,@Param("sid")Integer sid);
    //根据名称查找院系id
    String checkDeptByName(@Param("schoolName")String schoolName);
//插入新院系
    void InsertNewDept(@Param("newDept") String newDept);
//删除院系ById
    void deleteDeptById(@Param("id") Integer id);
//更新图片路径
    void updatePicById(@Param("id") Integer id, @Param("pid") String pid);
    //得到所有院系
    List<s_school> getAllSchool();
//按照账号查找用户
    user getUserByName(@Param("username") String username);
//获得所有用户名
    List<String> getAllUser();
//注册新用户
    void InsertNewUser(@Param("username") String username,@Param("password")  String password);
}
