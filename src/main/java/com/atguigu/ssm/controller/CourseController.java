package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.c_course;
import com.atguigu.ssm.pojo.clazz;
import com.atguigu.ssm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String getAllCourse(Model model, HttpSession session) {
        //查询所有课程信息
        List<c_course> list = courseService.showAll();
        //将课程信息在请求域中共享
        model.addAttribute("list", list);
        //将集合中的课程名称属性抽离形成一个新的集合
        List<String> courses = CollectionUtils.transform(list, s -> ((c_course) s).getName());
        //获得登录的用户信息
        String name = (String) session.getAttribute("username");
        model.addAttribute("loginUser", name);
        model.addAttribute("courses", courses);
        //跳转到course_list.html
        return "course_list";
    }

//request表示当前要处理的请求对象
@RequestMapping("/course/add")
    public String test(HttpServletRequest request, Model model, MultipartFile photo, HttpSession session) throws IOException {
        //获得输入的username和password
    String name = request.getParameter("name");
    String hour = request.getParameter("hours");
    Integer hours=Integer.parseInt(hour);
    String schoolName = request.getParameter("schoolName");
    c_course course= courseService.getCourseByName(name);

    //上传图片-------------------------------------
    //photo.getOriginalFilename(),获取上传的文件的文件名
    String fileName = photo.getOriginalFilename();
    //如果没有上传图片
    if(null==fileName||StringUtils.isEmpty(fileName)){
        //////插入
        //没有传文件则默认为1.png
        fileName="1.png";
        if (null == course || StringUtils.isEmpty(course.getName())){
            //插入
            courseService.Insert(name,hours,schoolName);
            Integer sid=courseService.getIdByName(schoolName);
            courseService.InsertCourse(name,hours,sid,fileName);
            //查询所有课程信息
            List<c_course> list=courseService.showAll();
            //将课程信息在请求域中共享
            model.addAttribute("list",list);
            model.addAttribute("exitCourse","0");
            return "redirect:/course";
        }else{
            System.out.println(course.getName().toString());
            model.addAttribute("exitCourse","1");
            model.addAttribute("course",name);
            return "redirect:/course";
        }
        //////
    }else
    {
        //获取上传的文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //获取一个永远不重复的文件名
        fileName=uuid+suffixName;
        System.out.println(fileName);
        //获取ServletContext对象
        ServletContext servletContext=session.getServletContext();
        //获取当前工程下photo目录的真实路径
        String photoPath = servletContext.getRealPath("photo");
        //创建photoPath所对应的File对象
        File file=new File(photoPath);
        //判断file所对应目录是否存在
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath=photoPath+File.separator+fileName;
        //上传文件
        photo.transferTo(new File(finalPath));
        //-----------------------------------------------
        if (null == course || StringUtils.isEmpty(course.getName())){
            //插入
            courseService.Insert(name,hours,schoolName);
            Integer sid=courseService.getIdByName(schoolName);
            courseService.InsertCourse(name,hours,sid,fileName);
            //查询所有课程信息
            List<c_course> list=courseService.showAll();
            //将课程信息在请求域中共享
            model.addAttribute("list",list);
            model.addAttribute("exitCourse","0");
            return "redirect:/course";

        }else{
            System.out.println(course.getName().toString());
            model.addAttribute("exitCourse","1");
            model.addAttribute("course",name);
            return "redirect:/course";
        }
    }

}
    @RequestMapping("course/delete/{course:.*}")
    public String delete(HttpServletRequest request, Model model, @PathVariable String course) {
        System.out.println("正在删除" + course);
    c_course deleteCourse=courseService.getCourseByName(course);
    courseService.deleteByName(course);
  Integer deleteSid=deleteCourse.getSchool();
//    List<c_course> list=courseService.showAll();
//    //将集合中的院系Id属性抽离形成一个新的集合
//    List<Integer> deletedsids = CollectionUtils.transform(list, s -> ((c_course) s).getSchool());
//        System.out.println(deletedsids);
//        System.out.println(deletesid);
//    Integer count= Collections.frequency(deletedsids,deletesid);
//    System.out.println(count);
    //当院系的课程均删除时自动删除院系
        return "redirect:/course";
}
//    @RequestMapping("/course/delete/check/")
//    public String deleteDept(){
//        //查询所有课程信息
//        List<c_course> list=courseService.showAll();
//        //将集合中的课程名称属性抽离形成一个新的集合
//        List<String> schools = CollectionUtils.transform(list, s -> ((c_course) s).getSchools().getSchoolName());
//        System.out.println("现有的课程为："+schools);
//    List<s_school> schoolList=courseService.getAllSchool();
//        List<String> allSchool= CollectionUtils.transform(schoolList, s -> ((s_school) s).getSchoolName());
//        System.out.println("数据库中的全部课程为："+allSchool);
////        Iterator<Integer> it = allDept.iterator();
////        while (it.hasNext()) {
////            if (!schoolList.contains(it.next())){
////                courseService.deleteDeptById(it.next());
////            }
////        }
//        //跳转到course_list.html
//        return "redirect:/course";
//    }
//@PathVariable("name") String courseName,
//                               @PathVariable("hours") String courseHours,
//                               @PathVariable("school") String courseSchool,
    @RequestMapping(value = "/course/{id}",method =RequestMethod.POST)
    public String updateCourse(
            @PathVariable("id") String sid , clazz c,MultipartFile photo, HttpSession session) throws IOException {
        System.out.println(c);
        System.out.println(sid);
//得到输入的数据
        String newName=c.getName();
        String newDept=c.getschool();
        Integer newHours=c.getHours();
c_course hasCourse=courseService.getCourseByName(newName);
//查询新输入的院系在DB中的id
String deptId=courseService.checkDeptByName(newDept);
//获取原来的数据
        Integer originalId=Integer.parseInt(sid);
        c_course originalCourse=courseService.getCourseById(originalId);
        Integer originalHours=originalCourse.getHours();
        String originalName=originalCourse.getName();
        Integer originalSid=originalCourse.getSchool();
        String originalPid=originalCourse.getPid();
        ///////////////////////////////////////////////////////////////////////Pictures
        //photo.getOriginalFilename(),获取上传的文件的文件名
        String fileName = photo.getOriginalFilename();
        if(null==fileName||StringUtils.isEmpty(fileName))
        //没有上传文件，上传默认图片
        {
            fileName="1.png";
            courseService.updatePicById(originalId,fileName);
            String newPid=fileName;
            System.out.println("新的pid是"+newPid);
            courseService.updatePicById(originalId,newPid);
        }
        else
        //上传了新图片
        {
            String newPid=fileName;
            System.out.println("新的pid是"+newPid);
            //获取上传的文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //获取uuid
            String uuid = UUID.randomUUID().toString();
            //获取一个永远不重复的文件名
            fileName=uuid+suffixName;
            //更新pid
            courseService.updatePicById(originalId,fileName);
            //获取ServletContext对象
            ServletContext servletContext=session.getServletContext();
            //获取当前工程下photo目录的真实路径
            String photoPath = servletContext.getRealPath("photo");
            //创建photoPath所对应的File对象
            File file=new File(photoPath);
            //判断file所对应目录是否存在
            if(!file.exists()){
                file.mkdir();
            }
            String finalPath=photoPath+File.separator+fileName;
            //上传文件
            photo.transferTo(new File(finalPath));
        }
        ///////////////////////////////////////////////////////////////////////Pictures
        //Check for new Dept
            System.out.println("新的院系在DB中的结果是:"+deptId);
            if(null==deptId)
            //有新的院系，需要插入的情况
            {
                //插入新院系
                courseService.InsertNewDept(newDept);
                String NewDept=courseService.checkDeptByName(newDept);
                Integer newDeptId=Integer.parseInt(NewDept);
                //更新内容
                if(!newHours.equals(originalHours)||!newName.equals(originalName))
{
    courseService.updateById(originalId,newName,newHours,newDeptId);
}
            }
            //没有新的院系产生，更新内容即可
            else {
                if(!newHours.equals(originalHours)||!newName.equals(originalName)||!deptId.equals(originalSid)){
                    Integer newDeptId=Integer.parseInt(deptId);
                    courseService.updateById(originalId,newName,newHours,newDeptId);
                }
            }
        return "redirect:/course";
    }
    @RequestMapping(value = "/course/{name}",method =RequestMethod.GET)
    public String toUpdate(@PathVariable("name") String name,Model model,HttpSession session){
        System.out.println("跳转至名为"+name+"的更新页面");
        c_course course=courseService.getCourseByName(name);
        System.out.println(course);
String dept=courseService.CheckDeptById(course.getSchool());
        System.out.println(dept);
        String username = (String)session.getAttribute("username");
        model.addAttribute("loginUser",username);
        model.addAttribute("course",course);
        model.addAttribute("courseName",course.getName());
        model.addAttribute("courseHours",course.getHours());
        model.addAttribute("courseSchool",course.getSchools().getSchoolName());
        model.addAttribute("deptName",dept);
        List<c_course> courseList=courseService.showAll();
        //将集合中的课程名称属性抽离形成一个新的集合
        List<String> courses = CollectionUtils.transform(courseList, s -> ((c_course) s).getName());
        courses.remove(name);
        //院系名
        List<String> depts = CollectionUtils.transform(courseList, s -> ((c_course) s).getSchools().getSchoolName());
        System.out.println(depts);
        System.out.println(courses);
        //将课程信息在请求域中共享
        model.addAttribute("list",courses);
        model.addAttribute("depts",depts);
        return "course_update";
    }

}


