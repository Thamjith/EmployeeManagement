package com.inapp.EmployeeManagement.Controller;

import com.inapp.EmployeeManagement.Entity.Employee;
import com.inapp.EmployeeManagement.HelperClasses.SimpleResponse;
import com.inapp.EmployeeManagement.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/paginated")
    public Page<Employee> getEmployeesPaginated(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit
    ) {
        return employeeService.getEmployeesPaginated(page, limit);
    }

    @GetMapping("/sorted")
    public List<Employee> getEmployeesSorted(
            @RequestParam(name = "sort", defaultValue = "asc") String sort
    ) {
        return employeeService.getEmployeesSorted(sort);
    }

    @GetMapping("paginated/sorted")
    public Page<Employee> getEmployeesPaginatedAndSorted(
            @RequestParam(name = "sort", defaultValue = "") String sort,
            @RequestParam(name = "page", defaultValue = "-1") Integer page,
            @RequestParam(name = "limit", defaultValue = "-1") Integer limit
    ) {
        return employeeService.getEmployeesPaginatedAndSorted(sort, page, limit);
    }

    @GetMapping(path = "/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping(path = "/{id}")
    public SimpleResponse DeleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping
    public Employee UpdateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }
}
