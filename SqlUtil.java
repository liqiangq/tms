package com.thtf.ezone.ezmap.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL工具类
 */
public class SqlUtil {

	public static void closeQuietly(Connection con){
		if (con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				
			}
		}
	}
	
	public static void closeQuietly(Statement st){
		if (st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				
			}
		}
	}
	
	public static void closeQuietly(ResultSet rs){
		if (rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				
			}
		}
	}
}
