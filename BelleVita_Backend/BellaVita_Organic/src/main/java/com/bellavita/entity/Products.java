package com.bellavita.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
   @Column(name="PID")
   private Integer productID;
   
   @NotEmpty(message="Product Name cannot be Empty")
   @NotBlank(message="Product Cannot be blank so enter valid Product name")
   private String productName;
   
   @NotEmpty(message="Product must have a valid image url")
   @NotBlank(message="Product image url cannot be blank")
   private String productUrl;
   
   @Min(value=0,message="Product quantity atleast should be one")
   private Integer quantity;
   
   @Temporal(TemporalType.TIMESTAMP)
   private LocalDateTime productAddTime;
   
   @Enumerated(EnumType.STRING)
   private ProductStatus status;
   
   @DecimalMin(value="0.0",message="Price cannot not be neagtive")
   @NotNull(message="Price cannot be null")
   private Double productPrice;
   
   @OneToMany(mappedBy="product",cascade=CascadeType.ALL)
   private List<OrderDetail> orderDetails= new ArrayList<>();
}
