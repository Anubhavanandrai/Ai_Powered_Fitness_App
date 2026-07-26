package com.fitness.userservice.model;


import java.time.LocalDateTime;			

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Table(name = "users")

public class User {
 
@Id	
@GeneratedValue (strategy = GenerationType.UUID)
private String id;

@Column( unique = true, nullable = false)
private String email;
@Column( nullable = false)
private String password;
private String firstName;
private String lastName;

@Enumerated(EnumType.STRING)
private UserRole role= UserRole.USER;

// Only hibernate specific annotation is used here
@CreationTimestamp
private LocalDateTime createdAt;
@UpdateTimestamp
private LocalDateTime updatedAt;
}
