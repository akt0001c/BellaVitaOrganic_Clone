package com.bellavita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsUpdatedFieldsDto {
private String productName;
private Double productPrice;
private String productUrl;

}
