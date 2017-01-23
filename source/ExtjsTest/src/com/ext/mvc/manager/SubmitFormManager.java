package com.ext.mvc.manager;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WangYG
 * @description : 表单提交处理业务类接口
 */
public interface SubmitFormManager {
    /**
     * @description : 接收表单数据，并处理方法.
     * @author : WangYG
     * @date : 2017-1-23 10:58:54
     */
    public String receiveFormData(HttpServletRequest request);
    /**
     * @description : 根据ID查找实体类对象数据，并返回.
     * @author : WangYG
     * @date : 2017-1-23 17:00:23
     */
    public String findUserInfoById(HttpServletRequest request)throws Exception;
}
