package com.manshop.module;

import com.manshop.bean.Show;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/show")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
@Filters(@By(type = CrossOriginFilter.class))
public class ShowModule {
    @Inject
    private Dao dao;

    @At("/getAllShow")
//    @POST
    public ResponseModel getAllShow(Show show) {
        Date date = new Date(System.currentTimeMillis());
        List<Show> result = dao.query(Show.class, Cnd.where("showtime", ">", date).asc("showtime"));
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("目前没有漫展");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getAddressShow")
    @POST
    public ResponseModel getAddressShow(Show show) {
        Date date = new Date(System.currentTimeMillis());
        List result = dao.query(Show.class, Cnd.where("province", "=", show.getProvince()).and("showtime", ">", date).asc("showtime"));
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("当前城市没有举办漫展");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/delShow")
    public ResponseModel delShow(@Param("id") int id) {
        dao.delete(Show.class, id);
        return ResponseModel.getCommonSuccessResponseModel("删除成功");
    }

    @At("/add")
    @AdaptBy(type = JsonAdaptor.class)
    public ResponseModel addShow(@Param("title") String title, @Param("picture") String picture, @Param("province") String province, @Param("address") String address, @Param("showdate") String showdate, @Param("showtime") String showtime) {
        System.out.println(title);
        Show show = new Show();

        String date = showtime;
        date = date.replace("Z", " UTC");//注意是空格+UTC
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//注意格式化的表达式
        java.util.Date date1 = null;
        try {
            date1 = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        show.setTitle(title);
        show.setPicture(picture);
        show.setProvince(province);
        show.setAddress(address);
        show.setShowdate(showdate);
        show.setShowtime(date1);
        dao.insert(show);
        return ResponseModel.getCommonSuccessResponseModel("上传成功");
    }
}