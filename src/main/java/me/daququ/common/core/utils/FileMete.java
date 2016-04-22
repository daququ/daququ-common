package me.daququ.common.core.utils;

import java.util.Date;
public  class FileMete{
	     
		String fileRoot;
		String filePath;
		String fileName;
		String httpUrl;
		Date createDate;
		int	 imageWidth;
		int imageHeight;
		int id;
		

		public int getImageWidth() {
			return imageWidth;
		}

		public void setImageWidth(int imageWidth) {
			this.imageWidth = imageWidth;
		}

		public int getImageHeight() {
			return imageHeight;
		}

		public void setImageHeight(int imageHeight) {
			this.imageHeight = imageHeight;
		}
 
 

 
		public String getAbsoleDir(){
			return fileRoot+filePath;
		}
		
		public String getAbsolePath(){
			return fileRoot+filePath+fileName;
		}
		
		public String getRelativePath(){
			return filePath+fileName;
		}
		
		public String getFileRoot() {
			return fileRoot;
		}
		public void setFileRoot(String fileRoot) {
			this.fileRoot = fileRoot;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getHttpUrl() {
			return httpUrl;
		}

		public void setHttpUrl(String httpUrl) {
			this.httpUrl = httpUrl;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	 
		
	}