package com.invoice.pdf_parser.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoicePdf {
    private String number;
    private LocalDate date;
    private BigDecimal tax;
    private BigDecimal price;
    private BigDecimal priceWithTax;
    private BankAccountEnum mainAccount;
    private BankAccountEnum taxAccount;
    
    public String getNumber() {
            return this.number;
    }

    public void setNumber(String number) {
            this.number = number;
    }

    public LocalDate getDate() {
            return this.date;
    }

    public void setDate(LocalDate date) {
            this.date = date;
    }

    public BigDecimal getTax() {
            return this.tax;
    }

    public void setTax(BigDecimal tax) {
            this.tax = tax;
    }

    public BigDecimal getPrice() {
            return this.price;
    }

    public void setPrice(BigDecimal price) {
            this.price = price;
    }

    public BigDecimal getPriceWithTax() {
            return this.priceWithTax;
    }

    public void setPriceWithTax(BigDecimal priceWithTax) {
            this.priceWithTax = priceWithTax;
    }

    public BankAccountEnum getMainAccount() {
            return this.mainAccount;
    }

    public void setMainAccount(BankAccountEnum mainAccount) {
            this.mainAccount = mainAccount;
    }

    public BankAccountEnum getTaxAccount() {
            return this.taxAccount;
    }

    public void setTaxAccount(BankAccountEnum taxAccount) {
            this.taxAccount = taxAccount;
    }
    
}
