package com.atguigu.ssm.pojo;

public class clazz {
    private String name;
    private Integer hours;
    private String school;
private String pid;

    @Override
    public String toString() {
        return "clazz{" +
                "name='" + name + '\'' +
                ", hours=" + hours +
                ", school='" + school + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public clazz(String name, Integer hours, String school, String pid) {
        this.name = name;
        this.hours = hours;
        this.school = school;
        this.pid = pid;
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

    public String getschool() {
        return school;
    }

    public void setschool(String school) {
        this.school = school;
    }

    public clazz(String name, Integer hours, String school) {
        this.name = name;
        this.hours = hours;
        this.school = school;
    }

    public clazz() {
    }
}
