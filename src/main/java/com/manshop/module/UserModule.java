package com.manshop.module;

import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/user")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
@Filters(@By(type=CrossOriginFilter.class))
public class UserModule {
    @Inject
    private Dao dao;

    private Log log = Logs.get();

    @At("/login")
    @POST
    public ResponseModel login(User user) {
        if(Strings.isBlank(user.getPhone()) || Strings.isBlank(user.getPassword())){
            return ResponseModel.getCommonFailedResponseModel("手机号或密码不能为空");
        }
        User result = dao.fetch(User.class, Cnd.where("phone", "=", user.getPhone()).and("password","=",user.getPassword()));
//        System.out.println(result.get(0).getId());
//        if(result.isEmpty())
//            return ResponseModel.getCommonFailedResponseModel("用户不存在");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/register")
    @POST
    public ResponseModel register(User user) {
        if(Strings.isBlank(user.getUsername())||Strings.isBlank(user.getPhone()) || Strings.isBlank(user.getPassword())){
            return ResponseModel.getCommonFailedResponseModel("手机号或密码不能为空");
        }
        dao.insert(user);
        System.out.println(user.getId());
        return ResponseModel.getCommonSuccessResponseModel("注册成功");
    }

    @At("/updateInfo")
    @POST
    public ResponseModel updateInfo(User user) {
        dao.update(User.class, Chain.make("username", user.getUsername()), Cnd.where("phone", "=", user.getPhone()));
        return ResponseModel.getCommonSuccessResponseModel("更改成功");
    }



    //后台使用接口
    @At("/getUser")
    public ResponseModel getUser(){
        List<User> result = dao.query(User.class,null);
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/delUser")
    public ResponseModel delUser(@Param("id") int id){
        System.out.println(id);
        dao.delete(User.class,id);
        return ResponseModel.getCommonSuccessResponseModel("删除成功");
    }

    @At("/get")
    public ResponseModel getOneUser(@Param("id") int id){
        System.out.println(id);
        List<User> user = dao.query(User.class,Cnd.where("id","=",id));
        return ResponseModel.getCommonSuccessResponseModel(user);
    }
}
