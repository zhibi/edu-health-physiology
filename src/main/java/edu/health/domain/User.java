package edu.health.domain;

import edu.health.core.base.dto.BaseDomain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 执笔
 * @date 2019/5/22 14:31
 */
@Table(name = "user")
public class User extends BaseDomain {
    /**
     * 用户表id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户名
     */
    private String  username;
    /**
     * 密码
     */
    private String  password;
    /**
     * 真实姓名
     */
    private String  name;
    /**
     * 电子邮箱
     */
    private String  email;
    /**
     * 手机号
     */
    private String  phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
