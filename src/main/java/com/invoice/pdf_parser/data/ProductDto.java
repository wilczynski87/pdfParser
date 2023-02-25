package com.invoice.pdf_parser.data;

import java.math.BigDecimal;

import lombok.Data;

// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Data
public class ProductDto{
    private Long id;
    private ProductEnum productEnum;
    private BigDecimal unitPrice;
    private float quantity;
    private int vatRate;
    private BigDecimal vatAmout;
    private BigDecimal price;
    private BigDecimal priceWithVat;

    private Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
