package com.manshop.module;

import com.manshop.bean.Goods;
import com.manshop.bean.SmallSort;
import com.manshop.bean.Sort;
import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;

import java.util.ArrayList;
import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/sort")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class SortModule {

    @Inject
    private Dao dao;

    @At("/getSort")
    @GET
    public ResponseModel getSort() {
        List<Sort> sortList = dao.query(Sort.class, null);
        List<SmallSort> smallSorts = new ArrayList<>();
        for (Sort sort : sortList) {
            if (sort.getId() == 0) {
                smallSorts = dao.query(SmallSort.class, null);
            } else {
                smallSorts = dao.query(SmallSort.class, Cnd.where("sortid", "=", sort.getId()));
            }
            sort.setSmallSortList(smallSorts);
        }
        if (sortList.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("");
        return ResponseModel.getCommonSuccessResponseModel(sortList);
    }

    @At("/getSortGood")
    @POST
    public ResponseModel getSortGood(SmallSort smallSort) {
        System.out.println(smallSort.getSortName());
        SmallSort sort = dao.fetch(SmallSort.class, Cnd.where("sortName", "=", smallSort.getSortName()));
        List<Goods> result = new ArrayList<>();
        if (sort.getId() == 1) {
            result = dao.query(Goods.class, null);
        } else {
            result = dao.query(Goods.class, Cnd.where("sid", "=", sort.getId()));
        }
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
}
