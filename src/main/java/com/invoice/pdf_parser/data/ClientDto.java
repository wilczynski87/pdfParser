package com.invoice.pdf_parser.data;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.core.serializer.Serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ClientDto implements Serializer {
	long id;
	String name;
	String address;
	String nip;
	String email;
    // private Set<ProductDto> products;
	@Override
	public void serialize(Object object, OutputStream outputStream) throws IOException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'serialize'");
	}
}
