package com.invoice.pdf_parser.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.invoice.pdf_parser.data.InvoiceDto;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;

@Service
public class JasperService {

	private String reportPath = "C:\\Users\\wilcz\\OneDrive\\Documents\\VCS Projects\\pdfParser\\src\\main\\resources";

	public byte[] generateReport(InvoiceDto invoiceDto) throws JRException, FileNotFoundException {

		try {
			File file = ResourceUtils.getFile("classpath:InvoiceComplete.jrxml");
			InputStream input = new FileInputStream(file);
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			JRSaver.saveObject(jasperReport, "invoice.jasper"); // baeldung
			// Get your data source
			// JREmptyDataSource source = new JREmptyDataSource();
			List<InvoiceDto> invoiceList = new ArrayList<>();
			invoiceList.add(invoiceDto);
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(invoiceList);
			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "JavaHelper.org");
			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Empployee.pdf");

			System.out.println("PDF File Generated !!");
			// from working mail
			// JasperExportManager.exportReportToPdfFile(jasperPrint, filePath + fileName + fileFormatPDF);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (RuntimeException e) {
            System.out.println("Exceptuin " + e);
        }
        return null;
	}
    
}
