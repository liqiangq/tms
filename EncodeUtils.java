package com.thtf.ezone.ezmap.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thtf.ezone.ezmap.Constant;

public class EncodeUtils {

	public static void main(String[] args) {
		System.out.println("222"+getEncode("114,35"));
	}
	/**
	 * 加密经纬度
	 * @param latAndLong
	 * @return
	 */
	public static String getEncode(String latAndLong) {
		String url = Constant.MAP_ENCODE_URL+"?latlon=" +latAndLong+"&customer=1";
		try {
			URL urlstr = new URL(url);
			URLConnection conn = urlstr.openConnection();
			conn.setConnectTimeout(5000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;			
			StringBuffer content=new StringBuffer("");
			while ((line= reader.readLine()) != null) {
				content.append(line);
			}
			//System.out.println(content.toString());
			if (content.length()>0){
				return  encode(content.toString());
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	// - <result>
	// - <pois>
	// - <item id="0">
	// <latlon>GFJWDCXUWIGRIJ</latlon>
	// </item>
	// </pois>
	// - <mapinfo>
	// <center>GFJWDCXUWIGRIJ</center>
	// <scale>13</scale>
	// </mapinfo>
	// </result>
	public static String encode(String xml) {
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			if (root!=null){
				Element poisElement = root.element("pois");
				if (poisElement != null) {
					List itemElements = poisElement.elements("item");
					if (itemElements.size() > 0) {
						Element itemElement = (Element) itemElements.get(0);
						if (itemElement != null) {
							Element latlonElement = itemElement.element("latlon");
							if (latlonElement!=null){
								return latlonElement.getText();
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return "";
	}
}
