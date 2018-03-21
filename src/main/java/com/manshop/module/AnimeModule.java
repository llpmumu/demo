package com.manshop.module;

import com.manshop.bean.Address;
import com.manshop.bean.Akira;
import com.manshop.bean.Anime;
import com.manshop.bean.Role;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import java.util.ArrayList;
import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/anime")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class AnimeModule {
    @Inject
    private Dao dao;

    @At("/getOneAnime")
    @POST
    public ResponseModel getOneAnime(Anime anime) {
        Anime result = dao.fetch(Anime.class, Cnd.where("title", "=", anime.getTitle()));
        List<Role> roleList = dao.query(Role.class, Cnd.where("animeId", "=", result.getId()));
        List<Akira> akiraList = new ArrayList<>();
        for (Role role : roleList) {
            Akira akira = dao.fetch(Akira.class, Cnd.where("id", "=", role.getAkiraId()));
            akiraList.add(akira);
        }
        result.setRoleList(roleList);
        result.setAkiraList(akiraList);
        if (result.equals(""))
            return ResponseModel.getCommonFailedResponseModel("");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getYearAnime")
    @POST
    public ResponseModel getYearAnime(Anime anime) {
        List<Anime> result = dao.query(Anime.class, Cnd.where("title", "=", anime.getYear()));
        System.out.println(result.size());
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }
}

