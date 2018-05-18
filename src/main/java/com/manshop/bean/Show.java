package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Table;


@Table("t_show")
public class Show {
    @Id(auto = false)
    private Integer id;
    @ColDefine(type = ColType.VARCHAR, width = 30)
    private String title;
    @ColDefine(type = ColType.VARCHAR, width = 15)
    private String province;
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String address;
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String picture;
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String showdate;
    @ColDefine(type = ColType.DATE)
    private java.util.Date showtime;


    public Show() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShowdate() {
        return showdate;
    }

    public void setShowdate(String showdate) {
        this.showdate = showdate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public java.util.Date getShowtime() {
        return showtime;
    }

    public void setShowtime(java.util.Date showtime) {
        this.showtime = showtime;
    }
}