package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.c_course;
import com.atguigu.ssm.pojo.clazz;
import com.atguigu.ssm.service.ClazzService;
import com.atguigu.ssm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @RequestMapping(value = "/clazz",method = RequestMethod.GET)
    public String getAll(Model model){
        //查询所有课程信息
        List<clazz> list=clazzService.getAll();
        //将课程信息在请求域中共享
        model.addAttribute("list",list);
        //跳转到course_list.html
        return "clazz_list";
    }
}
