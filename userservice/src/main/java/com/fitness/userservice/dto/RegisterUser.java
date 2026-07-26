package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUser {

	@NotBlank(message="Email is required")
	@Email(message="Invalid email fromat")
	private String email; 
	
	@NotBlank(message="Password is required")
	@Size(min=6,max=8,message="Password must be of length 8")
	private String password; 
	
	
	@NotBlank(message="FirstName is required")
	private String firstName; 
	
	@NotBlank(message="LastName is required")
	private String lastName; 
}
