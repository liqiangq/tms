package com.thtf.ezone.ezmap.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.thtf.ezone.security.util.BeanUtil;

public class GetCarCityJDBC {
	
	private Connection getConnection() throws SQLException{
		DataSource ds = (DataSource) BeanUtil.getBean("dataSource-eztms-ezmap");
		return ds.getConnection();		
	}	

	public String getCarCityJDBC(String serial) throws SQLException {
		Connection con=null;
		Statement st=null;
		ResultSet rs = null;
		String city=null;
		try {
			con=getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select city from cardevice where serial='" + serial + "'");
			while (rs.next()) {
				city = rs.getString("city");
				//System.out.println("city in jdbc= " + city);
			}
		}finally {
			SqlUtil.closeQuietly(rs);
			SqlUtil.closeQuietly(st);
			SqlUtil.closeQuietly(con);
		}
		return city;
	}

	public String getCarLatLonJDBC(String serial) throws SQLException {
		Connection con=null;
		Statement st=null;
		ResultSet rs = null;
		String latlon=null;
		try {
			con=getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select lat,lon from cardevice where serial='" + serial + "'");
			while (rs.next()) {
				latlon = rs.getString("lat") + "," + rs.getString("lon");
			}
		} finally {
			SqlUtil.closeQuietly(rs);
			SqlUtil.closeQuietly(st);
			SqlUtil.closeQuietly(con);
		}
		return latlon;

	}

}
