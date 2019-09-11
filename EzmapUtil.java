package com.thtf.ezone.ezmap.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.apache.commons.lang.StringUtils;

import com.thtf.ezone.ezmap.Constant;
import com.thtf.ezone.ezmap.model.Cardevice;
import com.thtf.ezone.ezmap.util.gif.AnimatedGifEncoder;
import com.thtf.ezone.eztms.common.TmsUtil;
import com.thtf.ezone.eztms.util.StringUtil;

public class EzmapUtil {

	/**
	 * 根据航度获得方向
	 * 
	 * @param du
	 * @return
	 */
	public static String getDirection(String direction) {
		if (StringUtils.isEmpty(direction)) {
			return "";
		}
		String directionStr = "";
		int j = 0;
		for (; j < direction.length(); j++) {
			if (direction.charAt(j) != '0') {
				break;
			}
		}
		String newDirection = direction.substring(j);
		// System.out.println("Direction= "+direction+", newDirection="+newDirection);
		double i;
		try {
			i = Double.parseDouble(newDirection);
		} catch (NumberFormatException e) {
			i = 0.0;
		}

		// 正北方向
		if ((i >= 0 && i < 22.5) || (i <= 360 && i >= 337.5)) {
			directionStr = "正北";
		}

		// 东北
		else if (i >= 22.5 && i < 67.5) {
			directionStr = "东北";
		}

		else if (i >= 67.5 && i < 112.5) {
			directionStr = "正东";
		}

		else if (i >= 112.5 && i < 157.5) {
			directionStr = "东南";
		}

		else if (i >= 157.5 && i < 202.5) {
			directionStr = "正南";
		}

		else if (i >= 202.5 && i < 247.5) {
			directionStr = "西南";
		}

		else if (i >= 247.5 && i < 292.5) {
			directionStr = "正西";
		}

		else if (i >= 292.5 && i < 337.5) {
			directionStr = "西北";
		}
		return directionStr;
	}

	public static String getDirectionImage(String direction) {
		if (StringUtils.isEmpty(direction)) {
			return "";
		}
		String directionStr = "";
		int j = 0;
		for (; j < direction.length(); j++) {
			if (direction.charAt(j) != '0') {
				break;
			}
		}
		String newDirection = direction.substring(j);

		double i;
		try {
			i = Double.parseDouble(newDirection);
		} catch (NumberFormatException e) {
			i = 0.0;
		}

		// 正北方向
		if ((i >= 0 && i < 22.5) || (i <= 360 && i >= 337.5)) {
			directionStr = "North.gif";
		}

		// 东北
		else if (i >= 22.5 && i < 67.5) {
			directionStr = "Northeast.gif";
		}
		// 正东
		else if (i >= 67.5 && i < 112.5) {
			directionStr = "East.gif";
		}
		// 东南
		else if (i >= 112.5 && i < 157.5) {
			directionStr = "Southeast.gif";
		}
		// 正南
		else if (i >= 157.5 && i < 202.5) {
			directionStr = "South.gif";
		}
		// 西南
		else if (i >= 202.5 && i < 247.5) {
			directionStr = "Southwest.gif";
		}
		// 正西
		else if (i >= 247.5 && i < 292.5) {
			directionStr = "West.gif";
		}
		// 西北
		else if (i >= 292.5 && i < 337.5) {
			directionStr = "Northwest.gif";
		}
		String directionStrUrl = "/tms/images/map/" + directionStr;
		return directionStrUrl;
	}

	public static String getDirectionImage1(String direction) {
		if (StringUtils.isEmpty(direction)) {
			return "";
		}
		String directionStr = "";
		int j = 0;
		for (; j < direction.length(); j++) {
			if (direction.charAt(j) != '0') {
				break;
			}
		}
		String newDirection = direction.substring(j);
		// System.out.println("Direction= "+direction+", newDirection="+newDirection);
		double i;
		try {
			i = Double.parseDouble(newDirection);
		} catch (NumberFormatException e) {
			i = 0.0;
		}

		// 正北方向
		if ((i >= 0 && i < 22.5) || (i <= 360 && i >= 337.5)) {
			directionStr = "North.gif";
		}

		// 东北
		else if (i >= 22.5 && i < 67.5) {
			directionStr = "Northeast.gif";
		}
		// 正东
		else if (i >= 67.5 && i < 112.5) {
			directionStr = "East.gif";
		}
		// 东南
		else if (i >= 112.5 && i < 157.5) {
			directionStr = "Southeast.gif";
		}
		// 正南
		else if (i >= 157.5 && i < 202.5) {
			directionStr = "South.gif";
		}
		// 西南
		else if (i >= 202.5 && i < 247.5) {
			directionStr = "Southwest.gif";
		}
		// 正西
		else if (i >= 247.5 && i < 292.5) {
			directionStr = "West.gif";
		}
		// 西北
		else if (i >= 292.5 && i < 337.5) {
			directionStr = "Northwest.gif";
		}
		String directionStrUrl = "/tms/images/person/" + directionStr;
		return directionStrUrl;
	}
	
