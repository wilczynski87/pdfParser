package com.invoice.pdf_parser.data;

public enum BankAccountEnum {
    COMPANY_MAIN("PeKaO", "50 1950 0001 2006 0023 6241 0001"),
    COMPANY_TAX("PeKaO", "23 1950 0001 2006 0023 6241 0002"),
    PRIVATE_MAIN("Alior", " 11 2490 1044 0000 4200 8845 2192");

    public final String bankName;
    public final String accountNumber;


    BankAccountEnum(String bankName, String accountNumber) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
