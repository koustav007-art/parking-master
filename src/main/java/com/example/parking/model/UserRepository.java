package com.example.parking.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
 
    UserEntity findByUsername(String userName);
}
