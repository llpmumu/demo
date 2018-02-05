package com.manshop.module;

import com.manshop.bean.Goods;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/goods")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class GoodsModule {
    @Inject
    private Dao dao;

    @At("/getGood")
    @POST
    public ResponseModel getAddress(Goods goods) {
        List<Goods> result = dao.query(Goods.class, Cnd.where("uid", "=", goods.getUid()));
        if(result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("无收件地址");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/newGood")
    @POST
    public ResponseModel newAddress(Goods good) {
        if(good.getRental().isEmpty())
            good.setType(1);
        else
            good.setType(2);
        good.setState(0);
        good.setPicture("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1121475478,2545730346&fm=27&gp=0.jpg;https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1116587413,1335069674&fm=27&gp=0.jpg");
        dao.insert(good);
        return ResponseModel.getCommonSuccessResponseModel("新建商品成功");
    }
}
