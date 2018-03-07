package com.manshop.util;

import com.manshop.bean.Goods;
import com.manshop.bean.Message;
import com.manshop.bean.Order;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

    //    订单按时间排序
    public static void oTimeSort(List<Order> list) {
        Collections.sort(list, new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                Order order1 = (Order) o1;
                Order order2 = (Order) o2;

                int flag = order2.getOrdertime().compareTo(order1.getOrdertime());
                return flag;
            }
        });
    }

    //    商品按时间排序
    public static void gTimeSort(List<Goods> list) {
        Collections.sort(list, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                Goods good1 = (Goods) o1;
                Goods good2 = (Goods) o2;

                int flag = good2.getGoodtime().compareTo(good1.getGoodtime());
                return flag;
            }
        });
    }

    //    商品按时间排序
    public static void mTimeSort(List<Message> list) {
        Collections.sort(list, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                Message message1 = (Message) o1;
                Message message2 = (Message) o2;

                int flag = message1.getMsgtime().compareTo(message2.getMsgtime());
                return flag;
            }
        });
    }
}
