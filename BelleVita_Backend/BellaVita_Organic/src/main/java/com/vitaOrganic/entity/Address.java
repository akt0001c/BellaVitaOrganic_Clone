package com.vitaOrganic.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
	
	private String type;
	private String hno_street_area;
	private String state;
	private String city;
	private String pincode;
	

}
