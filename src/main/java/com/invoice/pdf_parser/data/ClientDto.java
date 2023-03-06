package com.invoice.pdf_parser.data;

import lombok.Data;

// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Data
public class ClientDto {
	private String name;
	private String address;
	private String nip;
	private String email;
}
