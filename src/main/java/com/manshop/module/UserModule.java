package com.manshop.module;

import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/user")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class UserModule {
    @Inject
    private Dao dao;

    private Log log = Logs.get();

    @At("/login")
    @POST
    public ResponseModel login(String phone, String password) {
        if(Strings.isBlank(phone) || Strings.isBlank(password)){
            return ResponseModel.getCommonFailedResponseModel("用户名或密码不能为空");
        }
        List<User> user = dao.query(User.class, Cnd.where("phone", "=", phone).and("password","=",password));
        if(user.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("用户不存在");
        return ResponseModel.getCommonSuccessResponseModel("登录成功");
    }

    @At("/register")
    @POST
    public void register(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        dao.insert(user);
        System.out.println(user.getId());
    }
}
