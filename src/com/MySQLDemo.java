package com;
import java.sql.*;
 
public class MySQLDemo {
	
	// 注册 JDBC 驱动
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
    public static String query() {
    	String result = "";
        Connection conn = null;
        Statement stmt = null;
        try{
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(
            		"jdbc:mysql://localhost:3306/myweb1?characterEncoding=utf8",
            		"root",
            		"root123");
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            //sql = "select * from t1";
            sql = "SELECT t1.id, NAME, rst" + 
            		" FROM t1 " + 
            		" JOIN s1 ON t1.id=s1.id";
            
            sql = "select gid, gname, i.id, title, url " + 
            		" from web_group g" + 
            		" left join web_item i on g.id=i.gid" + 
            		" order by g.order_id, i.order_id";
            
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
				/* int id = rs.getInt("id"); 
				String name = rs.getString("name"); 
				int rst = rs.getInt("rst");
				
				// 输出数据 System.out.print("学号: " + id); System.out.print(", 姓名: " + name);
				System.out.print(", 成绩: " + rst); System.out.print("\n");
				
				result+=",{\"id\": "+id+", \"name\":\""+name+"\", \"rst\": "+rst+"}";
				*/
            	int gid = rs.getInt("gid");
            	String gname = rs.getString("gname");
            	int id = rs.getInt("id");
            	String title = rs.getString("title");
            	String url = rs.getString("url");
                result+=",{\"gid\":"+gid
                	+", \"gname\":\""+gname
                	+"\", \"id\":"+id
                	+", \"title\":\""+title
                	+"\", \"url\":\""+url+"\"}";
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
            
            return "["+result.substring(1)+"]";
        }
    }
    
    public static void main(String args[]) {
    	System.out.println(MySQLDemo.query());
    }
}