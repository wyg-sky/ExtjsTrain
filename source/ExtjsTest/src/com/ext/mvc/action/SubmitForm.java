package com.ext.mvc.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.mvc.manager.SubmitFormManager;
import com.ext.mvc.manager.impl.SubmitFormManagerImpl;

/**
 * @author WangYG
 * @description : 接收表单提交数据Servlet类，测试表单提交数据。
 */
public class SubmitForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private SubmitFormManager fromManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitForm() {
        super();
        fromManager = new SubmitFormManagerImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//放在第一行
        PrintWriter out = null;
	    try{
	        String backMsg = fromManager.findUserInfoById(request);
	        out = response.getWriter();
	        out.println(backMsg);
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	        if(null != out){
	            try{
	                out.close();
	            }catch(Exception e){
	                e.printStackTrace();
	            }finally{
	                out.close();
	            }
	        }
	    }
	    
	}

	/**
	 *     业务逻辑顺序分析：
	 *     1.【控制器层-->接收请求数据】客户端提交表单(表单都是通过POST方式提交)数据，到服务器端。
	 *     2.【Manager或者Service业务层】将request对象传递给表单业务处理类FromManager进行处理，通过request对象获取表单数据，进行业务处理。
	 *     3.【DAO数据持久化层】获取到表单数据后，将数据装配到UserInfo实体类中，将实体类传到DAO层进行数据持久化操作(保存到数据库中)，然后返回保存后的对象。
	 *     4.【控制器转到-->View视图层】将保存后的JAVA对象，转换成JSON对象返回到前台客户端，进行解析输出。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//放在第一行
        PrintWriter out = null;
	    try {
            String backMsg = fromManager.receiveFormData(request);
            out = response.getWriter();
            out.println(backMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(null != out){
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}

}
