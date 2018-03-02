package com.manshop.module;

import com.manshop.bean.Show;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

@IocBean  // 配置这个类能被ioc容器发现
@At("/order")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class ShowModule {
    @Inject
    private Dao dao;

    @At("/newShow")
    @POST
    public ResponseModel newOrder(Show show) {
        dao.insert(show);
        return ResponseModel.getCommonSuccessResponseModel(show.getId());
    }
}