package com.manshop.util;

import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Util {

    public static String changeToImage(String base64) {
        String imageName = "";
        String[] txtpicture = base64.split(";");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            for (int i = 0; i < txtpicture.length; i++) {
                //Base64解码
                byte[] b = new byte[0];
                b = decoder.decodeBuffer(txtpicture[i]);

                for (int k = 0; k < b.length; ++k) {
                    if (b[k] < 0) {//调整异常数据
                        b[k] += 256;
                    }
                }
                //生成jpeg图片
                String sName = ImageNameUtil.getName() + ".jpg";
                System.out.println(sName);
                String imgFilePath = "E:\\image\\" + sName;//新生成的图片
                imageName = sName + ";" + imageName;
                OutputStream out = new FileOutputStream(imgFilePath);
                out.write(b);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageName;
    }
}
