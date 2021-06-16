package com.ibar.demo.repositories;

import com.ibar.demo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(long id);

    Optional<User> getUserByPin(String pin);

    Optional<User> getUserByName(String name);
    Optional<User>  getUserByEmail(String email);
    @Query("select u from IBA_USERS u where u.pin = :pin and u.password = :password")
    Optional<User> getByPinAndPassword(String pin, String password);
}
