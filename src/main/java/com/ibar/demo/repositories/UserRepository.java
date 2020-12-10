package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(long id);

    Optional<User> getUserByName(String name);
}
