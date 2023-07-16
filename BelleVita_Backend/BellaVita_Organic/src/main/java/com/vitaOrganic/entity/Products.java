package com.vitaOrganic.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
   @Id
   @GeneratedValue(strategy= GenerationType.AUTO)
   private Integer productID;
   private String productName;
   private LocalDateTime productAddTime;
   private ProductStatus status;
   private List<OrderDetail> orderDetails= new ArrayList<>();
}
