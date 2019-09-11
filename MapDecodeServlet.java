package com.thtf.ezone.ezmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 对url进行解码,防止中文字符
 */
public class MapDecodeServlet extends HttpServlet {

	private static final long serialVersionUID = 6886919042301026672L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");

		String url = this.getInitParameter("url") + "?latlon=" + p + "&customer=1";
		String rtnMsg = "";
		try {
			URL urlstr = new URL(url);
			URLConnection conn = urlstr.openConnection();
			conn.setConnectTimeout(5000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				rtnMsg += line;
				line = reader.readLine();
			}
			// System.out.println(rtnMsg);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		rtnMsg = rtnMsg.trim();
		response.setContentType("text/plain");
		ServletOutputStream outStream = response.getOutputStream();
		outStream.write(rtnMsg.getBytes());
		outStream.flush();
		try {
			outStream.close();
		} catch (Exception ex) {

		}

	}
//	<?xml version="1.0" encoding="UTF-8" ?>
//	<result>    
//	<pois>     
//	<item id='0'>
//	<lat>41.34666</lat>
//	<lon>101.0</lon>
//	</item>        
//	</pois>  
//	<mapinfo>   
//	<center>FSCIUAVUJDECRC</center> 
//	<scale>13</scale> 
//	</mapinfo></result>
	public String decode(String xml) {
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
							Element latElement = itemElement.element("lat");
							Element lonElement = itemElement.element("lon");	
							if (latElement!=null&&lonElement!=null){
								String latlon=latElement.getText()+","+lonElement.getText();
								return latlon;
							}
						}
					}
				}
			}
		} catch (DocumentException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}
}