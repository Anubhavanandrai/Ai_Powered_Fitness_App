package com.fitness.userservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitness.userservice.dto.RegisterUser;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")

public class Usercontroller {
	
	
@Autowired private UserService userservice;


	@GetMapping("/")
	public String getHome(){
		String s="hello from userservice";
		return s;
	}


@GetMapping("/{userId}")
public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
	return ResponseEntity.ok(userservice.getUserProfile(userId));
			
}

@PostMapping("/register")
public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody RegisterUser request){

	return ResponseEntity.ok(userservice.registerUser(request));
			
}

@GetMapping("/{userId}/validate")
public ResponseEntity<Boolean> validateUser(@PathVariable String userId){
	System.out.println("Inside User controller validate");
	return ResponseEntity.ok(userservice.existByUserId(userId));
}

}
