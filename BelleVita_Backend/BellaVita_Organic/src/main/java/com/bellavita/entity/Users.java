package com.bellavita.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
 
 @Column(name="USER_ID")
 @Id
 @GeneratedValue(strategy= GenerationType.AUTO)
 private Integer userID;
 
 @NotEmpty(message="First Name cannot be Empty")
 @NotBlank(message="First Cannot be blank so enter valid Frist name")
 private String firstName;
 private String lastName;
 
 @Email(message="Email should be in valid format(abc@email.com)")
 @NotEmpty(message="Email cannot be Empty")
 @NotBlank(message="Email Cannot be blank so enter valid  email address")
 @Column(unique=true)
 private String email;
 
 @NotEmpty(message="Password cannot be Empty")
 @NotBlank(message="Password Cannot be blank so enter valid Password and there should be ")
 @Pattern(regexp="^[A-Z][a-z]+[!@#$%^&*()_+{}|;':\",.<>?/`~\\-=[\\]\\\\;'./A-Za-z0-9]{7,}$")
 @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
 @Column(unique=true)
 private String password;
 
 @Temporal(TemporalType.TIMESTAMP)
 private LocalDateTime createdAt;
 
 @Enumerated(EnumType.STRING)
 private UserStatus status;
 
 @NotEmpty(message="User should have a role ")
 @NotBlank(message="User should have a valid role ")
 private String role;
 
 @NotEmpty(message="User should have a location ")
 @NotBlank(message="User should have a valid location ")
 private String location;
 
 @OneToMany(mappedBy="user" ,cascade=CascadeType.ALL)
 private List<Orders> orders= new ArrayList<>();
 
 @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
 private Set<Transactions> transactions = new HashSet<>();
 
 @ElementCollection(fetch= FetchType.EAGER)
 @Embedded
 @JoinTable(name="userAddress" ,joinColumns= @JoinColumn(name="userId"))
 private Set<Address> addresses= new HashSet<>();
}
