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
 * 对url进行编码,防止中文字符
 */
public class MapEncodeServlet extends HttpServlet {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	private static final long serialVersionUID = 7546246883808812249L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p = request.getParameter("p");

		String url = this.getInitParameter("url") + "?latlon=" + p + "&customer=1";
		String rtnMsg = "";
		try {
			URL urlstr = new URL(url);
			URLConnection conn = urlstr.openConnection();
			conn.setConnectTimeout(5000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;

			StringBuffer content = new StringBuffer("");
			while ((line = reader.readLine()) != null) {
				content.append(line);
			}
			if (content.length() > 0) {
				rtnMsg = encode(content.toString());
			}
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
	public String encode(String xml) {
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
			System.err.println(e.getMessage());
		}
		return "";
	}

}