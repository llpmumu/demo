package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Table("t_goods")
public class Goods {
    @Id
    private Integer id;
    @ColDefine(notNull = true)
    private Integer uid;
    @One(field = "uid", key = "id")
    private User user;
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String title;
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String detail;
    @ColDefine(type = ColType.VARCHAR, width = 1000)
    private String picture;
    @ColDefine(width = 1)
    private Integer type;
    @ColDefine(width = 1)
    private Integer state;
    @ColDefine(type = ColType.INT)
    private String price;
    @ColDefine(type = ColType.VARCHAR, width = 8)
    private String rental;
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp goodtime;

    public Goods() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRental() {
        return rental;
    }

    public void setRental(String rental) {
        this.rental = rental;
    }

    public Timestamp getGoodtime() {
        return goodtime;
    }

    public void setGoodtime(Timestamp goodtime) {
        this.goodtime = goodtime;
    }
}
