package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> getUserById(long id);
    
    Optional<User> getUserByName(String name);
}
