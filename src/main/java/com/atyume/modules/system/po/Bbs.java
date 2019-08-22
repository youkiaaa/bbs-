package com.atyume.modules.system.po;

import javax.persistence.*;
import java.util.Date;

@Table(name = "sys_bbs")
public class Bbs {

    /**
     * 编号
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 留言标题
     */

    private String title;

    /**
     * 留言内容
     */
    private String main;
    private Date Ctime;
    private Date Etime;

    /**
     * 创建者
     */
    private Long userid;

    public Long getId() {
        return id;
    }

    public Bbs setId(Long id) {
        this.id = id;
        return this;
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

    public String getTitle() {
        return title;
    }

    public Bbs setTitle(String title){
        this.title=title;
        return this;
    }

    public Bbs setName(String title) {
        this.title = title;
        return this;
    }

    public String getMain() {
        return main;
    }

    public Bbs setMain(String main) {
        this.main = main;
        return this;
    }

    public Long getUserid() {
        return userid;
    }

    public Bbs setUserid(Long userid) {
        this.userid = userid;
        return this;
    }
}