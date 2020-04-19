package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getRangeEmployee(@RequestParam(required = false) Integer page,
                                           @RequestParam(required = false) Integer pageSize,
                                           @RequestParam(required = false) String gender) {

        return service.getEmployee(page, pageSize, gender);
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployeeById(@PathVariable("employeeId") Integer employeeId) {
        return service.findEmployeeById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {

        return service.addEmployee(newEmployee);
    }

    @PutMapping("/{employeeId}")
    public void changeEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody Employee newEmployee) {
         service.updateEmployee(employeeId, newEmployee);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeEmployee(@PathVariable("employeeId") Integer employeeId) { service.removeEmployee(employeeId);
    }
}
