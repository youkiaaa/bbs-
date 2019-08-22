package com.atyume.modules.system.po;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.Date;
import javax.persistence.*;
@Table(name = "sys_money_detail")
public class MoneyLog {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 出钱
     */
    private Long posuser;

    /**
     * 收钱
     */
    private Long quauser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 金额
     */
    private Double money;

    /**
     * 类型
     */
    private String type;

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
     * 获取出钱
     *
     * @return posuser - 出钱
     */
    public Long getPosuser() {
        return posuser;
    }

    /**
     * 设置出钱
     *
     * @param posuser 出钱
     */
    public void setPosuser(Long posuser) {
        this.posuser = posuser;
    }

    /**
     * 获取收钱
     *
     * @return quauser - 收钱
     */
    public Long getQuauser() {
        return quauser;
    }

    /**
     * 设置收钱
     *
     * @param quauser 收钱
     */
    public void setQuauser(Long quauser) {
        this.quauser = quauser;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }
}