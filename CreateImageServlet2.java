package com.thtf.ezone.ezmap;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thtf.ezone.ezmap.model.Cardevice;
import com.thtf.ezone.ezmap.service.ICardeviceService;
import com.thtf.ezone.eztms.util.StringUtil;
import com.thtf.ezone.security.util.BeanUtil;

/**
 * 生成车机图片
 */
public class CreateImageServlet2 extends HttpServlet {

	private static final long serialVersionUID = 6033552324466273706L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
		int fontsize = Integer.parseInt(request.getParameter("fontsize"));

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width - 1, height - 1);
		g.setColor(Color.BLUE);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.YELLOW);
		g.fillOval(3, 3, height - 6, height - 6);
		g.setColor(Color.BLUE);
		g.drawOval(3, 3, height - 6, height - 6);
		g.fillOval(5, 5, height - 10, height - 10);
		g.drawLine(width / 2, 0, width / 2, 5);
		g.drawLine(width / 2 - 2, 3, width / 2, 5);
		g.drawLine(width / 2 + 2, 3, width / 2, 5);

		g.setFont(new Font("����", Font.BOLD, fontsize));

		String car = request.getParameter("car");
		if (car == null)
			car = "";
		if (StringUtil.isEmpty(car)) {
			String serial = request.getParameter("serial");
			ICardeviceService service = (ICardeviceService) BeanUtil.getBean("cardeviceService");
			Cardevice device = service.loadCardeviceBySerial(serial);
			if (device != null)
				car = device.getCarid();
		}
		g.drawString(car, height, height - 4);

		// System.out.println("--jpg image--"+request.getParameter("car"));

		response.setContentType("image/jpeg");
		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		this.doGet(request, response);
	}
}
