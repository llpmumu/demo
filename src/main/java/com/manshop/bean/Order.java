package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

import java.sql.Time;

@Table("t_order")
public class Order {
    @Id
    private Integer id;
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
    @ColDefine(width = 1)
    private Integer delivery;
    @ColDefine(type = ColType.VARCHAR, width = 20)
    private String trackingnum;
    @ColDefine(type = ColType.INT, width = 1)
    private Integer state;
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String leavetime;
    @ColDefine(type = ColType.VARCHAR, width = 1)
    private Integer type;
    @ColDefine(type = ColType.TIME)
    private Time ordertime;
}
