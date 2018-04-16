package com.manshop.module;

import com.manshop.bean.Address;
import com.manshop.bean.Goods;
import com.manshop.bean.Order;
import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import com.manshop.util.SortUtil;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/order")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class OrderModule {
    SortUtil sortUtil = new SortUtil();
    @Inject
    private Dao dao;

    @At("/newOrder")
    @POST
    public ResponseModel newOrder(Order order) {
        order.setDelivery(0);
        dao.insert(order);
        Goods goods = dao.fetch(Goods.class, order.getGid());
        goods.setState(1);
        dao.update(goods);
        return ResponseModel.getCommonSuccessResponseModel(order.getId());
    }

    @At("/getOneOrder")
    @POST
    public ResponseModel getOneOrder(Order order) {
        Order result = dao.fetch(Order.class, Cnd.where("id", "=", order.getId()));

        //买家
        User bUser = dao.fetch(User.class, result.getBuid());
        result.setBuser(bUser);

        //卖家
        User sUser = dao.fetch(User.class, result.getSuid());
        result.setSuser(sUser);

        Goods good = dao.fetch(Goods.class, result.getGid());
        result.setGood(good);

        Address address = dao.fetch(Address.class, result.getAid());
        result.setAddress(address);
        if (result == null)
            return ResponseModel.getCommonFailedResponseModel("获取订单失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getMyOrder")
    @POST
    public ResponseModel getMyOrder(Order order) {
        List<Order> result = dao.query(Order.class, Cnd.where("buid", "=", order.getBuid()));
        for (int i = 0; i < result.size(); i++) {
            //买家
            User bUser = dao.fetch(User.class, result.get(i).getBuid());
            result.get(i).setBuser(bUser);

            //卖家
            User sUser = dao.fetch(User.class, result.get(i).getSuid());
            result.get(i).setBuser(sUser);

            Goods good = dao.fetch(Goods.class, result.get(i).getGid());
            result.get(i).setGood(good);
        }
//        Collections.sort(result, sortUtil);
        sortUtil.oTimeSort(result);
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("获取订单数据失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getSellOrder")
    @POST
    public ResponseModel getSellOrder(Order order) {
        List<Order> result = dao.query(Order.class, Cnd.where("suid", "=", order.getSuid()));
        for (int i = 0; i < result.size(); i++) {
            //买家
            User bUser = dao.fetch(User.class, result.get(i).getBuid());
            result.get(i).setBuser(bUser);

            //卖家
            User sUser = dao.fetch(User.class, result.get(i).getSuid());
            result.get(i).setBuser(sUser);

            Goods good = dao.fetch(Goods.class, result.get(i).getGid());
            result.get(i).setGood(good);
        }
//        Collections.sort(result, sortUtil);
        sortUtil.oTimeSort(result);
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("获取订单数据失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/updateOrder")
    @POST
    public ResponseModel updataOrder(Order order) {
        dao.update(Order.class, Chain.make("delivery", order.getDelivery()), Cnd.where("id", "=", order.getId()));
        dao.update(Order.class, Chain.make("trackingnum", order.getTrackingnum()), Cnd.where("id", "=", order.getId()));
        dao.update(Order.class, Chain.make("state", order.getState()), Cnd.where("id", "=", order.getId()));
        Order result = dao.fetch(Order.class, Cnd.where("id", "=", order.getId()));
        Goods good = dao.fetch(Goods.class, result.getGid());
        result.setGood(good);
        return ResponseModel.getCommonSuccessResponseModel(result);
    }
}
