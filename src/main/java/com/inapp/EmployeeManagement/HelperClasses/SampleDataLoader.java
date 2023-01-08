package com.inapp.EmployeeManagement.HelperClasses;

import com.github.javafaker.Faker;
import com.inapp.EmployeeManagement.Entity.Employee;
import com.inapp.EmployeeManagement.Entity.EmployeeDependents;
import com.inapp.EmployeeManagement.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;
    private final Faker faker;

    @Override
    public void run(String... args) throws Exception {
        List<Employee> people = IntStream.rangeClosed(1,100)
                .mapToObj(i -> new Employee(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.number().numberBetween(30000, 80000),
                        new EmployeeDependents(
                            faker.name().firstName(),
                            faker.name().lastName()
                        )
                )).collect(Collectors.toList());

        employeeRepository.saveAll(people);

    }
}
