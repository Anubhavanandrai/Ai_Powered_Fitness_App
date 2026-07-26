package com.fitness.userservice.service;


import org.springframework.stereotype.Service;

import com.fitness.userservice.Repository.UserRepository;
import com.fitness.userservice.dto.RegisterUser;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse registerUser(RegisterUser registeruser) {


        if(userRepository.existsByEmail(registeruser.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setPassword(registeruser.getPassword());
        user.setEmail(registeruser.getEmail());
        user.setFirstName(registeruser.getFirstName());
        user.setLastName(registeruser.getLastName());

        User x = userRepository.save(user);
        UserResponse userresponse = new UserResponse();
        userresponse.setEmail(registeruser.getEmail());
        userresponse.setFirstName(registeruser.getFirstName());
        userresponse.setLastName(registeruser.getLastName());

        return userresponse;
    }

    public UserResponse getUserProfile(String userid) {
        User user = new User();
        user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse response = new UserResponse();

        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());

        return response;
    }
}