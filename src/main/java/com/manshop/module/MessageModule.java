package com.manshop.module;

import com.manshop.bean.Message;
import com.manshop.bean.User;
import com.manshop.model.ResponseModel;
import com.manshop.util.SortUtil;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.adaptor.JsonAdaptor;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;

import java.util.List;

@IocBean  // 配置这个类能被ioc容器发现
@At("/message")  // 配置这个模块的根路径
@Ok("json")  // 配置这个模块的默认返回格式是json
@AdaptBy(type = JsonAdaptor.class) // 以json流的方式入参
public class MessageModule {
    SortUtil sortUtil = new SortUtil();
    @Inject
    private Dao dao;

    @At("/newMsg")
    @POST
    public ResponseModel newMsg(Message message) {
        dao.insert(message);
        return ResponseModel.getCommonSuccessResponseModel(message.getId());
    }

    @At("/getMsg")
    @POST
    public ResponseModel getMsg(Message message) {
        List<Message> result = dao.query(Message.class, Cnd.where("sender", "=", message.getSender()).and("receiver","=",message.getReceiver())
        .or("sender","=",message.getReceiver()).and("receiver","=", message.getSender()));
//        for (int i = 0; i < result.size(); i++) {
//            //接受方
//            User rUser = dao.fetch(User.class, result.get(i).getReceiver());
//            result.get(i).setRuser(rUser);
//
//            //发送方
//            User sUser = dao.fetch(User.class, result.get(i).getSender());
//            result.get(i).setSuser(sUser);
//        }
        sortUtil.mTimeSort(result);
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("获取聊天记录失败失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }

    @At("/getMsgList")
    @POST
    public ResponseModel getMsgList(Message message) {
        int id = message.getSender();
        Sql sql = Sqls.create("SELECT * FROM t_msg WHERE sender = "+id +" or receiver = "+id +" AND sender NOT IN (SELECT receiver FROM t_msg WHERE sender = "+id +") GROUP BY receiver");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Message.class));
        dao.execute(sql);
        List<Message> result = sql.getList(Message.class);
//        List<Message> result = dao.query(Message.class,  Cnd.wrap("SELECT *,COUNT(DISTINCT receiver) FROM t_msg WHERE sender = "+id +" or receiver = "+id +" AND sender NOT IN (SELECT receiver FROM t_msg WHERE sender = "+id +") GROUP BY receiver"));
        for (int i = 0; i < result.size(); i++) {
            //接受方
            User rUser = dao.fetch(User.class, result.get(i).getReceiver());
            result.get(i).setRuser(rUser);

            //发送方
            User sUser = dao.fetch(User.class, result.get(i).getSender());
            result.get(i).setSuser(sUser);
        }
        sortUtil.mTimeSort(result);
        if (result.isEmpty())
            return ResponseModel.getCommonFailedResponseModel("获取聊天记录失败失败");
        return ResponseModel.getCommonSuccessResponseModel(result);
    }
}
