package com.bellavita.dto;

import java.util.HashSet;
import java.util.Set;

import com.bellavita.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDto {
 private Set<ProductDto> ordersList = new HashSet<>();
 private Address address;
}
