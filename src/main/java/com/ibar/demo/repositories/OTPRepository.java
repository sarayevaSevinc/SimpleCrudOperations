package com.ibar.demo.repositories;

import com.ibar.demo.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OTPRepository extends JpaRepository<OTP, Long> {
}
