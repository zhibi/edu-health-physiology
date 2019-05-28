package edu.health.domain;

import edu.health.core.base.dto.BaseDomain;

import javax.persistence.*;
import java.util.Date;

/**
 * 健康数据
 *
 * @author 执笔
 * @date 2019/5/22 15:45
 */
@Table(name = "info")
public class Info extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userid;
    /**
     * 添加时间
     */
    private Date    addtime;
    /**
     * 体温
     */
    private Integer tiwen;
    /**
     * 脉搏
     */
    private Integer maibo;
    /**
     * 血糖
     */
    private Integer xuetang;

    @Transient
    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getTiwen() {
        return tiwen;
    }

    public void setTiwen(Integer tiwen) {
        this.tiwen = tiwen;
    }

    public Integer getMaibo() {
        return maibo;
    }

    public void setMaibo(Integer maibo) {
        this.maibo = maibo;
    }

    public Integer getXuetang() {
        return xuetang;
    }

    public void setXuetang(Integer xuetang) {
        this.xuetang = xuetang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
