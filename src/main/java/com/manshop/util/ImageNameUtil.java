package com.manshop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by Lin on 2018/4/10.
 */

public class ImageNameUtil {
    /**
     * 生成图片编号，该值在商户端应保持唯一（String(8,20) 仅支持数字或字母）
     */
    public static String getName() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);
        Random r = new Random();
        key = key + r.nextInt(100000);
        return key;
    }
}
