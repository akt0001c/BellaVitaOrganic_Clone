package com.vitaOrganic.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="ORDERID")
	private Integer orderID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime OrderDateTime;
	
	@Temporal(TemporalType.DATE)
	private LocalDate deliveryDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	@JsonIgnore
	private Users user;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.ALL)
	private List<OrderDetail> orderDetails =new ArrayList<>();
	
	
}