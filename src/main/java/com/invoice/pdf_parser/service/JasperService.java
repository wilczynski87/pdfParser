package com.invoice.pdf_parser.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bouncycastle.jcajce.provider.asymmetric.ec.SignatureSpi.ecCVCDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.invoice.pdf_parser.data.InvoiceDto;

import lombok.Value;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class JasperService {

	private String reportPath = "src.main.resources.InvoiceComplete.jrxml";
    InvoiceDto invoice;

	public String generateReport() throws JRException, FileNotFoundException {
		
		invoice = new InvoiceDto();

		try {
			File file = ResourceUtils.getFile("classpath:employee-rpt.jrxml");
			InputStream input = new FileInputStream(file);
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			// Get your data source
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource((Collection<?>) invoice);
			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "JavaHelper.org");
			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Empployee.pdf");
			System.out.println("PDF File Generated !!");
		} catch (RuntimeException e) {
            System.out.println("Exceptuin " + e);
        }
        return reportPath;
	}
    
}
