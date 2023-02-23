package com.invoice.pdf_parser.data;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private ProductEnum productEnum;
    private BigDecimal unitPrice;
    private float quantity;
    private int vatRate;
    private BigDecimal vatAmout;
    private BigDecimal price;
    private BigDecimal priceWithVat;
}
