package com.vitaOrganic.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 @Column(name="USER_ID")
 private Integer userID;
 private String firstName;
 private String lastName;
 private String email;
 private String password;
 private LocalDateTime createdAt;
 private UserStatus status;
 private String role;
 private String location;
}
