package com.invoice.pdf_parser.data;

import lombok.Data;

// @JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Data
public class ClientDto {
	private long id;
	private String name;
	private String address;
	private String nip;
	private String email;
	
    private Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }
}
