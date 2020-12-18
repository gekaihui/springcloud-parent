package com.gkh.demo;

import java.text.DecimalFormat;

/**
 * @author gekaihui
 * @Description
 * @date 2020/11/18
 */
public class Test {
    public static void main(String[] args) {
        DecimalFormat df = new DecimalFormat("0000");
        Long id = 569L;
        System.out.println(df.format(id));
    }
}
