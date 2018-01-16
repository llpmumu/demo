package com.manshop.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User {
    @Id
    private Integer id;
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String phone;
    @ColDefine(type = ColType.VARCHAR, width = 16)
    private String username;
    @ColDefine(type = ColType.VARCHAR, width = 16)
    private String password;
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String head;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
