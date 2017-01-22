package com.ext.mvc.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.ext.mvc.manager.UploadFileManager;
import com.ext.mvc.manager.impl.UploadFileManagerImpl;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UploadFileManager uploadFileManager; //文件上传管理类，真正干活的类。
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // 构造方法中：初始化时，将上传文件Manager类赋值.
        if(null == uploadFileManager){
            //注意这里声明的接口类型，但是赋值时，赋的是它的实现类(实现接口的子类)
            uploadFileManager = new UploadFileManagerImpl();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 通过Get方法，访问指定路径的图片，将图片转换成数据流返回。
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String type = request.getParameter("type");
	    if(StringUtils.isNotBlank(type) && type.equals("showImage")){
	        response.setContentType("image/jpeg");//设置相应信息的类型
	        String path = request.getParameter("path");
	        System.out.println("================>获得了图片显示请求路径："+path);
	        //String serverUrl = "D:/piture-tomcat/webapps/ROOT/";
	        //String realPath =serverUrl+path;
	        //String realPath = ServletActionContext.getServletContext().getRealPath(path);
	        //String realPath = request.getServletContext().getRealPath(path);
	        String realPath = path; 
	        File file = new File(realPath);
	        if( !file.exists()){//文件不存在时，返回默认图片
	            realPath = request.getServletContext().getRealPath("image/220x220/setting.png");
	        }
	        //ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	        //InputStream input = new BufferedInputStream(new FileInputStream(realPath));
	        
	        OutputStream outps=response.getOutputStream();//获得servlet的servletoutputstream对象
	        FileInputStream inps=new FileInputStream(realPath);//打开图片文件
	        byte[] buffer = new byte[2048];//每次从文件里面读取2k
	        int count;
	        try {  
	            while ((count = inps.read(buffer)) > 0) {
	                outps.write(buffer, 0, count);//向客户输入图片
	              //this.inputStream = new ByteArrayInputStream(bos.toByteArray());
	            }
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  finally{
	            outps.close();
	            inps.close();
	        }
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 在Post方法中调用，文件上传实现类。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");//放在第一行
	    PrintWriter out = null;
	    try {
	        List<String> imagePaths = uploadFileManager.UploadImageHandler(request);
	        out = response.getWriter();
	        if( null != imagePaths && imagePaths.size() > 0){
	            Map<String,Object> backObj = new HashMap<String,Object>();
	            backObj.put("success", true);
	            backObj.put("msg", imagePaths);
	            System.out.println("================>返回结果："+JSONObject.fromObject(backObj).toString());
	             out.println(JSONObject.fromObject(backObj).toString());
	        }else{
	            out.println("上传失败!");
	        }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
	    
	    
	}

}
