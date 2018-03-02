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
    private String showdata;

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

    public String getShowdata() {
        return showdata;
    }

    public void setShowdata(String showdata) {
        this.showdata = showdata;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", picture='" + picture + '\'' +
                ", showdata='" + showdata + '\'' +
                '}';
    }
}
