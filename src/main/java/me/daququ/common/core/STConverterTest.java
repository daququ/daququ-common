package me.daququ.common.core;

import org.junit.Test;

import junit.framework.Assert;

/**
 * 
 * @description  
 * @version 1.0
 * @author daququ
 * @date 2016年5月11日
 */
public class STConverterTest {

	@Test
	public void testConvertSTConvertTypeString() {
	  Assert.assertNotNull(STConverter.getInstance().convert(STConverter.STConvertType.T2S, "北京國際電視檯"));
	}

	@Test
	public void testConvertStringSTConvertType() {
		 Assert.assertNotNull(STConverter.getInstance().convert(STConverter.STConvertType.S2T, "北京国际电视台"));
	}

}
