package com.atguigu.ssm.pojo;

import java.util.List;

public class s_school {
    public s_school() {

    }
    private Integer sid;
    private String schoolName;
    private List<c_course> courses ;

    @Override
    public String toString() {
        return "s_school{" +
                "sid=" + sid +
                ", schoolName='" + schoolName + '\'' +
                ", courses=" + courses+
                '}';
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<c_course> getCourses() {
        return courses;
    }

    public void setCourses(List<c_course> courses) {
        this.courses = courses;
    }

    public s_school(Integer sid, String schoolName, List<c_course> courses) {
        this.sid = sid;
        this.schoolName = schoolName;
        this.courses = courses;
    }
}
