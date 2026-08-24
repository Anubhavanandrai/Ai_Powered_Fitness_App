package com.fitness.userservice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.fitness.userservice.model.User;

public interface UserRepository extends JpaRepository<User,String>{

    //define methods which we wants to create for JPA.

    boolean existsByEmail(String email);


}
