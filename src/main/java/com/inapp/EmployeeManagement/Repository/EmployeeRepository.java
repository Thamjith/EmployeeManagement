package com.inapp.EmployeeManagement.Repository;

import com.inapp.EmployeeManagement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
}
