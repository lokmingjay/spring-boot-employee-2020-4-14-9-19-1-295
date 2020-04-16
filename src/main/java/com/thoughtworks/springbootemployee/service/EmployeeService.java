package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.getEmployeeById(employeeId);
    }

    public List<Employee> addEmployee(Employee newEmployee) {
        return employeeRepository.addEmployee(newEmployee);
    }

    public List<Employee> updateEmployee(Integer employeeId, Employee newEmployee) {
        return employeeRepository.updateEmployee(employeeId, newEmployee);
    }

    public List<Employee> removeEmployee(Integer employeeId) {
        return employeeRepository.removeEmployee(employeeId);
    }

    public List<Employee> getEmployee(Integer page, Integer pageSize, String gender) {
        if (gender != null) {
            return employeeRepository.getEmployeeByGender(gender);
        }

        if (page != null && pageSize != null) {
            return employeeRepository.getEmployeeByPage(page, pageSize);

        }
        return employeeRepository.getEmployee();
    }
}
