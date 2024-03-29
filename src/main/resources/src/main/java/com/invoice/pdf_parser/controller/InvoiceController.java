package com.invoice.pdf_parser.controller;

import java.io.FileNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.invoice.pdf_parser.data.EmailDetails;
import com.invoice.pdf_parser.data.InvoiceDto;
import com.invoice.pdf_parser.service.EmailService;
import com.invoice.pdf_parser.service.InvoiceService;
import com.invoice.pdf_parser.service.JasperService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
public class InvoiceController {

  @Autowired
  InvoiceService invoiceService;

  @Autowired
  JasperService jasperService;

  @Autowired
  EmailService emailService;

  private record InvoiceNip(String nip, Long month, Long year) {
  }

  public List<InvoiceDto> getInvoice(String nip, Long month, Long year) {

    // log.info("get Invoice by NIP: {}", nip);

    final String uri= "http://localhost:8081/getInvoices";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<InvoiceNip> entity = new HttpEntity<>(new InvoiceNip(nip, invoiceService.getCurrentMonth(month), invoiceService.getCurrentYear(year)), headers);

    ResponseEntity<List<InvoiceDto>> response = restTemplate.exchange(
      uri, HttpMethod.POST, entity, new ParameterizedTypeReference<List<InvoiceDto>>(){});

    List<InvoiceDto> invoiceList = response.getBody();

    // log.info("Exiting getting invoices");
    return invoiceList;
  }

  @GetMapping("getInvoices/{nip}")
  public ResponseEntity<byte[]> requestInvoices(@PathVariable("nip") String nip) {
    var invoice = invoiceService.chooseInvoice(getInvoice(nip, invoiceService.getCurrentMonth(null), invoiceService.getCurrentYear(null)));
    try {

      byte[] data = jasperService.generateReport(invoice);

      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=people.pdf");

      return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(data);

    } catch (FileNotFoundException | JRException e) {
      e.printStackTrace();
    }
    // log.error("Request success, invoice nr: {}", invoice.getNumber());
    return  null;
  }

  @GetMapping("/getInvoiceById/{id}")
  public ResponseEntity<InvoiceDto> requestInvoiceById(@PathVariable("id") Long id) {
    // log.info(String.valueOf( "id: " + id));

    String resourceUrl = "http://localhost:8081/getInvoiceById/" + id;

    RestTemplate restTemplate = new RestTemplate();

    // Fetch response as List wrapped in ResponseEntity
    ResponseEntity<InvoiceDto> response = restTemplate.getForEntity(resourceUrl, InvoiceDto.class);
    
    InvoiceDto invoiceDto = response.getBody();

    return new ResponseEntity<>(invoiceDto, HttpStatus.OK) ;
  }

  @GetMapping("/getInvoicesCount/{month}/{year}")
  @ResponseBody
  public String countInvoices(@PathVariable String month, @PathVariable String year) {
      String result = getInvoiceCounter(month, year);
      return " On month: " + month + ", year: " + year + " there was: " + result + " invoices.";
  }
  public String getInvoiceCounter(String month, String year) {
    // log.info("Starting getInvoiceCounter");

    final String uri= "http://localhost:8081/countInvoices/" + month + "/" + year;

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    
    
    return response.getBody();
  }

  // @PostMapping("/sendInvoice")
  // public ResponseEntity<String> sendInvoice(@RequestBody InvoiceDto invoiceDto) {
  //   try {
  //     // creating invoice
  //     byte[] data = jasperService.generateReport(invoiceDto);

  //     var response = data != null ? "Invoice created, " : "Fail to create Invoice";

  //     HttpHeaders headers = new HttpHeaders();
  //     headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=people.pdf");

  //     var emailDetails = new EmailDetails();
  //     emailDetails.setRecipient(invoiceDto.getClient().getEmail());
  //     emailDetails.setMsgBody("Witam \nPrzesyłam fakturkę w załączniku. \nPozdrawiam \nKarol Wilczyński");
  //     emailDetails.setSubject("Fakturka, plac przy Ostrowskiego 102");
  //     emailDetails.setAttachmentByte(data);
  //     var result = emailService.sendMailWithAttachment(emailDetails);

  //     // log.info(result);

  //     return new ResponseEntity<>(response + result, HttpStatus.OK);

  //   } catch (FileNotFoundException | JRException e) {
  //     e.printStackTrace();
  //     throw new RuntimeException("Mail have not been send, exception in " + e);
  //   }
  // }

  @PostMapping("/sendInvoice")
  public ResponseEntity<Byte[]> sendInvoice(@RequestBody InvoiceDto invoiceDto) {
    try {
      // creating invoice
      // byte[] data = jasperService.generateReportToOutputStream(invoiceDto);
      var data = jasperService.generateReportToOutputStream(invoiceDto);

      var response = data != null ? "Invoice created, " : "Fail to create Invoice";

      HttpHeaders headers = new HttpHeaders();
      headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=people.pdf");

      var emailDetails = new EmailDetails();
      emailDetails.setRecipient(invoiceDto.getClient().getEmail());
      emailDetails.setMsgBody("Witam \nPrzesyłam fakturkę w załączniku. \nPozdrawiam \nKarol Wilczyński");
      emailDetails.setSubject("Fakturka, plac przy Ostrowskiego 102");
      // emailDetails.setAttachmentByte(data);
      // var result = emailService.sendMailWithAttachment(emailDetails);

      // log.info(result);

      return new ResponseEntity(data, HttpStatus.OK);

    } catch (FileNotFoundException | JRException e) {
      e.printStackTrace();
      throw new RuntimeException("Mail have not been send, exception in " + e);
    }
  }
    
}
