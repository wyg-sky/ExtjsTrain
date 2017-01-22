package com.ext.mvc.manager.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.FileUtils;

import com.ext.mvc.manager.UploadFileManager;

public class UploadFileManagerImpl implements UploadFileManager {
    
    /**
     * @description : 图片上传处理方法
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> UploadImageHandler(HttpServletRequest request){
        List<String> uploadImagePathList = new ArrayList<String>();
        //上传文件目录
        File uploadPath = new File("D:/temp");
        if (!uploadPath.exists()) {
           uploadPath.mkdirs();
        }
        // 临时文件目录
        File tempPathFile = new File("D:/temp/buffer/");
        if (!tempPathFile.exists()) {
           tempPathFile.mkdirs();
        }
        System.err.println("===============>当前工作目录："+System.getProperty("user.dir"));
        try {
            //为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
            DiskFileItemFactory dfFactory = new DiskFileItemFactory();
            // 设置工厂参数
            dfFactory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            dfFactory.setRepository(tempPathFile);//设置缓冲区目录
            /**
             * ServletContext获取的几种方法：
             *      Javax.servlet.http.HttpSession.getServletContext();
             *      Javax.servlet.jsp.PageContext.getServletContext();
             *      Javax.servlet.ServletConfig.getServletContext();
             */
            FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getServletContext());
            dfFactory.setFileCleaningTracker(fileCleaningTracker);
            
            //Create a new file upload handler 创建一个新的文件上传处理程序
            ServletFileUpload upload = new ServletFileUpload(dfFactory);
            upload.setFileSizeMax(4194304);// 设置允许上传最大文为4MB
            List<FileItem> items = upload.parseRequest(request);//得到所有的文件
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fileItem = (FileItem) i.next();
                //检查当前项目是普通表单项目还是上传文件。
                if (fileItem.isFormField()) {//1.如果是普通表单项目，显示表单内容。
                    //显示表单基本类型内容
                    System.out.println("这个表的项是普通的表单字段选项，名称为：" + fileItem.getFieldName()+"，字段值为："+fileItem.getString());
                }else{//2.如果是上传文件类型
                    String fileName = fileItem.getName();
                    System.out.println("===============》上传文件的名称为：" + fileName);
                    String fileExt = fileName.substring(fileName.lastIndexOf(".")); //获取上传文件的后缀名
                    System.out.println("===============》上传文件的后缀名：" + fileExt);
                    String newFileName = new SimpleDateFormat("YYYY-MM-DD-HHmmss").format(new Date())+fileExt;
                    if (fileName != null) {
                        //File fullFile = new File(fileItem.getName());
                        //File savedFile = new File(uploadPath, fullFile.getName());
                        File savedFile = new File(uploadPath, newFileName);
                        fileItem.write(savedFile);
                        uploadImagePathList.add(savedFile.getAbsolutePath());
                        System.out.println("===========>最终存储路径为："+savedFile.getAbsolutePath().replace("\\", "/"));
                    }
                }
            }
            //代码删除文件夹及其子文件夹和文件操作 
            FileUtils.deleteDirectory(tempPathFile);//清空缓冲区
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadImagePathList;
    }
    
}
