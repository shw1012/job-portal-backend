package com.nisi.jobportalbackend.repository;

import com.nisi.jobportalbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
