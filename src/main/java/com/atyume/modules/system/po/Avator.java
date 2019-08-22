package com.atyume.modules.system.po;

import javax.persistence.*;

@Table(name = "sys_avator")
public class Avator {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 图片路径
     */
    @Column(name = "picPath")
    private String picpath;

    /**
     * 本地路径
     */
    @Column(name = "locPath")
    private String locpath;

    /**
     * 是否使用
     */
    private Integer active;

    /**
     * 获取编号
     *
     * @return id - 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return userid - 用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取图片路径
     *
     * @return picPath - 图片路径
     */
    public String getPicpath() {
        return picpath;
    }

    /**
     * 设置图片路径
     *
     * @param picpath 图片路径
     */
    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    /**
     * 获取本地路径
     *
     * @return locPath - 本地路径
     */
    public String getLocpath() {
        return locpath;
    }

    /**
     * 设置本地路径
     *
     * @param locpath 本地路径
     */
    public void setLocpath(String locpath) {
        this.locpath = locpath;
    }

    /**
     * 获取是否使用
     *
     * @return active - 是否使用
     */
    public Integer getActive() {
        return active;
    }

    /**
     * 设置是否使用
     *
     * @param active 是否使用
     */
    public void setActive(Integer active) {
        this.active = active;
    }
}