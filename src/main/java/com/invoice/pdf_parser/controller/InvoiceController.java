package com.invoice.pdf_parser.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invoice.pdf_parser.data.BankAccountEnum;
import com.invoice.pdf_parser.data.ClientDto;
import com.invoice.pdf_parser.data.InvoiceDto;
import com.invoice.pdf_parser.data.ProductDto;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class InvoiceController {

    private record invoiceNip(String nip) {
    }
    private record Invoice(@JsonProperty("id") Long id,
            @JsonProperty("number") String number,
            @JsonProperty("date") LocalDate date,
            @JsonProperty("tax") BigDecimal tax,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("priceWithTax") BigDecimal priceWithTax,
            @JsonProperty("mainAccount") BankAccountEnum mainAccount,
            @JsonProperty("taxAccount") BankAccountEnum taxAccount,
            @JsonProperty("client") ClientDto client,
            @JsonProperty("productsDto") ProductDto product
            ){}

    public List<InvoiceDto> getInvoice(String nip) {
    log.info("Starting BLOCKING Controller!");

    final String uri= "http://localhost:8081/getInvoices";

    RestTemplate restTemplate = new RestTemplate();

    HttpEntity<invoiceNip> request = 
          new HttpEntity<>(
            new invoiceNip("1234567890"));

    ResponseEntity<List<InvoiceDto>> response = restTemplate.exchange(
      uri, HttpMethod.POST, request,
      new ParameterizedTypeReference<List<InvoiceDto>>(){});

    List<InvoiceDto> result = response.getBody();

    result.forEach(tweet -> log.info(tweet.toString()));

    log.info("Exiting BLOCKING Controller!");
    return result;
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

    @GetMapping("getInvoices/{nip}")
    public String requestInvoices(@PathVariable("nip") String nip) {
        getInvoice(nip);
        return "Request success";
    }

    @GetMapping("/getInvoiceById/{id}")
    public ResponseEntity<Invoice> requestInvoiceById(@PathVariable("id") Long id) {
        System.out.println(String.valueOf( "id: " + id));

        String resourceUrl = "http://localhost:8081/getInvoiceById/" + id;

        RestTemplate restTemplate = new RestTemplate();

        // Fetch response as List wrapped in ResponseEntity
        ResponseEntity<Invoice> response = restTemplate.getForEntity(resourceUrl, Invoice.class);
        
        Invoice invoiceDto = response.getBody();

        return new ResponseEntity<>(invoiceDto, HttpStatus.OK) ;
    }

    @GetMapping("/getInvoicesCount/{month}/{year}")
    @ResponseBody
    public String countInvoices(@PathVariable String month, @PathVariable String year) {
        String result = getInvoiceCounter(month, year);
        return " On month: " + month + ", year: " + year + " there was: " + result + " invoices.";
    }
    
}
