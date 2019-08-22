package com.atyume.modules.system.vo;

import com.atyume.modules.system.po.Bbs;

import java.util.Date;

public class BbsVO {
    private Long id;
    private String title;
    private String main;
    private Date Ctime;
    private Date Etime;
    private Long userid;
    private String username;
    private String picPath;

    public BbsVO(Long id, String title, String main, Date ctime, Date etime, Long userid, String username,String picPath) {
        this.id = id;
        this.title = title;
        this.main = main;
        Ctime = ctime;
        Etime = etime;
        this.userid = userid;
        this.username = username;
        this.picPath=picPath;
    }

    public BbsVO(Bbs b) {
        this.id = b.getId();
        this.title = b.getTitle();
        this.main = b.getMain();
        Ctime = b.getCtime();
        Etime = b.getEtime();
        this.userid = b.getUserid();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Date getCtime() {
        return Ctime;
    }

    public void setCtime(Date ctime) {
        Ctime = ctime;
    }

    public Date getEtime() {
        return Etime;
    }

    public void setEtime(Date etime) {
        Etime = etime;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
