package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_address")
public class Address {
    @Id
    private Integer id;
    @ColDefine(notNull=true)
    private Integer uid;
    @One(field = "uid", key = "id")
    private User user;
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String consignee;
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String address;
    @ColDefine(type = ColType.VARCHAR, width = 11)
    private String addphone;
    private Boolean isDefault;

}
