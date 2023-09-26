package com.vitaOrganic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Column(name="transactionAmount")
@NotNull(message="Transaction amount should be there")
@DecimalMin(value="0.0")
private Double tamount;

@Column(name="transactionMethod")
@Enumerated(EnumType.STRING)
private TransactionMethod tmethod;

}
