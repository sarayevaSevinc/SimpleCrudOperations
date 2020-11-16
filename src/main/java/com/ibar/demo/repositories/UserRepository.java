package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> getUserById(UUID id);
    
    Optional<User> getUserByName(String name);
}
