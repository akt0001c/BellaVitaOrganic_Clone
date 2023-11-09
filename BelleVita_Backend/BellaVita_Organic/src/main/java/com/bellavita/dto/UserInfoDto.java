package com.bellavita.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto {

@NotEmpty(message="First Name cannot be Empty")
@NotBlank(message="First Cannot be blank so enter valid Frist name")	
private String FirstName;


@Email(message="Email should be in valid format(abc@email.com)")
@NotEmpty(message="Email cannot be Empty")
@NotBlank(message="Email Cannot be blank so enter valid  email address")
private String email;
}
