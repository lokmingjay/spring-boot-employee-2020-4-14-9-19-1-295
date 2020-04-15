package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    public List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1, "Jay", 10, "Male"),
            new Employee(2, "Andy", 20, "Male"),
            new Employee(3, "Leo", 30, "Male"),
            new Employee(4, "Wesley", 40, "Male"),
            new Employee(5, "Hilary", 50, "Female")));


    @GetMapping
    public List<Employee> getRangeEmployee(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) String gender) {
        if (gender != null) {
            return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        }

        if (page != null && pageSize != null) {
            return employees.subList((page - 1) * pageSize, page * pageSize);
        }

        return employees;
    }


    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employees.stream().filter(employee -> employee.getId() == employeeId).findFirst().get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public List<Employee> createNewEmployee(@RequestBody Employee newEmployee) {
        if (employees.stream().noneMatch(employee -> employee.getId() == newEmployee.getId())) {
            employees.add(newEmployee);
            return employees;
        }
        return null;

    }

    @PutMapping("/{employeeId}")
    public List<Employee> changeEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody Employee newEmployee) {
        employees.removeIf(employee -> employee.getId() == employeeId);
        employees.add(newEmployee);
        return employees;
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Employee> removeEmployee(@PathVariable("employeeId") Integer employeeId) {
        employees.removeIf(employee -> employee.getId() == employeeId);
        return employees;
    }


}
