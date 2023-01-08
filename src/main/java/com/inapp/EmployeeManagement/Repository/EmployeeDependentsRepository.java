package com.inapp.EmployeeManagement.Repository;

import com.inapp.EmployeeManagement.Entity.EmployeeDependents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDependentsRepository extends JpaRepository<EmployeeDependents, Integer> {
}
