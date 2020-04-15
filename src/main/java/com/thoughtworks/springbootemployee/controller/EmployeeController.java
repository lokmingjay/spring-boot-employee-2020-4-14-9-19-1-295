package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    public List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1, "Jay", 10, "Male"),
            new Employee(2, "Andy", 20, "Male"),
            new Employee(3, "Leo", 30, "Male"),
            new Employee(4, "Wesley", 40, "Male"),
            new Employee(5, "Hilary", 50, "Female")));

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    public Employee changeEmployee(@PathVariable("employeeId")  Integer employeeId,@RequestBody Employee newEmployee) {
        employees.removeIf(employee ->employee.getId() == employeeId);
        employees.add(newEmployee);
        return newEmployee;
    }




}
