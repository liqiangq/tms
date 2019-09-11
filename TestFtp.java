package com.thtf.ezone.ezmap.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.thtf.ezone.ezmap.Constant;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

public class TestFtp {

	FtpClient ftpClient;

	private String host;

	private int port;

	private String user;

	private String pwd;

	private String localDirFile;

	private String serverDirFile;

	protected final Log log = LogFactory.getLog(this.getClass());

	public TestFtp() {

		this.host = Constant.FTP_DIR;
		this.port = 21;
		this.user = Constant.FTPUSENAME_DIR;
		this.pwd = Constant.FTPPASSWORD_DIR;
		this.serverDirFile = Constant.serverDirFile_DIR;
		ftpClient = new FtpClient();
	}

	public void uploadFiles(String filename, String webroot) {
		try {
			ftpClient.openServer(host, port);//打开ftp连接   
			ftpClient.login(user, pwd);//输入用户名密码   
			upload(this.serverDirFile + "/" + filename, webroot
					+ System.getProperty("file.separator")  + filename);
			ftpClient.closeServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void upload(String serverName, String loacalName) {
		try {
			TelnetOutputStream os = ftpClient.put(serverName);
			InputStream is = new FileInputStream(loacalName);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			is.close();
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
