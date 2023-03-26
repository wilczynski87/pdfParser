package com.invoice.pdf_parser.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceDto implements Comparable<InvoiceDto>{
        private String number;
        private LocalDate date;
        private BigDecimal tax;
        private BigDecimal price;
        private BigDecimal priceWithTax;
        private BankAccountEnum mainAccount;
        private BankAccountEnum taxAccount;
        private ClientDto client;
        private List<ProductDto> productsDto;

        public InvoiceDto() {
        }

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

        public ClientDto getClient() {
                return this.client;
        }

        public void setClient(ClientDto client) {
                this.client = client;
        }

        public List<ProductDto> getProductsDto() {
                return this.productsDto;
        }

        public void setProductsDto(List<ProductDto> productsDto) {
                this.productsDto = productsDto;
        }

        @Override
        public String toString() {
                return "{" +
                        "number='" + getNumber() + "'" +
                        ", date='" + getDate() + "'" +
                        ", tax='" + getTax() + "'" +
                        ", price='" + getPrice() + "'" +
                        ", priceWithTax='" + getPriceWithTax() + "'" +
                        ", mainAccount='" + getMainAccount() + "'" +
                        ", taxAccount='" + getTaxAccount() + "'" +
                        ", client='" + getClient() + "'" +
                        ", productsDto='" + getProductsDto() + "'" +
                        "}";
        }

        @Override
        public int compareTo(InvoiceDto otherInvoice) {
                return Integer.compare(
                        Integer.parseInt(this.getNumber().substring(0, 1)), 
                        Integer.parseInt(otherInvoice.getNumber().substring(0, 1)));
        }
        
}