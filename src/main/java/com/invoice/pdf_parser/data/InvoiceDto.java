package com.invoice.pdf_parser.data;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.core.serializer.Serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class InvoiceDto implements Serializer {
        private Long id;
        private String number;
        private LocalDate date;
        private BigDecimal tax;
        private BigDecimal price;
        private BigDecimal priceWithTax;
        private BankAccountEnum mainAccount;
        private BankAccountEnum taxAccount;
        private ClientDto client;
        private List<ProductDto> products;
        @Override
        public void serialize(Object object, OutputStream outputStream) throws IOException {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'serialize'");
        }
}