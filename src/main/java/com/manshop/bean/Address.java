package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_address")
public class Address {
    @Id
    private Integer id;
    @ColDefine(notNull = true)
    private Integer uid;
    @One(field = "uid", key = "id")
    private User user;
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String consignee;
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String addphone;
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String address;
    @ColDefine(type = ColType.BOOLEAN)
    @Default(value = "false")
    private boolean isDefault;

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddphone() {
        return addphone;
    }

    public void setAddphone(String addphone) {
        this.addphone = addphone;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
