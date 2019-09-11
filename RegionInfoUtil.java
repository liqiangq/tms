package com.thtf.ezone.ezmap.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thtf.ezone.ezframework.adapter.struts.ActionContext;
import com.thtf.ezone.security.util.BeanUtil;

public class RegionInfoUtil {

	private static Log log=LogFactory.getLog(RegionInfoUtil.class);
	/**
	 * 获取所有省份
	 * @param ctx
	 * @return
	 */
	public static  Map<String,String> queryAllRegionInfos(ActionContext ctx) {
		Map<String,String> reginInfosmap = new LinkedHashMap<String,String>();		
		String sql = "select id,region_name from regioninfo where rank=0 order by region_name";

		DataSource ds = (DataSource) BeanUtil.getBean("dataSource-eztms-ezmap");
		if (ds==null){
			log.error("Could not found Bean [dataSource-eztms-ezmap] in spring context.");
			return reginInfosmap;
		}
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();			
			rs = (ResultSet) st.executeQuery(sql);			
			while (rs.next()) {
				reginInfosmap.put(rs.getString("id"), rs.getString("region_name"));
			}
			
		} catch (SQLException e) {
			log.error("Query all RegionInfos failed, error: " + e.getMessage(), e);
		} finally {
			SqlUtil.closeQuietly(rs);
			SqlUtil.closeQuietly(st);
			SqlUtil.closeQuietly(con);
		}
		return reginInfosmap;
	}

	/**
	 * 获取指定省份的所有城市
	 * @param ctx
	 * @param pid
	 * @return
	 */
	public static Map<String,String> queryAllCityInfos(ActionContext ctx, String pid) {
		Map<String,String> citymap = new LinkedHashMap<String,String>();		
		String sql = "select id,region_name from regioninfo where pid='" + pid + "' order by region_name";

		DataSource ds = (DataSource) BeanUtil.getBean("dataSource-eztms-ezmap");
		if (ds==null){
			log.error("Could not found Bean [dataSource-eztms-ezmap] in spring context.");
			return citymap;
		}
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			st = con.createStatement();			
			rs = (ResultSet) st.executeQuery(sql);			
			while (rs.next()) {
				citymap.put(rs.getString("id"), rs.getString("region_name"));
			}			
		} catch (SQLException e) {
			log.error("Query all CityInfos failed, error: " + e.getMessage(), e);
		} finally {
			SqlUtil.closeQuietly(rs);
			SqlUtil.closeQuietly(st);
			SqlUtil.closeQuietly(con);
		}
		return citymap;
	}
}
