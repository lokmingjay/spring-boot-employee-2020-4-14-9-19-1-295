package com.thoughtworks.springbootemployee.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.thoughtworks.springbootemployee.model.Employee;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    public List<Employee> employees ;


    @GetMapping
    public List<Employee> getRangeEmployee(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) String gender) {
        if (gender != null) {
            return employees.stream()
                    .filter(employee -> employee.getGender().equals(gender))
                    .collect(Collectors.toList());
        }

        if (page != null && pageSize != null) {
            return employees.subList((page - 1) * pageSize, page * pageSize);
        }
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
             Employee targetEmployee =   employees.stream()
                .filter(employee -> employee.getId() == employeeId)
                .findFirst()
                .orElse(null);
             return targetEmployee;
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
