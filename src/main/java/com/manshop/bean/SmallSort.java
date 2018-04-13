package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_smallsort")
public class SmallSort {
    @Id
    private Integer id;
    @ColDefine(type = ColType.INT)
    private Integer sortid;
    @One(field = "sortid", key = "id")
    private Sort sort;
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String sortName;

    public SmallSort() {
    }

    public SmallSort(Integer id, Integer sortid, Sort sort, String sortName) {
        this.id = id;
        this.sortid = sortid;
        this.sort = sort;
        this.sortName = sortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortid() {
        return sortid;
    }

    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }
}
