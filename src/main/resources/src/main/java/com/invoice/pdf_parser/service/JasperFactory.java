package com.invoice.pdf_parser.service;

import com.invoice.pdf_parser.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JasperFactory {
    public static InvoiceDto exampleInvoiceDto() {
        InvoiceDto invoiceDto = new InvoiceDto();

        ClientDto clientDto = new ClientDto();
        clientDto.setAddress("Duposzczyny 2, Zadupcze 55-555");
        clientDto.setEmail("dupa@dupa.pl");
        clientDto.setName("Dupowicki sp. z o.o.");
        clientDto.setNip("1234567890");

        ProductDto productDto1 = new ProductDto(ProductEnum.PLAC, new BigDecimal("2.5"), 200f, 23, new BigDecimal(115), new BigDecimal(500), new BigDecimal(615));
        ProductDto productDto2 = new ProductDto(ProductEnum.PLAC, new BigDecimal("2.5"), 200f, 23, new BigDecimal(115), new BigDecimal(500), new BigDecimal(615));

        invoiceDto.setNumber("1/01/2023");
        invoiceDto.setDate(LocalDate.now());
        invoiceDto.setPrice(new BigDecimal(1000));
        invoiceDto.setTax(new BigDecimal(230));
        invoiceDto.setMainAccount(BankAccountEnum.COMPANY_MAIN);
        invoiceDto.setTaxAccount(BankAccountEnum.COMPANY_TAX);
        invoiceDto.setPriceWithTax(new BigDecimal(1230));
        invoiceDto.setClient(clientDto);
        invoiceDto.setProductsDto(List.of(productDto1, productDto2));

        return invoiceDto;
    }

    //createCollection
    public static java.util.Collection genereateCollection()
    {
        InvoiceDto invoiceDto = exampleInvoiceDto();

        List<ProductDto> listOfProducts = invoiceDto.getProductsDto();

        java.util.Vector collection = new java.util.Vector();
        collection.addAll(listOfProducts);
        return collection;
    }

}
