package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Servlet 实现文件上传 参考：
	 * https://www.jianshu.com/p/1968019b6927
	 */
       
	public static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd-HHmmss");//设置日期格式
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(req, res);
		System.out.println("File upload post request received.");
		
		// 获取文件上传组件
        Part part = req.getPart("file");
        
        // 获取文件的路径
        String header = part.getHeader("content-disposition");
        String fileName = header.substring(header.indexOf("filename=") + 10, header.length() - 1);

        // 获取文件名
        //String name = UploadUtils.getRealName(path);
        
        String now = df.format(new Date());// new Date()为获取当前系统时间

        // 对拷流
        InputStream inputStream = part.getInputStream();
        String path = this.getServletContext().getRealPath("/upload");
        FileOutputStream outputStream = new FileOutputStream(new File(path, now+"-"+fileName));
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
        }

        // 关闭资源
        outputStream.close();
        inputStream.close();

        // 删除临时文件
        part.delete();

        res.setContentType("text/html;charset=utf-8");
        res.getWriter().print("文件上传成功！");
		
		res.getWriter().append("Served at: ").append(req.getContextPath());
		
	}

}
