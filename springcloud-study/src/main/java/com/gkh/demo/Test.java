package com.gkh;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/18
 */
public class Test {
    public static void main(String[] args) {
       System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = new Date().getTime();
        System.out.println(time);
    }
}