	public static String getDirectionAlarmImage(String direction) {
		if (StringUtils.isEmpty(direction)) {
			return "";
		}
		String directionStr = "";
		int j = 0;
		for (; j < direction.length(); j++) {
			if (direction.charAt(j) != '0') {
				break;
			}
		}
		String newDirection = direction.substring(j);
		// System.out.println("Direction= "+direction+", newDirection="+newDirection);
		double i;
		try {
			i = Double.parseDouble(newDirection);
		} catch (NumberFormatException e) {
			i = 0.0;
		}

		// 正北方向
		if ((i >= 0 && i < 22.5) || (i <= 360 && i >= 337.5)) {
			directionStr = "North";
		}

		// 东北
		else if (i >= 22.5 && i < 67.5) {
			directionStr = "Northeast";
		}
		// 正东
		else if (i >= 67.5 && i < 112.5) {
			directionStr = "East";
		}
		// 东南
		else if (i >= 112.5 && i < 157.5) {
			directionStr = "Southeast";
		}
		// 正南
		else if (i >= 157.5 && i < 202.5) {
			directionStr = "South";
		}
		// 西南
		else if (i >= 202.5 && i < 247.5) {
			directionStr = "Southwest";
		}
		// 正西
		else if (i >= 247.5 && i < 292.5) {
			directionStr = "West";
		}
		// 西北
		else if (i >= 292.5 && i < 337.5) {
			directionStr = "Northwest";
		}
		return directionStr;
	}

	public static String createImage(String companyName, String serial,
			String car, Color color) {
		
		String serialfilename = null;
		if (color.getRGB() == -16776961) {
			serialfilename = serial + "_BLUE.jpg";
		} else if (color.getRGB() == -16777216) {
			serialfilename = serial + "_BLACK.jpg";
		} else if (color.getRGB() == -65536) {
			serialfilename = serial + "_RED.jpg";
		} else {
			serialfilename = serial + "_RED.jpg";
		}
		String dirName = TmsUtil.getWebBasePath() + Constant.MAP_IMAGE_DIR
				+ companyName + File.separator;
		dirName = dirName.replace("/", File.separator);

		String imageUrl = Constant.MAP_IMAGE_DIR.replace(File.separator, "/")
				+ companyName + "/" + serialfilename;
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imagefile = new File(dir, serialfilename);
		
		/*
		if (imagefile.exists()) {
			return imageUrl;
		}
		*/

		int width = 80;
		int height = 20;
		int fontsize = 12;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();

		System.out.println("please number 1");
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width - 1, height - 1);
		g.setColor(color);
		g.drawRect(0, 0, width - 1, height - 1);
		g.setColor(Color.YELLOW);
		g.fillOval(3, 3, height - 6, height - 6);
		g.setColor(color);
		g.drawOval(3, 3, height - 6, height - 6);
		g.fillOval(5, 5, height - 10, height - 10);

		g.drawLine(width / 2, 0, width / 2, 5);
		g.drawLine(width / 2 - 2, 3, width / 2, 5);
		g.drawLine(width / 2 + 2, 3, width / 2, 5);

		g.setFont(new Font("宋体", Font.BOLD, fontsize));

		if (car == null)
			car = "";

