package com.ext.mvc.dao;

import com.ext.mvc.model.UserInfo;

public interface BasicDao {
    
    public UserInfo saveObj(UserInfo user)throws Exception;
    
    public UserInfo getObj(String id)throws Exception;
}
