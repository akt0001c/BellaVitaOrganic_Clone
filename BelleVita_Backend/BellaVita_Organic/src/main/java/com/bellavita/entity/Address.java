package com.bellavita.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
	
	private AddressType adType;
	
	@NotEmpty(message="Address receiver name is mandatory")
	@NotBlank(message="Adderss receiver name is mandatory and cannot be blank")
	private String Name;
	
	@NotEmpty(message="House no or Street no or Area are mandatory")
	@NotBlank(message="House no or Street no or Area are mandatory")
	private String hno_street_area;
	
	@NotEmpty(message="State field is mandatory")
	@NotBlank(message="State field is mandatory and cannot be blank")
	private String state;
	
	@NotEmpty(message="city field is mandatory ")
	@NotBlank(message="city field is mandatory and cannot be blank")
	private String city;
	
	@NotEmpty(message="Area pincode is mandatory")
	@NotBlank(message="Area pincode is mandatory and cannot be blank")
	private String pincode;
	
	@NotEmpty(message="Address Phone numeber is mandatory")
	@NotBlank(message="Address Phone number is mandatory  and cannot be blank")
	private String phoneNO;
	
	private LocalDateTime updatedAt;
	

}
