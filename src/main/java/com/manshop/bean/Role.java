package com.manshop.bean;

import org.nutz.dao.entity.annotation.*;

@Table("t_role")
public class Role {
    @Id
    private Integer id;
    @ColDefine(type= ColType.VARCHAR,width = 20)
    private String name;
    @ColDefine(notNull=true)
    private Integer animeId;
    @One(field = "animeId", key = "id")
    private Anime anime;
    @ColDefine(notNull=true)
    private Integer akiraId;
    @One(field = "akiraId", key = "id")
    private Role role;
    private String akiraName;

    public Role(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnimeId() {
        return animeId;
    }

    public void setAnimeId(Integer animeId) {
        this.animeId = animeId;
    }

    public Anime getAnime() {
        return anime;
    }

    public void setAnime(Anime anime) {
        this.anime = anime;
    }

    public Integer getAkiraId() {
        return akiraId;
    }

    public void setAkiraId(Integer akiraId) {
        this.akiraId = akiraId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAkiraName() {
        return akiraName;
    }

    public void setAkiraName(String akiraName) {
        this.akiraName = akiraName;
    }
}
