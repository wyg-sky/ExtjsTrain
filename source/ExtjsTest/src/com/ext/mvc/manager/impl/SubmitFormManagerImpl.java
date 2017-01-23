package com.ext.mvc.manager.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.ext.mvc.dao.BasicDao;
import com.ext.mvc.dao.impl.BasicDaoImpl;
import com.ext.mvc.manager.SubmitFormManager;
import com.ext.mvc.model.UserInfo;
/**
 * @author WangYG
 * @description : 表单提交处理业务实现类
 */
public class SubmitFormManagerImpl implements SubmitFormManager {
    private BasicDao basicDao;
    //构造方法中初始化basicDao实例
    public SubmitFormManagerImpl(){
        if(null == basicDao){
            basicDao = new BasicDaoImpl();
        }
    }
    /**
     * @description : 接收表单数据，并处理方法.
     * @author : WangYG
     * @date : 2017-1-23 10:58:54
     */
    public String receiveFormData(HttpServletRequest request){
        //1.使用request获取表单中的全部数据(request对象必须由上一步Servlet中传过来，必须为同一request对象！)
        String username = request.getParameter("username");//用户名
        int age = Integer.valueOf(request.getParameter("age"));//年龄
        String phone = request.getParameter("phone");//电话
        String email = request.getParameter("email");//邮箱
        String likes = request.getParameter("likes");//爱好
        int faceStatus = Integer.valueOf(request.getParameter("faceStatus"));//政治面貌
        String address = request.getParameter("address");//地址
        String remark = request.getParameter("remark");//备注
        //2.将获取的表单数据填充到UserInfo实体类对象中
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setAge(age);
        user.setPhone(phone);
        user.setEmail(email);
        user.setLikes(likes);
        user.setFaceStatus(faceStatus);
        user.setAddress(address);
        user.setRemark(remark);
        //3.将对象传入DAO层，进行保存，持久化到数据库中
        Map<String, Object> backMap = new HashMap<String,Object>();
        try {
            UserInfo newUser = basicDao.saveObj(user);
            backMap.put("success", true);
            backMap.put("data", newUser);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //4.将持久化以后的实体类对象返回到前台页面中
        return JSONObject.fromObject(backMap).toString();
    }
    
    /**
     * @description : 根据ID查找实体类对象数据，并返回.
     * @author : WangYG
     * @date : 2017-1-23 17:00:23
     */
    public String findUserInfoById(HttpServletRequest request)throws Exception{
        String id = request.getParameter("id");
        UserInfo user = basicDao.getObj(id);
        Map<String,Object> backMap = new HashMap<String,Object>();
        if(null != user){
            backMap.put("success", true);
            backMap.put("UserInfo", user);
        }else{
            backMap.put("success", false);
            backMap.put("msg", "要查找的ID信息不存在!");
        }
        return JSONObject.fromObject(backMap).toString();
    }
}
