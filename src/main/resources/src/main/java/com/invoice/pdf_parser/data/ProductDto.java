package com.invoice.pdf_parser.data;

import java.math.BigDecimal;

// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ProductDto {
    private ProductEnum productEnum;
    private BigDecimal unitPrice;
    private float quantity;
    private int vatRate;
    private BigDecimal vatAmout;
    private BigDecimal price;
    private BigDecimal priceWithVat;

    public ProductDto() {};

    public ProductDto(ProductEnum productEnum, BigDecimal unitPrice, float quantity, int vatRate, BigDecimal vatAmout, BigDecimal price, BigDecimal priceWithVat) {
        this.productEnum = productEnum;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.vatRate = vatRate;
        this.vatAmout = vatAmout;
        this.price = price;
        this.priceWithVat = priceWithVat;
    }

    public ProductEnum getProductEnum() {
        return this.productEnum;
    }

    public void setProductEnum(ProductEnum productEnum) {
        this.productEnum = productEnum;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public int getVatRate() {
        return this.vatRate;
    }

    public void setVatRate(int vatRate) {
        this.vatRate = vatRate;
    }

    public BigDecimal getVatAmout() {
        return this.vatAmout;
    }

    public void setVatAmout(BigDecimal vatAmout) {
        this.vatAmout = vatAmout;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithVat() {
        return this.priceWithVat;
    }

    public void setPriceWithVat(BigDecimal priceWithVat) {
        this.priceWithVat = priceWithVat;
    }

}
