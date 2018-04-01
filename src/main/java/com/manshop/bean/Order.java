package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Table("t_order")
public class Order {
    @Name
    @Prev(els = @EL("uuid(32)"))
    private String id;
    @ColDefine(notNull = true)
    private Integer gid;
    @One(field = "gid", key = "id")
    private Goods good;
    @ColDefine(notNull = true)
    private Integer suid;
    @One(field = "suid", key = "id")
    private User suser;
    @ColDefine(notNull = true)
    private Integer buid;
    @One(field = "buid", key = "id")
    private User buser;
    @ColDefine(notNull = true)
    private Integer aid;
    @One(field = "aid", key = "id")
    private Address address;
    @ColDefine(width = 1)
    private Integer delivery;
    @ColDefine(type = ColType.VARCHAR, width = 25)
    private String trackingnum;
    @ColDefine(type = ColType.INT, width = 1)
    private Integer state;
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String leavetime;
    @ColDefine(type = ColType.VARCHAR, width = 1)
    private Integer type;
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp ordertime;

    public Order() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }

    public Integer getSuid() {
        return suid;
    }

    public void setSuid(Integer suid) {
        this.suid = suid;
    }

    public User getSuser() {
        return suser;
    }

    public void setSuser(User suser) {
        this.suser = suser;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public User getBuser() {
        return buser;
    }

    public void setBuser(User buser) {
        this.buser = buser;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public String getTrackingnum() {
        return trackingnum;
    }

    public void setTrackingnum(String trackingnum) {
        this.trackingnum = trackingnum;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Timestamp ordertime) {
        this.ordertime = ordertime;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getAid() {

        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", gid=" + gid +
                ", good=" + good +
                ", suid=" + suid +
                ", suser=" + suser +
                ", buid=" + buid +
                ", buser=" + buser +
                ", aid=" + aid +
                ", address=" + address +
                ", delivery=" + delivery +
                ", trackingnum='" + trackingnum + '\'' +
                ", state=" + state +
                ", leavetime='" + leavetime + '\'' +
                ", type=" + type +
                ", ordertime=" + ordertime +
                '}';
    }
}
