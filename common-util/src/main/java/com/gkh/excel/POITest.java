package com.gkh.excel;

import java.io.*;
import java.util.List;

/**
 * @author gekaihui
 * @Description
 * @date 2020/10/21
 */
public class POITest {
    public  static void main(String[] args) {
        String fileName = "E:\\haier\\文档\\帐号数据1026.xlsx";
        String pre = "insert  into  `oab_user_invite_code_temp`(invite_code_old,invite_code_new) VALUES";
        StringBuilder stringBuilder = new StringBuilder(pre);
        FileWriter fw = null;
        int num = 0;
        try {
            List<String[]> list = POIUtil.readExcel(fileName);
            for(String[] arr : list) {
                num++;
                stringBuilder.append("('");
                stringBuilder.append(arr[0]);
                stringBuilder.append("','");
                stringBuilder.append(arr[2]);
                stringBuilder.append("'),");
            }
            System.out.println(num);
            //创建字符输出流对象，负责向文件内写入
            File f=new File("E:\\sql.txt");
            // 声明File对象
            OutputStream out = null ;
            // 准备好一个输出的对象
            out = new FileOutputStream(f) ;
            // 准备一个字符串
            byte b[] = stringBuilder.toString().getBytes() ;
            // 只能输出byte数组，所以将字符串变为byte数组
            out.write(b) ;
            // 将内容输出，
            out.close() ;
//            fw = new FileWriter(f);
//            //将str里面的内容读取到fw所指定的文件中
//            fw.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
