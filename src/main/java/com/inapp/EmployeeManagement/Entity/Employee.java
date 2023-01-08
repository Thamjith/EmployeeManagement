package com.inapp.EmployeeManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer salary;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dependents_id", referencedColumnName = "id")
    private EmployeeDependents dependents;

    public Employee(String firstname, String lastname, Integer salary, EmployeeDependents dependents){
        this.firstname = firstname;
        this.lastname = lastname;
        this.salary = salary;
        this.dependents = dependents;
    }
}
