package com.manshop.module;

import com.manshop.bean.Goods;
import com.manshop.bean.SmallSort;
import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import com.manshop.util.Base64Util;
import com.manshop.util.SortUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/goods")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
@Filters(@By(type = CrossOriginFilter.class))
public class GoodsModule {
    @Inject
    private Dao dao;
    SortUtil sortUtil = new SortUtil();

    @At("/getMyGood")
    @POST
    public ResponseModel getMyGood(Goods goods) {
        List<Goods> result = dao.query(Goods.class, Cnd.where("uid", "=", goods.getUid()).asc("releasedate"));
//        sortUtil.gTimeSort(result);
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("无发布的商品");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getGood")
    @POST
    public ResponseModel getGood(Goods goods) {
        List<Goods> result = dao.query(Goods.class, Cnd.wrap("uid !=" + goods.getUid() + " ORDER BY  RAND() LIMIT 10"));
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            User user = dao.fetch(User.class, result.get(i).getUid());
            result.get(i).setUser(user);
            if (result.get(i).getState() == 1) {
                result.remove(i);
                i--;
            }
        }
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("获取商品数据失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getOneGood")
    @POST
    public ResponseModel getOneGood(Goods good) {
        Goods result = dao.fetch(Goods.class, Cnd.where("id", "=", good.getId()));
        User user = dao.fetch(User.class, result.getUid());
        result.setUser(user);
        if (result == null)
            return ResponseModel.getCommonFailedResponseModel("获取商品失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/newGood")
    @POST
    public ResponseModel newGood(Goods good) {
//        if (good.getRental().isEmpty())
//            good.setType(1);
//        else
//            good.setType(2);
        good.setState(0);
        good.setPicture(Base64Util.changeToImage(good.getPicture()));
        dao.insert(good);
        return ResponseModel.getCommonSuccessResponseModel("新建商品成功");
    }

    @At("/updateGood")
    @POST
    public ResponseModel updateGood(Goods good) {
        System.out.println(good.getId());
        dao.update(good, Cnd.where("id", "=", good.getId()));
        return ResponseModel.getCommonSuccessResponseModel("修改商品成功");
    }

    @At("/deleteGood")
    @POST
    public ResponseModel deleteGood(Goods good) {
        System.out.println(good.getId());
        dao.delete(Goods.class, good.getId());
        return ResponseModel.getCommonSuccessResponseModel("删除商品成功");
    }

    @At("/searchGoods")
    @POST
    public ResponseModel searchGoods(Goods good) {
        System.out.println(good.getTitle());
        List<Goods> result = dao.query(Goods.class, Cnd.where("title", "like", "%" + good.getTitle() + "%"));
        for (int i = 0; i < result.size(); i++) {
            User user = dao.fetch(User.class, result.get(i).getUid());
            result.get(i).setUser(user);
            result.get(i).setSmallSort(dao.fetch(SmallSort.class, Cnd.where("id", "=", result.get(i).getSid())));
            if (result.get(i).getState() == 1) {
                result.remove(i);
                i--;
            }
        }
        return ResponseModel.getCommonSuccessResponseModel(result);
    }


    @At("/get")
    public ResponseModel get(Goods goods) {
        List<Goods> result = dao.query(Goods.class,null);
        for (int i = 0; i < result.size(); i++) {
            SmallSort smallSort = dao.fetch(SmallSort.class,Cnd.where("id","=",result.get(i).getSid()));
            result.get(i).setSortName(smallSort.getSortName());
        }
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/del")
    public ResponseModel del(@Param("id") int id) {
        System.out.println(id);
        dao.delete(User.class,id);
        return ResponseModel.getCommonSuccessResponseModel("删除成功");
    }
}
