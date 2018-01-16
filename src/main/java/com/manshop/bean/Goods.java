package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

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
    @ColDefine(type = ColType.VARCHAR, width = 200)
    private String picture;
    @ColDefine(width = 1)
    private Integer type;
    @ColDefine(width = 1)
    private String state;
    @ColDefine(type = ColType.VARCHAR, width = 8)
    private String price;
    @ColDefine(type = ColType.VARCHAR, width = 8)
    private String rental;

}
