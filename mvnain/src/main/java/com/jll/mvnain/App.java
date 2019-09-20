package com.jll.mvnain;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws Exception {
		System.out.println("******************** POI 生成带表格的 World! ********************");
		int row = 3;
		int cell = 5;
		
		if(args.length == 2) {
			row = Integer.parseInt(args[0]);
			cell = Integer.parseInt(args[1]);
		}
		createSimpleTable(row, cell);
	}
	
	 public static void createSimpleTable(int rows, int cells) throws Exception {
	        try (XWPFDocument doc = new XWPFDocument()) {
	            XWPFTable table = doc.createTable(rows, cells);
	            
	            table.setInsideHBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            table.setInsideVBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            table.setLeftBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            table.setRightBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            table.setTopBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            table.setBottomBorder(XWPFBorderType.NONE, 0, 0, "ffffff");
	            
	            table.setWidth("100%");
	            
	            CTTblPr tblPr = table.getCTTbl().getTblPr();
	            CTString styleStr = tblPr.addNewTblStyle();
	            styleStr.setVal("StyledTable");
	            
//	            tblPr.setTblBorders(arg0);
//	            CTTblBorders border = tblPr.addNewTblBorders();
//	            border.addNewLeft().setSpace(new BigInteger("0"));
//	            border.addNewRight().setSpace(new BigInteger("0"));
//	            border.addNewTop().setSpace(new BigInteger("0"));
//	            border.addNewBottom().setSpace(new BigInteger("0"));
	            
	            for(int i = 0; i < rows; i++) {
	            	XWPFTableRow row = table.getRow(i);
	            	CTTrPr trPr = row.getCtRow().addNewTrPr();
	            	CTHeight ht = trPr.addNewTrHeight();
	                ht.setVal(BigInteger.valueOf(1000));
	                
	            	
	            	for(int j = 0; j < cells; j++) {
	            		XWPFTableCell cell = row.getCell(j);
	            		
	            		CTTcPr tcpr = cell.getCTTc().addNewTcPr();
	                    // set vertical alignment to "center"
	                    CTVerticalJc va = tcpr.addNewVAlign();
	                    va.setVal(STVerticalJc.BOTTOM);
	                    // create cell color element
//	                    CTShd ctshd = tcpr.addNewShd();
//	                    ctshd.setVal(STShd.CLEAR);
//	                    cell.setColor("FF0000");
	                    
	                    CTTcBorders cellBorders = tcpr.addNewTcBorders();
	                    cellBorders.addNewLeft().setVal(STBorder.NONE);
	                    cellBorders.addNewRight().setVal(STBorder.NONE);
	                    cellBorders.addNewTop().setVal(STBorder.NONE);
	                    cellBorders.addNewBottom().setVal(STBorder.NONE);
	                    
	                    cell.removeParagraph(0);	
	                    XWPFParagraph newPara = new XWPFParagraph(cell.getCTTc().addNewP(), cell);	
	                    XWPFRun run=newPara.createRun();	
	                                                                    
	                    newPara.setAlignment(ParagraphAlignment.CENTER); 
	                    run.getCTR().addNewRPr().addNewColor().setVal("FF0000");/**FF0000红色*/	
	                    run.setUnderline(UnderlinePatterns.THICK); 
	                    
	                    run.setText("Row " + i + " ,  Cell " + j);
//	            		cell.setText("Row " + i + " ,  Cell " + j);
	            	}
	            }
	            
	            // table cells have a list of paragraphs; there is an initial
	            // paragraph created when the cell is created. If you create a
	            // paragraph in the document to put in the cell, it will also
	            // appear in the document following the table, which is probably
	            // not the desired result.
//	            XWPFParagraph p1 = table.getRow(0).getCell(0).getParagraphs().get(0);
//
//	            XWPFRun r1 = p1.createRun();
//	            r1.setBold(true);
//	            r1.setText("The quick brown fox");
//	            r1.setItalic(true);
//	            r1.setFontFamily("Courier");
//	            r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
//	            r1.setTextPosition(100);

//	            table.getRow(2).getCell(2).setText("only text");

	            try (OutputStream out = new FileOutputStream("simpleTable.docx")) {
	                doc.write(out);
	            }
	        }
	    }
}
