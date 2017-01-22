package com.ext.mvc.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface UploadFileManager {
    
    /**
     * @description : 图片上传处理方法
     * @param request
     * @return
     */
    public List<String> UploadImageHandler(HttpServletRequest request);
}
