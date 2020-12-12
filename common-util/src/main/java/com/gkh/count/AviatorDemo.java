package com.gkh.count;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gekaihui
 * @date 2020/5/18
 */
public class AviatorDemo {
    public static void main(String[] args){
        //求值
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);

        //内置函数
        String str = "22";
        Map<String, Object> env1 = new HashMap<>();
        env1.put("str", str);
        Long strLength= (Long)AviatorEvaluator.execute("string.length(str)", env1);
        System.out.println(strLength);

        String expression = "a * 99 + (b+c) * 100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<>();
        env.put("a", 9999);
        env.put("b", 9999);
        env.put("c", 9999);
        // 执行表达式
        Long result2 =  (Long)compiledExp.execute(env);
        System.out.println(result2);
    }
}
