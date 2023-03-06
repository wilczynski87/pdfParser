package com.invoice.pdf_parser.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.jfree.util.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.invoice.pdf_parser.data.InvoiceDto;
import com.invoice.pdf_parser.pdf_mapper.PdfMapper;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@Slf4j
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
			var invoicePdf = PdfMapper.INSTANCE.invoiceDtoToInvoicePdf(invoiceDto);
			var productListPdf = invoiceDto.getProductsDto();
			var clientPdf = invoiceDto.getClient();
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(productListPdf);
			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("createdBy", "JavaHelper.org");
			parameters.put("invoice", invoicePdf);
			parameters.put("client", clientPdf);
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
        return new byte[0];
	}

	public byte[] generateReportToOutputStream(InvoiceDto invoiceDto) throws JRException, FileNotFoundException {

		try {
			File file = ResourceUtils.getFile("classpath:InvoiceComplete.jrxml");
			InputStream input = new FileInputStream(file);

			String fileSavePath = "src.main.resources.";
			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			JRSaver.saveObject(jasperReport, fileSavePath + "invoice.jasper"); // baeldung
			
			// Get your data source
			var invoicePdf = PdfMapper.INSTANCE.invoiceDtoToInvoicePdf(invoiceDto);
			var productListPdf = invoiceDto.getProductsDto();
			var clientPdf = invoiceDto.getClient();
			// Add source
			JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(productListPdf);
			// Add parameters
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("invoice", invoicePdf);
			parameters.put("client", clientPdf);
			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, source);
			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Empployee.pdf");

			// Exporting file
			// OutputStream outputStream = new ObjectOutputStream(null);
			// JRPdfExporter exporter = new JRPdfExporter();
			// exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			// exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			// SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
			// configuration.setMetadataAuthor("Petter");  //why not set some config as we like
			// exporter.setConfiguration(configuration);
			// exporter.exportReport();

			log.info("PDF File Generated");
			// from working mail
			// JasperExportManager.exportReportToPdfFile(jasperPrint, filePath + fileName + fileFormatPDF);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (RuntimeException e) {
            log.error("Exceptuin " + e);
        }
		return new byte[0];
	}
    
}
