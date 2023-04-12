package com.atguigu.ssm.pojo;
/*
* changing Time
* 2023/4/7
* rebuilding...
*
*
* */
public class c_course {
    public c_course() {
    }
    private Integer id;
    private String name;
    private Integer hours;
    private Integer school;
private s_school schools;
private String pid;

    @Override
    public String toString() {
        return "c_course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hours=" + hours +
                ", school=" + school +
                ", schools=" + schools +
                ", pid='" + pid + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public c_course(Integer id, String name, Integer hours, Integer school, s_school schools, String pid) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.school = school;
        this.schools = schools;
        this.pid = pid;
    }

    public c_course(Integer id, String name, Integer hours, Integer school, s_school schools) {
        this.id = id;
        this.name = name;
        this.hours = hours;
        this.school = school;
        this.schools = schools;
    }

    public s_school getSchools() {
        return schools;
    }

    public void setSchools(s_school schools) {
        this.schools = schools;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

}
