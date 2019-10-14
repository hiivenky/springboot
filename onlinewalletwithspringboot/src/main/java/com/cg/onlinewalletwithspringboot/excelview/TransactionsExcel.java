package com.cg.onlinewalletwithspringboot.excelview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.cg.onlinewalletwithspringboot.dto.TransactionHistory;


/**
 *author: Utkarsh
 *Description : This class creates the excel sheet and writes all the transactions to an excel sheet
 *              which can further be downloaded by the user  
 *created Date: 02/10/2019
 *last modified : 02/10/2019
 *Input : Map<String,Object> Object,Workbook Object,HttpServletResponse Object,HttpServlet Request Object
 *Output : downloaded excel file              
 */


public class TransactionsExcel extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		res.setHeader("Content-Disposition", "attachment;filename=\"transactions.xls\"");
		List<TransactionHistory> transactions = (List<TransactionHistory>) model.get("transactionList");
		 Sheet sheet = workbook.createSheet("Transaction History Data");
		 Row header = sheet.createRow(0);
		 header.createCell(0).setCellValue("Transaction ID");
		 header.createCell(1).setCellValue("Transaction amount");
		 header.createCell(2).setCellValue("Transaction description");
		 header.createCell(3).setCellValue("Transaction balance");
		 int rowNum = 1;
		 for(TransactionHistory transaction:transactions){
		 Row row = sheet.createRow(rowNum++);
		 row.createCell(0).setCellValue(transaction.getTransactionId());
		 row.createCell(1).setCellValue(transaction.getDateOfTransaction().toString());
		 row.createCell(2).setCellValue(transaction.getAmount());
		 row.createCell(3).setCellValue(transaction.getDescription());
		 row.createCell(4).setCellValue(transaction.getBalance());
		 }
		 
		 SheetConditionalFormatting sheetCF = sheet.getSheetConditionalFormatting();

	        // Condition 1: Formula Is   =A2=A1   (White Font)
	        ConditionalFormattingRule rule1 = sheetCF.createConditionalFormattingRule("COUNTIF($C$2:$C$4,A2)");
	        PatternFormatting fill1 = rule1.createPatternFormatting();
	        fill1.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.index);
	        fill1.setFillPattern(PatternFormatting.SOLID_FOREGROUND);
	   }
	}

