package com.vitaOrganic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
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
 @Column(name="OrderDetailID")
  private Integer OrderDetailID;
 
  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="ORDERID")
  @JsonIgnore
  private Orders order;
  
  @Min(1)
  private Integer quantity;
  
  @ManyToOne(cascade=CascadeType.ALL)
  @JoinColumn(name="PID")
  @JsonIgnore
  private Products product;
}
