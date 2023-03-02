package com.invoice.pdf_parser.service;

import java.util.Collection;
import java.util.Vector;

import org.springframework.stereotype.Service;

import com.invoice.pdf_parser.data.InvoiceDto;

@Service
public class ReportFactory {
//CreateArrayList
public static InvoiceDto[] generateBeanArray()
{
    InvoiceDto[] list = new InvoiceDto[1];
    list[0] = new InvoiceDto();
    return list;
}
//createCollection
public static Collection genereateCollection()
{
    Vector collection = new Vector();
    collection.add(new InvoiceDto());
    return collection;
}
    
}
