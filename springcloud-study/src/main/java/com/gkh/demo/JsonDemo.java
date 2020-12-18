package com.gkh.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author gekaihui
 * @date 2020/12/1
 */
public class JsonDemo {
    public static void main(String[] args) {
//        String jaStr = "[{\"industryCode\":\"12\",\"industryValue\":\"34\",\"microCode\":\"56\",\"microValue\":\"78\"},\n" +
//                "{\"industryCode\":\"910\",\"industryValue\":\"1112\",\"microCode\":\"1314\",\"microValue\":\"1516\"}]";
//        //将jsonArray字符串转化为JSONArray
//        JSONArray jsonArray = JSONArray.parseArray(jaStr);



            JSONArray jSONArray = new JSONArray();
            JSONObject jb = new JSONObject();
            jb.put("id", 1);
            jb.put("name", "s");
            jSONArray.add(jb);
            JSONObject j1 = new JSONObject();
            j1.put("id", 2);
            j1.put("name", "s");
            jSONArray.add(j1);
            StringBuffer sBuffer = new StringBuffer();
            jSONArray.stream().forEach(jsonobejct->arrayIdToString((JSONObject) jsonobejct,sBuffer));
            System.out.println(sBuffer.toString());
        }

        private static StringBuffer arrayIdToString(JSONObject jsonobejct,
                StringBuffer sBuffer) {
            return sBuffer.append(jsonobejct.getInteger("id")).append(",");
        }
}
