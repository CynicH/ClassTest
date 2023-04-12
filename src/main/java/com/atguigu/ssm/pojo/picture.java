package com.atguigu.ssm.pojo;

public class picture {
    private Integer id;
    private Integer pid;

    @Override
    public String toString() {
        return "picture{" +
                "id=" + id +
                ", pid=" + pid +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public picture(Integer id, Integer pid) {
        this.id = id;
        this.pid = pid;
    }

    public picture() {
    }
}