		g.drawString(car, height, height - 4);
		try {

			imagefile.createNewFile();
			FileImageOutputStream output = new FileImageOutputStream(imagefile);
			ImageIO.write(image, "JPEG", output);
		} catch (Exception exc) {
			exc.printStackTrace();
			return "";
		}
		return imageUrl;
	}

	public static String createImage2(String companyName, Cardevice cardevice,
			Color color) {
		String serialfilename = null;
		String serial = cardevice.getSerial();
		String car = cardevice.getCarid();
		String direction=cardevice.getDirection();
		String directionname=getDirectionAlarmImage(direction);

		String alarmflag = getAlarmFlag(cardevice.getAlarmstates());

		if (color.getRGB() == -16776961) {
			serialfilename = serial + "_BLUE"+"_" + directionname+"_"+alarmflag + ".gif";
		} else if (color.getRGB() == -16777216) {
			serialfilename = serial + "_BLACK"+"_" +directionname+"_"+ alarmflag + ".gif";
		} else if (color.getRGB() == -65536) {
			serialfilename = serial + "_RED"+"_" + directionname+"_"+ alarmflag + ".gif";
		} else {
			serialfilename = serial + "_RED"+"_"+ directionname+"_"+ alarmflag + ".gif";
		}
		String dirName = TmsUtil.getWebBasePath() + Constant.MAP_IMAGE_DIR
				+ companyName + File.separator;
		dirName = dirName.replace("/", File.separator);

		String imageUrl = Constant.MAP_IMAGE_DIR.replace(File.separator, "/")
				+ companyName + "/" + serialfilename;
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File imagefile = new File(dir, serialfilename);
		
		/*  ruguo chuzai 
		if (imagefile.exists()) {
			return imageUrl;
		}
		*/
		try {
			createCardeviceImg(imagefile,direction, car, alarmflag, color);
		} catch (Exception exc) {
			exc.printStackTrace();
			return "";
		}
		

		return imageUrl;
	}

	private static void createCardeviceImg(File imagefile, String direction,String car,
			String alarmflag, Color color) throws Exception {
		try {
			// 指定Frame的文件
			AnimatedGifEncoder e = new AnimatedGifEncoder();
			OutputStream os = new FileOutputStream(imagefile); // 输出图片
			e.start(os);// 开始处理
			e.setQuality(15); // 设置图片质量
			e.setRepeat(0); // 设置循环
			e.setDelay(500); // 设置延迟时间

			String path3 = ""; // 动态图片地址
			if (StringUtil.isNotEmpty(alarmflag)) {
				BufferedImage im = pressImage(car,direction, true, color);
				e.addFrame(im);// 循环加入Frame
				im = pressImage(car,direction, false, color);
				e.addFrame(im);// 循环加入Frame
				e.finish();
			} else {
				BufferedImage im = pressImage(car,direction, false, color);
				e.addFrame(im);// 循环加入Frame
				e.finish();
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	private static String getAlarmFlag(String alarmstates) {
		if (StringUtil.isEmpty(alarmstates)) {
			return "";
		}
		return "_ALARM";
	}

	private static void writeAlarmImg(Graphics g,String direction, int x, int y) {
		
		String alarmimageurl =TmsUtil.getWebBasePath()+ "images"
		+ java.io.File.separator + "alarm" + java.io.File.separator
		+ getDirectionAlarmImage(direction)+".gif";
		alarmimageurl = alarmimageurl.replace("/", File.separator);

		File alarmimage = new File(alarmimageurl);
		Image alarm;
		try {
			alarm = ImageIO.read(alarmimage);
			int alarm_width = alarm.getWidth(null);
			int alarm_height = alarm.getHeight(null);
			g.drawImage(alarm, x - alarm_width, y - alarm_height, alarm_width,
					alarm_height, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void writeAlarmImg(Graphics g, int x, int y) {
		
		String alarmimageurl = TmsUtil.getWebBasePath()
				+ Constant.ALARM_IMAGE_FILE;
		alarmimageurl = alarmimageurl.replace("/", File.separator);

		File alarmimage = new File(alarmimageurl);
		Image alarm;
		try {
			alarm = ImageIO.read(alarmimage);
			int alarm_width = alarm.getWidth(null);
			int alarm_height = alarm.getHeight(null);
			g.drawImage(alarm, x - alarm_width, y - alarm_height, alarm_width,
					alarm_height, null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static BufferedImage pressImage(String car,String direction, boolean alarmflag,
			Color color) {
		try {
			// // 目标文件
			// Image src = ImageIO.read(targetFile);
			// int wideth = src.getWidth(null);
			// int height = src.getHeight(null);
			// BufferedImage image = new BufferedImage(wideth, height,
			// BufferedImage.TYPE_INT_RGB);
			// Graphics g = image.createGraphics();
			// g.drawImage(src, 0, 0, wideth, height, null);
			int width = 80;
			int height = 20;
			int fontsize = 12;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width - 1, height - 1);
			g.setColor(color);
			g.drawRect(0, 0, width - 1, height - 1);

			
			if (alarmflag) {
				writeAlarmImg(g, height - 2, height - 2);
			} else{
				writeAlarmImg(g,direction, height - 2, height - 2);
			}
			// g.drawOval(3, 3, height - 6, height - 6);
			// g.fillOval(5, 5, height - 10, height - 10);
			g.setColor(color);
			g.drawLine(width / 2, 0, width / 2, 5);
			g.drawLine(width / 2 - 2, 3, width / 2, 5);
			g.drawLine(width / 2 + 2, 3, width / 2, 5);

			g.setFont(new Font("宋体", Font.BOLD, fontsize));

			if (car == null)
				car = "";

			g.drawString(car, height, height - 4);
			g.dispose();
			return image;

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}

}
