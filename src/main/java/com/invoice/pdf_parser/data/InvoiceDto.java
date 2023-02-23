package com.invoice.pdf_parser.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceDto {
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
}