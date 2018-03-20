package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_anime")
public class Anime {
    @Id
    private Integer id;
    @ColDefine(notNull=true,type = ColType.VARCHAR, width = 30)
    private String title;
    @ColDefine(type = ColType.VARCHAR, width = 512)
    private String detail;
    @ColDefine(type = ColType.VARCHAR, width = 10)
    private String year;
    @ColDefine(type = ColType.VARCHAR, width = 5)
    private String episodes;

    public Anime() {
    }

    public Anime(Integer id, String title, String detail, String year, String episodes) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.year = year;
        this.episodes = episodes;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }
}

