package com.invoice.pdf_parser.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.invoice.pdf_parser.data.InvoiceDto;

@Service
public class InvoiceService {

    public InvoiceDto chooseInvoice(List<InvoiceDto> invoiceList) {
        if(invoiceList.isEmpty() ) return new InvoiceDto();
        if(invoiceList.size() == 1 ) return invoiceList.get(0);

        // sprawdzić jak sobie radzi komparator
        return invoiceList.stream()
            .max(Comparable::compareTo)
            .orElse(new InvoiceDto());
    }

    public Long getCurrentMonth(Long month) {
        return month == null || month > 12l || month < 1
        ? Long.valueOf(LocalDate.now().getMonthValue())
        : month;
    }

    public Long getCurrentYear(Long year) {
        return year == null 
        ? Long.valueOf(LocalDate.now().getYear()) 
        : year;
    }
    
}
