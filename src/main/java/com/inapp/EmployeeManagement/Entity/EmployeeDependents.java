package com.inapp.EmployeeManagement.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="EmployeeDependents")
public class EmployeeDependents {
    @Id
    @JsonIgnore
    @GeneratedValue
    private Integer id;
    @OneToOne(mappedBy = "dependents")
    @JsonIgnore
    private Employee employee;
    private String firstname;
    private String lastname;

    public EmployeeDependents(String firstname, String lastname){
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
