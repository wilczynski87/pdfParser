package com.invoice.pdf_parser.data;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

import org.springframework.core.serializer.Serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ProductDto implements Serializer {
    private Long id;
    private ProductEnum productEnum;
    private BigDecimal unitPrice;
    private float quantity;
    private int vatRate;
    private BigDecimal vatAmout;
    private BigDecimal price;
    private BigDecimal priceWithVat;
    @Override
    public void serialize(Object object, OutputStream outputStream) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serialize'");
    }
}
