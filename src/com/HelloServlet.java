package com;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/query")
public class HelloServlet extends HttpServlet {
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Request received.");
		String result = "";
		Statement stat = null;
		
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("UTF-8");
		
		String operLevel = req.getParameter(WebManageServlet.OPER_LEVEL);
		
		if(null == operLevel) {
			result = MySQLDemo.query();
		}else if(operLevel!=null && WebManageServlet.OPER_LEVEL_GROUP.equals(operLevel)) {
			try {
				String sql = "select * from web_group order by order_id";
				stat = MySQLDemo.conn.createStatement();
				ResultSet rs = stat.executeQuery(sql);
				while(rs.next()) {
					int id = rs.getInt("id");
	            	String gname = rs.getString("gname");
	            	int orderId = rs.getInt("order_id");
	                result+=",{\"id\":"+id
	                	+", \"gname\":\""+gname+"\""
	                	+", \"orderId\":"+orderId
	                	+"}";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(stat!=null) {
					try {
						stat.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			result = "["+(result.length()>=2 ? result.substring(1) : "")+"]";
		}
		res.getWriter().append(result);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
}
