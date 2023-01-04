package com.inapp.EmployeeManagement.Repository;

import java.util.Optional;

import com.inapp.EmployeeManagement.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
