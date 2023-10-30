package com.vitaOrganic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

@Column(name="timestamp")
@Temporal(TemporalType.TIMESTAMP)
private LocalDateTime timestamp;

@Column(name="transactionType")
@Enumerated(EnumType.STRING)
private TransactionType ttype;

@OneToOne(cascade=CascadeType.PERSIST )
@JoinColumn(name="ORDERID" ,referencedColumnName="ORDERID")
private Orders order;

@Column(name="transactionAmount")
@NotNull(message="Transaction amount should be there")
@DecimalMin(value="0.0")
private Double tamount;

@Column(name="TransactionStatus")
@Enumerated(EnumType.STRING)
private TransactionStatus status;





}
