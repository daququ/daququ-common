package me.daququ.common.core.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {
	
	public static String getImageType(InputStream file) throws IOException{
		String format = null;
	    ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream(file);
		    Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		    if(iter.hasNext()){
			    ImageReader reader = iter.next();
			    
			    format =  reader.getFormatName().toLowerCase();
		    }
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				iis.close();
			} catch (IOException e) {}
		}
		return format;
	}
	
	public static class  ImgMeta{
		int width;
		int height;
		String format;
		public int getWidth() {
			return width;
		}
		public void setWidth(int width) {
			this.width = width;
		}
		public int getHeight() {
			return height;
		}
		public void setHeight(int height) {
			this.height = height;
		}
		public String getFormat() {
			return format;
		}
		public void setFormat(String format) {
			this.format = format;
		}
	}
	
	
	public static ImgMeta getImageMetaInfo(InputStream file) throws IOException{
		ImgMeta imgMeta = null;
		String format = null;
	    ImageInputStream iis = null;
	    BufferedImage bImage =  null;
		try {
			imgMeta = new ImgMeta();
			iis = ImageIO.createImageInputStream(file);
		    Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		    if(iter.hasNext()){
			    ImageReader reader = iter.next();
			    format =  reader.getFormatName().toLowerCase();
		    }
		      bImage = ImageIO.read(iis);
		    imgMeta.setFormat(format.toLowerCase());
		    imgMeta.setWidth(bImage.getWidth());
		    imgMeta.setHeight(bImage.getHeight());
		  
		    
		} catch (IOException e) {
			imgMeta = null;
			throw new IOException(e);
		} finally {
			try {
				iis.close();
			} catch (IOException e) {}
		}
		return imgMeta;
	}
	
	
	public static String getImageType(byte[] file) throws IOException{
		String format = null;
	    ImageInputStream iis = null;
		try {
			iis = ImageIO.createImageInputStream( new ByteArrayInputStream(file));
			
		    Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		    if(iter.hasNext()){
			    ImageReader reader = iter.next();
			    format =  reader.getFormatName().toLowerCase();
		    }
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				iis.close();
			} catch (IOException e) {}
		}
		return format;
	}
	
	public static String getImgType(byte[] file,FileMete fs) throws IOException{
		String format = null;
	    ImageInputStream iis = null;
		try {
			
			iis = ImageIO.createImageInputStream( new ByteArrayInputStream(file));
		    Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
		    if(iter.hasNext()){
			    ImageReader reader = iter.next();
			    format =  reader.getFormatName().toLowerCase();
			    reader.setInput(iis,true);
		    }
		    BufferedImage bi = ImageIO.read(iis);
		    fs.setImageHeight(bi.getHeight());
		    fs.setImageWidth(bi.getWidth());
		   
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				iis.close();
			} catch (IOException e) {}
		}
		return format;
	}

}
