package com.invoice.pdf_parser.pdf_mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.invoice.pdf_parser.data.InvoiceDto;
import com.invoice.pdf_parser.data.InvoicePdf;

@Mapper
public interface PdfMapper {

    PdfMapper INSTANCE = Mappers.getMapper( PdfMapper.class );

    // @Mapping(target = "productsDto", ignore = true)
    public InvoicePdf invoiceDtoToInvoicePdf(InvoiceDto invoiceDto);
    
}
