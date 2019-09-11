package com.thtf.ezone.ezmap.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMark {
	/** *//**
     * 给图片添加水印
    * @param filePath 需要添加水印的图片的路径
    * @param markContent 水印的文字
    * @param markContentColor 水印文字的颜色
    * @param qualNum 图片质量
    * @param fontType 字体
    * @param fontsize 字体大小
    * @return
    * @author zhongweihai newwei2001@yahoo.com.cn
    */
   public boolean createMark(String filePath,String markContent,Color markContentColor,float qualNum,
                             String fontType,int fontSize)
   {
       ImageIcon imgIcon=new ImageIcon(filePath);
       Image theImg =imgIcon.getImage();
       int width=theImg.getWidth(null);
       int height= theImg.getHeight(null);
       BufferedImage bimage = new BufferedImage(1028,728, BufferedImage.TYPE_INT_RGB);
       Graphics2D g=bimage.createGraphics();
       g.setColor(markContentColor);
       g.setBackground(Color.white);
       g.drawImage(theImg, 0, 0, null );
       AttributedString ats = new AttributedString(markContent);
       Font f = new Font(fontType,Font.BOLD, fontSize);

       ats.addAttribute(TextAttribute.FONT, f, 0,markContent.length() );
       AttributedCharacterIterator iter = ats.getIterator();

       g.drawString(iter,width/5,height/5); //添加水印的文字和设置水印文字出现的内容
       g.dispose();

       try{
       FileOutputStream out=new FileOutputStream(filePath);
       JPEGImageEncoder encoder =JPEGCodec.createJPEGEncoder(out);
       JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
       param.setQuality(qualNum, true);
       encoder.encode(bimage, param);
       out.close();
       }catch(Exception e)
       { return false; }
       return true;
   }
}
