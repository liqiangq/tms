package com.thtf.ezone.ezmap.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class ExcelUtil {

	public static void main(String[] args) {
		File testFile=new File("D:\\test.xls");
		List<String[]> list =new ArrayList<String[]>();
		list.add(new String[]{"2323","3dsfdsf","测试"});
		try {
			writeExcel("测试",list,testFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 功能说明: 向excel中写入数据
	 * @param sheetName
	 * @param list
	 * @param file
	 * @throws IOException
	 * 2008-6-23
	 */
	public static void writeExcel(String sheetName,List<String[]> list, File file) throws IOException {
		Assert.notNull(file, "file must not be null.");
		FileOutputStream fout = null;
		try {
			if (!file.exists()){
				file.createNewFile();
			}
			fout = new FileOutputStream(file);
			writeExcel(sheetName, list,fout);
		} finally {
			IOUtil.closeQuietly(fout);
		}
	}
	
	/**
	 * 
	 * 功能说明: 向excel中写入数据
	 * @param sheetName
	 * @param list
	 * @param out
	 * @throws IOException
	 * 2008-6-23
	 */
	public static void writeExcel(String sheetName,List<String[]> list, OutputStream out) throws IOException {
		Assert.hasText(sheetName, "sheetName must not be null.");
		Assert.notNull(list, "list must not be null.");
		Assert.notNull(out, "out must not be null.");
		WritableSheet ws = null;
		WritableWorkbook wwb = null;
		try {
			WorkbookSettings settings=new WorkbookSettings();
			settings.setEncoding("UTF-8");
			wwb = Workbook.createWorkbook(out,settings);
			ws = wwb.createSheet(sheetName, 0);
			for (int i = 0; i < list.size(); i++) {
				String[] s = (String[]) list.get(i);
				if (s != null) {
					for (int j = 0; j < s.length; j++) {
						if (!StringUtils.isEmpty(s[j])){
							Label label = new Label(j, i,s[j]);
							ws.setColumnView(j, 20);
							ws.addCell(label);
						}
					}
				}
			}
			wwb.write();
			// ExcelCtrl.createAssetExcel(recordList,ctx);
		} catch (RowsExceededException e) {
			throw new IOException("Could not write excel, error: "+e.getMessage());
		} catch (WriteException e) {
			throw new IOException("Could not write excel, error: "+e.getMessage());
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * 功能说明: 向客户端写入excel文件
	 * 
	 * @param response
	 * @param file
	 * @throws IOException
	 *             2008-6-23
	 */
	public static void writeExcelToResponse(HttpServletResponse response, File file) throws IOException {
		Assert.notNull(file, "file must not be null.");
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(file);
			writeExcelToResponse(response, fin, file.getName());
		} finally {
			IOUtil.closeQuietly(fin);
		}
	}

	/**
	 * 
	 * 功能说明: 向客户端写入excel流
	 * 
	 * @param response
	 * @param inputStream
	 * @param fileName
	 * @throws IOException
	 *             2008-6-23
	 */
	public static void writeExcelToResponse(HttpServletResponse response, InputStream inputStream, String fileName)
			throws IOException {
		// 向客户端写文件流
		Assert.notNull(inputStream, "inputStream must not be null.");
		Assert.hasText(fileName, "fileName must not be empty.");
		ServletOutputStream outx = null;
		try {
			outx = response.getOutputStream();
			response.setContentType("application/msexcel;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			byte[] tmp = new byte[1024];
			int nr;
			while ((nr = inputStream.read(tmp)) > -1) {
				outx.write(tmp, 0, nr);
			}
		} finally {
			IOUtil.closeQuietly(outx);
		}
	}
}
