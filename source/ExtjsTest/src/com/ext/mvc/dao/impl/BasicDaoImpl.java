package com.ext.mvc.dao.impl;

import org.apache.commons.lang.StringUtils;

import com.ext.mvc.dao.BasicDao;
import com.ext.mvc.model.UserInfo;

public class BasicDaoImpl implements BasicDao {

    public UserInfo saveObj(UserInfo user)throws Exception{
        System.out.println("将UserInfo对象保存到数据库中，并会返回保存后的对象实体。");
        /**
         * 此处应为对象保存代码。。。
         * .....
         */
        System.out.println(user.toString());
        return user;
    }
    
    public UserInfo getObj(String id)throws Exception{
        //这个方法应该是根据传入的id,去数据库查找对应的数据，并返回给上级调用方法！
        UserInfo user = null;
        if(StringUtils.isNotBlank(id) && id.equals("100")){
            user = new UserInfo();
            user.setUsername("后台数据");
            user.setAge(38);
            user.setEmail("312455@qq.com");
            user.setPhone("15212348888");
            user.setFaceStatus(2);
            user.setLikes("喜欢：编程，写代码，加班，挣钱...");
            user.setAddress("中国地球村");
            user.setRemark("这是一条来自服务器后台的反馈信息，就是这么神奇！！！！");
        }
        return user;
    }
}
