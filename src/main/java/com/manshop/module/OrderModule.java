package com.manshop.module;

import com.manshop.bean.Order;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Dao;
import org.nutz.el.opt.custom.CustomMake;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

@IocBean  // 配置这个类能被ioc容器发现
@At("/order")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class OrderModule {
    @Inject
    private Dao dao;

    @At("/newOrder")
    @POST
    public ResponseModel getMyGood(Order order) {
        dao.insert(order);
        return ResponseModel.getCommonSuccessResponseModel("新建商品成功");
    }

//    @At("/")
}
