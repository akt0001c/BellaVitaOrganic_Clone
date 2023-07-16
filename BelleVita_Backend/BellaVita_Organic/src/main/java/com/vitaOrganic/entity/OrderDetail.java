package com.vitaOrganic.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="OrderDetail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer OrderDetailID;
  private Orders order;
  private Integer quantity;
  private Products product;
}
