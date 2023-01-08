package com.inapp.EmployeeManagement.Service;

import com.inapp.EmployeeManagement.Entity.Employee;
import com.inapp.EmployeeManagement.HelperClasses.SimpleResponse;
import com.inapp.EmployeeManagement.Repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee emp){
        return employeeRepository.save(emp);
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int id){
        return employeeRepository.findById(id).get();
    }

    public Page<Employee> getEmployeesPaginated(Integer page, Integer limit) {
        Pageable paging = PageRequest.of(page, limit);
        return employeeRepository.findAll(paging);
    }

    public List<Employee> getEmployeesSorted(String sort) {
        if(sort.equalsIgnoreCase("desc")){
            return employeeRepository.findAll(Sort.by(Sort.Order.desc("salary")));
        }else{
            return employeeRepository.findAll(Sort.by(Sort.Order.asc("salary")));
        }
    }

    public Page<Employee> getEmployeesPaginatedAndSorted(String sort, Integer page, Integer limit) {
        Pageable paging = null;
        if(sort.equalsIgnoreCase("desc")){
            paging = PageRequest.of(page, limit, Sort.by("salary").descending());
        }else{
            paging = PageRequest.of(page, limit, Sort.by("salary").ascending());
        }
        return employeeRepository.findAll(paging);
    }

    public SimpleResponse deleteEmployee(int id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return SimpleResponse.builder()
                    .message("Employee deleted")
                    .status(true)
                    .build();
        }else{
            return SimpleResponse.builder()
                    .message("Employee doesn't exist")
                    .status(false)
                    .build();
        }
    }

    public Employee updateEmployee(Employee employee) {
        if(employeeRepository.existsById(employee.getId())){
            Employee emp = employeeRepository.findById(employee.getId()).get();
            emp.setFirstname(employee.getFirstname());
            emp.setLastname(employee.getLastname());
            emp.setSalary(employee.getSalary());
            emp.getDependents().setFirstname(employee.getDependents().getFirstname());
            emp.getDependents().setLastname(employee.getDependents().getLastname());
            return employeeRepository.save(emp);
        }else throw new UsernameNotFoundException("User doesn't exist");
    }
}
