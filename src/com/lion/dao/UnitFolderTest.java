package com.lion.dao;

import org.junit.Before;
import org.junit.Test;

public class UnitFolderTest {
	private static UnitFolder unitFolder = new UnitFolder();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAppendBody() throws Exception{
		
		String filePath="D:\\����\\a3.doc";
		String appendfilePath="D:\\����\\a.doc";
		String outFilePth="D:\\����\\file.doc";

		unitFolder.appendBody(filePath, appendfilePath, outFilePth);
		System.out.println("�ɹ���������");
		
	}

}
