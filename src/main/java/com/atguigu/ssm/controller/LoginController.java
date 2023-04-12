package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.user;
import com.atguigu.ssm.service.CourseService;
import com.atguigu.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private CourseService courseService;
    @RequestMapping("/toLoginPage")
    public String toLoginPage()
    {
        //跳转至登录页面
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, Model model, HttpServletRequest request, user user)
    {
        String username=user.getUsername();
        String password=user.getPassword();
        System.out.println("用户账号为："+username+"用户密码为："+password);
        user dbuser=courseService.getUserByName(username);
        if(null==dbuser){
            model.addAttribute("noUser",username);
            return "index";
        }
        System.out.println("数据库内用户账号为："+dbuser.getUsername()+"用户密码为："+dbuser.getPassword());
        if(password.equals(dbuser.getPassword())){
            request.getSession().setAttribute("user",username);
            model.addAttribute("loginUser",username);
            session.setAttribute("username", username);
            return "redirect:/course";
        }
        else
        {
            model.addAttribute("errorMsg", "ERROR");
        }
        return "index";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void loginwait()
    {
    }
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request)
    {
        //销毁session对象
        request.getSession().invalidate();
        return "delete_success";
    }
    @RequestMapping(value = "/register" ,method = {RequestMethod.POST})
    public String register(user user,Model model)
    {
        List<String> list=courseService.getAllUser();
        if(!list.contains(user.getUsername())){
            courseService.InsertNewUser(user.getUsername(),user.getPassword());
        }
       model.addAttribute("exitUser",user.getUsername());

        return "redirect:/register";
    }
    @RequestMapping(value = "/register" ,method = {RequestMethod.GET})
    public void registerget(Model model)
    {List<String> list=courseService.getAllUser();
model.addAttribute("list",list);
    }
}
