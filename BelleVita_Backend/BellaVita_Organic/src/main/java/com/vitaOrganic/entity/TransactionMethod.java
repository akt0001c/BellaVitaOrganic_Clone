package com.vitaOrganic.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TransactionMethods")
public class TransactionMethod {
 
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
@Column(name="ttid")
 private Integer TransactionTypeId;

 @NotEmpty(message="Transaction type should  not be empty")
 @NotBlank(message="Transaction type should be valid")
 @Column(name="ttype")
 private String ttype;
 
 @Column(name="isActive", columnDefinition="boolean")
 private boolean isActive;
 
 @JsonIgnore
 @OneToMany(cascade=CascadeType.PERSIST)
 @JoinColumn(name="ttid")
 private List<Transactions> transactions= new ArrayList<>();
 

 
}
