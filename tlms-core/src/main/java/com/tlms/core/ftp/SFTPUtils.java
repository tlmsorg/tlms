package com.tlms.core.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtils {
	public static final Logger logger = Logger.getLogger(SFTPUtils.class);
	static private Session session = null;
	static private Channel channel = null;

	/**
	 * 获取ftp通道
	 * 160068 2018年8月14日 下午6:07:15
	 * @param username 用户名
	 * @param password 密码
	 * @param ip ftp服务器地址
	 * @param port 端口
	 * @param timeout 连接超时时间
	 * @return
	 * @throws JSchException
	 */
	public static ChannelSftp getChannel(String username, String password, String ip, Integer port, Integer timeout)
			throws JSchException {
		JSch jsch = new JSch();
		session = jsch.getSession(username, ip, port);
		System.out.println("Session created...");
		if (password != null) {
			session.setPassword(password); 
		}
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); 
		session.setTimeout(timeout); 
		session.connect(); 
		
		channel = session.openChannel("sftp");
		channel.connect();
		return (ChannelSftp) channel;
	}

	/**
	 * 关闭channel和session
	 * @throws Exception
	 */
	public static void closeChannel() throws Exception {
		if (channel != null) {
			channel.disconnect();
		}
		if (session != null) {
			session.disconnect();
		}
	}

	/**
	 * ftp文件上传
	 * 160068
	 * 2018年8月14日 下午6:10:18
	 * @param channelSftp 通道对象
	 * @param ftpServerDir ftp服务器目录
	 * @param ftpClientDir 本地目录
	 * @param fileName 本地待上传文件名
	 * @throws Exception
	 */
	public static void uploadFile(ChannelSftp channelSftp, String ftpServerDir, String ftpClientDir, String fileName)
			throws Exception {
		try {
			try {
				Vector dir = channelSftp.ls(ftpServerDir);
				if (dir == null) {
					channelSftp.mkdir(ftpServerDir);
				}
			} catch (SftpException e) {
				channelSftp.mkdir(ftpServerDir);
				e.printStackTrace();
			}
			File file = new File(ftpClientDir + File.separator + fileName);
			channelSftp.put(new FileInputStream(file), ftpServerDir + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (channelSftp != null)
				channelSftp.quit();
			try {
				SFTPUtils.closeChannel();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * ftp文件下载
	 * 160068
	 * 2018年8月14日 下午6:11:03
	 * @param channelSftp 通道对象
	 * @param ftpServerDir ftp服务器文件目录
	 * @param ftpClientDir 本地文件目录
	 * @param fileName ftp服务器待下载文件名
	 * @return
	 * @throws Exception
	 */
	public static byte[] downloadFile(ChannelSftp channelSftp, String ftpServerDir, String ftpClientDir,
			String fileName) throws Exception {
		byte[] fileByteArray = null;
		try {
			System.out.println(channelSftp);
			boolean isDownloadFileExist = false;
			Iterator fileIterator = channelSftp.ls(ftpServerDir).iterator();
			while (fileIterator.hasNext()) {
				LsEntry lsEntry = (LsEntry) fileIterator.next();
				System.out.println(lsEntry);
				String tempFileName = lsEntry.getFilename();
				if (fileName.equals(tempFileName)) {
					isDownloadFileExist = true;
				}
			}
			FileOutputStream fos = null; 
			if (isDownloadFileExist) {
				 fos = new FileOutputStream(new File(ftpClientDir +File.separator + fileName));
				channelSftp.get(ftpServerDir + fileName, fos);
			} else {
				logger.error("工行账单文件下载失败，服务器不存在文件："+fileName);
			}
			fos.close();
			channelSftp.quit();
			SFTPUtils.closeChannel();
			System.out.println("下载完成");
		} catch (Exception e) {
			throw e;
		} finally {
			if (channelSftp != null)
				channelSftp.quit();
			try {
				SFTPUtils.closeChannel();
			} catch (Exception e) {

			}
		}
		return fileByteArray;
	}

	public static void main(String[] args) throws Exception {
		String username = "pjadmin";
		String password = "Pjadmin123";
		String ip = "172.18.10.131";
		int port = 22;
		int timeout = 60000;

		// 假设参数值
		String fileName = "工行账单数据导入.xls";
		String ftpClientDir = "H:";
		String ftpServerDir = "/home/pjadmin/icbcFtp/";

		ChannelSftp channelSftp = SFTPUtils.getChannel(username, password, ip, port, timeout);
		 SFTPUtils.uploadFile(channelSftp, ftpServerDir, ftpClientDir, fileName);
//		byte[] fileByteArray = SFTPUtils.downloadFile(channelSftp, ftpServerDir, ftpClientDir, fileName);
		System.out.println("66666");
		SFTPUtils.closeChannel();
		
		System.out.println(channelSftp.isClosed());
	}

}
