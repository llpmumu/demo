package com.manshop.module;

import com.manshop.bean.Address;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/address")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class AddressModule {
    @Inject
    private Dao dao;

    @At("/getAddress")
    @POST
    public ResponseModel getAddress(Address address) {
        List<Address> result = dao.query(Address.class, Cnd.where("uid", "=", address.getUser().getId()));
        if(result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("无收件地址");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/newAddress")
    @POST
    public ResponseModel newAddress(Address address) {
        dao.insert(address);
        return ResponseModel.getCommonSuccessResponseModel("新建地址成功");
    }
}
