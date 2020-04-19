package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElse(null);
    }

    public Employee addEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public void updateEmployee(Integer employeeId, Employee newEmployee) {
        employeeRepository.updateName(employeeId, newEmployee.getName());
    }

    public void removeEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getEmployee(Integer page, Integer pageSize, String gender) {
        if (gender != null) {
            return employeeRepository.findByGender(gender);
        }

        if (page != null && pageSize != null) {
            return employeeRepository.findAll(PageRequest.of(page, pageSize)).getContent();
        }
        return employeeRepository.findAll();
    }
}
