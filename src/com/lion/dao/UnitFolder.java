package com.lion.dao;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;

//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlOptions;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;

public class UnitFolder {
	
	public  void appendBody(String filePath, String appendfilePath,String outFilePth) throws Exception {
		
		InputStream in1 = null;
		InputStream in2 = null;
		OPCPackage src1Package = null;
		OPCPackage src2Package = null;

		OutputStream dest = new FileOutputStream(outFilePth);
		
		try {
			in1 = new FileInputStream(filePath);
			in2 = new FileInputStream(appendfilePath);
//			打开文档进行读取
			src1Package = OPCPackage.open(in1);
			src2Package = OPCPackage.open(in2);
//			转成CTBody
			XWPFDocument src1Document = new XWPFDocument(src1Package);
			CTBody src = src1Document.getDocument().getBody();
			XWPFDocument src2Document = new XWPFDocument(src2Package);
			CTBody append = src2Document.getDocument().getBody(); 
			
			XmlOptions optionsOuter = new XmlOptions();
			optionsOuter.setSaveOuter();
			
//			获取xml格式字符串
			String appendString = append.xmlText(optionsOuter);
			String srcString = src.xmlText();
//			对字符串进行拼装
			String prefix = srcString.substring(0,srcString.indexOf(">")+1);
			String mainPart = srcString.substring(srcString.indexOf(">")+1,srcString.lastIndexOf("<"));
			String sufix = srcString.substring( srcString.lastIndexOf("<") );
			String addPart = appendString.substring(appendString.indexOf(">") + 1, appendString.lastIndexOf("<"));
			
			CTBody makeBody = CTBody.Factory.parse(prefix+mainPart+addPart+sufix);
			src.set(makeBody);
			
			src1Document.write(dest);
		} catch (Exception e) {
			e.printStackTrace();	
		}
		
		
		
	}

}
