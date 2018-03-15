package com.manshop.module;

import com.manshop.bean.Show;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/order")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class ShowModule {
    @Inject
    private Dao dao;

    @At("/getAllShow")
    @POST
    public ResponseModel getAllShow(Show show) {
        List result = dao.query(Show.class,null);
        if(result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("目前没有漫展");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("getAddressShow")
    @POST
    public ResponseModel getAddressShow(Show show){
        List result = dao.query(Show.class, Cnd.where("province","=",show.getProvince()));
        if(result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("当前城市没有举办漫展");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }
}