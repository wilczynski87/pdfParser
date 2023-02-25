package com.invoice.pdf_parser.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.invoice.pdf_parser.data.InvoiceDto;
import com.invoice.pdf_parser.service.InvoiceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InvoiceController {

  @Autowired
  InvoiceService invoiceService;

  private record invoiceNip(String nip, Long month, Long year) {
  }

  public List<InvoiceDto> getInvoice(String nip) {
    log.info("get Invoice by NIP ");

    final String uri= "http://localhost:8081/getInvoices";

    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<invoiceNip> entity = new HttpEntity<>(new invoiceNip("1234567890", 2l, 2023l), headers);

    ResponseEntity<List<InvoiceDto>> response = restTemplate.exchange(
      uri, HttpMethod.POST, entity, new ParameterizedTypeReference<List<InvoiceDto>>(){});

    List<InvoiceDto> invoiceList = response.getBody();

    log.info("Exiting getting invoices");
    return invoiceList;
  }

  @GetMapping("getInvoices/{nip}")
  public String requestInvoices(@PathVariable("nip") String nip) {
    var invoice = invoiceService.chooseInvoice(getInvoice(nip));
    return "Request success, invoice nr: " + invoice.getNumber();
  }

  @GetMapping("/getInvoiceById/{id}")
  public ResponseEntity<InvoiceDto> requestInvoiceById(@PathVariable("id") Long id) {
    System.out.println(String.valueOf( "id: " + id));

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
    log.info("Starting getInvoiceCounter");

    final String uri= "http://localhost:8081/countInvoices/" + month + "/" + year;

    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response
      = restTemplate.getForEntity(uri, String.class);
    
    String productsJson = response.getBody();
    
    return productsJson;
  }
    
}
