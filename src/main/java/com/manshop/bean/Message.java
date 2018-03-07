package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;
import org.nutz.dao.entity.annotation.Table;

import java.sql.Timestamp;

@Table("t_msg")
public class Message {
    @Id
    private Integer id;
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String msg;
    @ColDefine(notNull = true)
    private Integer sender;
    @One(field = "sender", key = "id")
    private User suser;
    @ColDefine(notNull = true)
    private Integer receiver;
    @One(field = "receiver", key = "id")
    private User ruser;
    @ColDefine(notNull = true, type = ColType.INT, width = 1)
    @Default(value = "0")
    private Integer type;
    @ColDefine(type = ColType.TIMESTAMP)
    private Timestamp msgtime;

    public Message() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public User getSuser() {
        return suser;
    }

    public void setSuser(User suser) {
        this.suser = suser;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public User getRuser() {
        return ruser;
    }

    public void setRuser(User ruser) {
        this.ruser = ruser;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getMsgtime() {
        return msgtime;
    }

    public void setMsgtime(Timestamp msgtime) {
        this.msgtime = msgtime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", sender=" + sender +
                ", suser=" + suser +
                ", receiver=" + receiver +
                ", ruser=" + ruser +
                ", type=" + type +
                '}';
    }
}
