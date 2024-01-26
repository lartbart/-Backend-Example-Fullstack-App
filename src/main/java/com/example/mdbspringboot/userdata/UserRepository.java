package com.example.mdbspringboot.userdata;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    boolean existsByUsername(String username);
    UserModel findByUsername(String username);
}
