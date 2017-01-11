package com.ext.direct.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import com.softwarementors.extjs.djn.config.annotations.DirectPollMethod;

/**
 * @description : 后台JAVA方法，要声明为轮询方法，必须要声明为@DirectPollMethod
 * 注意：1.轮询方法必须定义为：@DirectPollMethod声明.
 *            2.指定唯一事件名称,event="name",该事件名称必须唯一，前台JS通过在此声明的监听事件名称获取该方法的返回值。
 *            3.接收参数必须定义为 Map<String,String>类型，方便获取键值对的形式。
 * @author WangYG
 * @date 2017-1-11 09:19:47
 */
public class DirectAPIDemo {
    
    @DirectPollMethod(event="message")
    public String sayHello(Map<String,String> params){
        System.err.println(params.get("data"));
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String newDate = sdf.format(new Date());
        return "Hello everyone， I'm  轮询 - PollingProvider Class！this is "+newDate+" return message!";
    }
    
    @DirectPollMethod(event="message2")
    public String getMessage(Map<String,String> params){
        System.err.println("message2...............");
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
        String newDate = sdf.format(new Date());
        return " I'm  轮询 - messgae2！this is "+newDate+" return message!";
    }
    
}
