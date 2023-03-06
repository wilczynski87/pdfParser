package com.invoice.pdf_parser.data;

import java.math.BigDecimal;

import lombok.Data;

// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Data
public class ProductDto{
    private ProductEnum productEnum;
    private BigDecimal unitPrice;
    private float quantity;
    private int vatRate;
    private BigDecimal vatAmout;
    private BigDecimal price;
    private BigDecimal priceWithVat;
}
