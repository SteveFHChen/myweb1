package com;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WebManageServlet
 */
@WebServlet("/webmanage")
public class WebManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static String OPER_TYPE="operType";
	public static String OPER_TYPE_QUERY="operTypeQuery";
	public static String OPER_TYPE_ADD="operTypeAdd";
	public static String OPER_TYPE_UPDATE="operTypeUpdate";
	public static String OPER_TYPE_REMOVE="operTypeRemove";
	
	public static String OPER_LEVEL="operLevel";
	public static String OPER_LEVEL_GROUP="operLevelGroup";
	public static String OPER_LEVEL_ITEM="operLevelItem";
	
	public static String PARA_ID="id";
	public static String PARA_GID="gid";
	public static String PARA_NAME="name";
	public static String PARA_ORDERID="orderId";
	public static String PARA_URL="url";
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//res.getWriter().append("Served at: ").append(req.getContextPath());
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(req, res);
		String name; 
		PreparedStatement stat = null;
		
		req.setCharacterEncoding("utf-8");
		Enumeration<String> names = req.getParameterNames();
		while(names.hasMoreElements()) {
			name = names.nextElement();
			System.out.println(name + ": " + req.getParameter(name));
		}
		
		String operType=req.getParameter(OPER_TYPE);
		String operLevel=req.getParameter(OPER_LEVEL);
		if(OPER_TYPE_QUERY.equals(operType) && OPER_LEVEL_GROUP.equals(operLevel)) {
			/*
			 * try { String sql = "select * from web_group"; stat =
			 * MySQLDemo.conn.prepareStatement(sql); ResultSet rs = stat.executeQuery();
			 * 
			 * while(rs.next()) { int gid = rs.getInt("gid"); String gname =
			 * rs.getString("gname"); int id = rs.getInt("id"); String title =
			 * rs.getString("title"); String url = rs.getString("url");
			 * result+=",{\"gid\":"+gid +", \"gname\":\""+gname +"\", \"id\":"+id
			 * +", \"title\":\""+title +"\", \"url\":\""+url+"\"}"; }
			 * 
			 * System.out.println("Insert web group: "+n); } catch (SQLException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); } finally {
			 * if(stat!=null) { try { stat.close(); } catch (SQLException e) { // TODO
			 * Auto-generated catch block e.printStackTrace(); } } }
			 */
		}else if(OPER_TYPE_ADD.equals(operType) && OPER_LEVEL_GROUP.equals(operLevel)) {
			try {
				String sql = "insert into web_group(gname, order_id)"
						+ " values(?, ?)";
				stat = MySQLDemo.conn.prepareStatement(sql);
				//stat.setInt(1, Integer.valueOf(req.getParameter(PARA_ID)));
				stat.setString(1, req.getParameter(PARA_NAME));
				stat.setInt(2, Integer.valueOf(req.getParameter(PARA_ORDERID)));
				int n = stat.executeUpdate();
				if(!MySQLDemo.conn.getAutoCommit()) {
					MySQLDemo.conn.commit();
				}
				System.out.println("Insert web group: "+n);
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
			
		}else if(OPER_TYPE_ADD.equals(operType) && OPER_LEVEL_ITEM.equals(operLevel)) {
			try {
				String sql = "insert into web_item(gid, title, order_id, url)"
						+ " values(?, ?, ?, ?)";
				stat = MySQLDemo.conn.prepareStatement(sql);
				//stat.setInt(1, Integer.valueOf(req.getParameter(PARA_ID)));
				stat.setInt(1, Integer.valueOf(req.getParameter(PARA_GID)));
				stat.setString(2, req.getParameter(PARA_NAME));
				stat.setInt(3, Integer.valueOf(req.getParameter(PARA_ORDERID)));
				stat.setString(4, req.getParameter(PARA_URL));
				int n = stat.executeUpdate();
				if(!MySQLDemo.conn.getAutoCommit()) {
					MySQLDemo.conn.commit();
				}
				System.out.println("Insert web item: "+n);
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
		}
	}

}
