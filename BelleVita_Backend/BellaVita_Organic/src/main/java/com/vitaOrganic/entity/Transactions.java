package com.vitaOrganic.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Transactions")
@Entity
public class Transactions {
@Id
@GeneratedValue(strategy= GenerationType.AUTO)
@Column(name="tid")
private Integer transactionId;

@Column(name="transactionTime")
@Temporal(TemporalType.TIME)
private LocalTime transactionTime;

@Column(name="transactionDate")
@Temporal(TemporalType.DATE)
private LocalDate transactionDate;



@OneToOne(cascade=CascadeType.PERSIST )
@JoinColumn(name="ORDERID" ,referencedColumnName="ORDERID")
private Orders order;

@JsonIgnore
@ManyToOne(cascade=CascadeType.PERSIST)
@JoinColumn(name="user_id")
private Users user;

@Column(name="transactionAmount")
@NotNull(message="Transaction amount should be there")
@DecimalMin(value="0.0")
private Double tamount;

@Column(name="TransactionStatus")
@Enumerated(EnumType.STRING)
private TransactionStatus status;





}
