package me.daququ.common.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.StringTokenizer;

/**
 * ftp上传下载工具绿
 * @author daququ
 *
 */
public class FtpUtils {
	
	static Log logger = LogFactory.getLog(FtpUtils.class);
	
	FtpConfig ftpConfig =null;
	 
	public FtpUtils(FtpConfig ftpConfig) {
		this.ftpConfig = ftpConfig;
	}

	FTPClient ftpClient = null;
	
	/**
	 * 在当前目录判断文件是否存在，并且大小一致
	 * @param localFile
	 * @param remoteFile
	 * @return
	 * @throws java.io.IOException
	 */
	public boolean compareFileSize(File localFile, String remoteFile)
			throws IOException {
		boolean f = false;
		FTPFile[] rf = ftpClient.listFiles(remoteFile);
		if (rf.length == 1) {
			if (rf[0].isFile()) {
				if (localFile.length() == rf[0].getSize()) {
					f = true;
				}
			}
		} else {
			f = false;
		}
		return f;
	}
	public boolean connectServer() throws IOException{
		ftpClient = new FTPClient();
		  
		boolean flag = false;
		if(ftpConfig.isDebug()){
			ftpClient.addProtocolCommandListener(new ProtocolCommandListener() {
				public void protocolReplyReceived(ProtocolCommandEvent arg0) {
					logger.info("Received: "+arg0.getCommand()+" ->"+arg0.getMessage());
				}
				public void protocolCommandSent(ProtocolCommandEvent arg0) {
					logger.info("Send: "+arg0.getCommand()+" ->"+arg0.getMessage());
				}
			});	
		}
		
		try {
			FTPClientConfig conf  = new FTPClientConfig(FTPClientConfig.SYST_NT);
			conf.setServerLanguageCode("zh");  
 
			ftpClient.enterLocalPassiveMode();
			ftpClient.connect(ftpConfig.getIp(),ftpConfig.getPort());
			ftpClient.setControlEncoding(ftpConfig.getEncoding());
			ftpClient.login(ftpConfig.getUsername(), ftpConfig.getPassword());
			//连接测试
			int reply = ftpClient.getReplyCode();
			ftpClient.setBufferSize(4096);
			ftpClient.setKeepAlive(false);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				logger.error("[connectServer]FTP isPositiveCompletion!");
				flag = false;
			}
			
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//			ftpClient.enterLocalPassiveMode();
			//连接配置
			
			flag = true;
			// logger.info("[connection]"+ftpConfig.getIp()+" connenct success!");
		} catch (Exception e) {
			logger.error("[upload]download file failed ！", e);
		} 
		
		return flag;
	}
	
	public boolean createDir(String dir) throws IOException{
		StringTokenizer s = new StringTokenizer(dir, "/"); // sign
		s.countTokens();
		String[] arr = StringUtils.split(dir,"/");
		String pathName = "";
		for(int i=0;i<arr.length;i++){
			if(StringUtils.isEmpty(arr[i]) || arr[i]==""){
				continue;
			}
			pathName = pathName +"/"+arr[i];
			 
			if(ftpClient.changeWorkingDirectory(pathName)){
				continue;
			} else {
				try {
					ftpClient.makeDirectory(pathName);
				} catch (Exception e) {
					e = null;
					return false;
				}
			}
		}
		ftpClient.changeWorkingDirectory("/");
		return true;
	}
	
	public boolean closeServer(){
	
		try {
			ftpClient.logout();
			ftpClient.disconnect();
		} catch (Exception e1) {
			 return false;
		}  
		return true;
	}
	
	public boolean isDirExist(String dir) throws IOException{
		return  ftpClient.changeWorkingDirectory(dir);
	}
	
	public boolean isFileExist(String dir,String file) throws IOException{
		boolean r = true;
		 r &= ftpClient.changeWorkingDirectory(dir);
		InputStream is = ftpClient.retrieveFileStream(file);
		 r &= is!=null?true:false;
		 return r;
	}
	public boolean cd(String dir) throws IOException{
		return ftpClient.changeWorkingDirectory(dir);
	}
	
	public boolean uploadFile(InputStream localFile,String remoteFile) throws IOException{
		return ftpClient.storeFile(remoteFile, localFile);
	}
	
	/** 
     * 删除当前目录下的的文件 
     * @param dir :文件在FTP中的路径 
     * @param fileName :文件的名称 
     * @return boolean:删除是否成功 
	 * @throws java.io.IOException
     */  
	public boolean deleteFile(String dir, String fileName) throws IOException {  
		boolean bool = false;
		cd(dir);
		 
		FTPFile[] ftpFiles = ftpClient.listFiles();
		for (int i = 0; i < ftpFiles.length; i++) {
			if (fileName.equals(ftpFiles[i].getName())) {
				bool = ftpClient.deleteFile(fileName);
			}
		}
		return bool;  
    }  
	
	/**
	 * 列出指定目录下文件列表
	 * @param dir
	 * @return
	 * @throws java.io.IOException
	 */
	public FTPFile[]  listFile(String dir) throws IOException{
		cd(dir);
		return ftpClient.listFiles();
	}
	
	
	public FTPFile[] listFile(String dir,FTPFileFilter filler) throws IOException{
	
		return ftpClient.listFiles(dir,filler);
	}
	
	public boolean download(String file,OutputStream localOut) throws IOException{
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		return ftpClient.retrieveFile(file,localOut);
	}
	public static class FtpConfig {
		String ip;
		int port;
		String username;
		String password;
		String encoding;
		boolean isDebug=false;

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
 

		public String getEncoding() {
			return encoding;
		}

		public void setEncoding(String encoding) {
			this.encoding = encoding;
		}

		public boolean isDebug() {
			return isDebug;
		}

		public void setDebug(boolean isDebug) {
			this.isDebug = isDebug;
		}
	}
	public static void main(String[] args) {
		String c =" a ";
		System.err.println(c.trim().length());
	}
}
