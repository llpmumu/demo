package com.manshop.bean;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.util.List;

@Table("t_sort")
public class Sort {
    @Id
    private Integer id;
    @ColDefine(type = ColType.VARCHAR, width = 50)
    private String sortName;
    private List<SmallSort> smallSortList;

    public Sort() {
    }

    public Sort(Integer id, String sortName) {
        this.id = id;
        this.sortName = sortName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public List<SmallSort> getSmallSortList() {
        return smallSortList;
    }

    public void setSmallSortList(List<SmallSort> smallSortList) {
        this.smallSortList = smallSortList;
    }
}
