package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    public List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(1, "Jay", 10, "Male"),
            new Employee(2, "Andy", 20, "Male"),
            new Employee(3, "Leo", 30, "Male"),
            new Employee(4, "Wesley", 40, "Male"),
            new Employee(5, "Hilary", 50, "Female")));

    public Employee getEmployeeById(Integer employeeId) {
        return employeeList.stream()
                .filter(employee -> employee.getId() == employeeId)
                .findFirst()
                .orElse(null);
    }

    public List<Employee> addEmployee(Employee newEmployee) {
        if (employeeList.stream().noneMatch(employee -> employee.getId() == newEmployee.getId())) {
            employeeList.add(newEmployee);
        }
        return employeeList;
    }

    public List<Employee> updateEmployee(Integer employeeId, Employee newEmployee) {
              employeeList.removeIf(employee -> employee.getId() == employeeId);
              employeeList.add(newEmployee);
                return employeeList;


    }

    public List<Employee> removeEmployee(Integer employeeId) {
         employeeList.removeIf(employee -> employee.getId() == employeeId);
         return employeeList;
    }

    public List<Employee> getEmployeeByGender(String gender) {
                    return employeeList.stream()
                   .filter(employee -> employee.getGender().equals(gender))
                   .collect(Collectors.toList());

    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employeeList.subList((page - 1) * pageSize, page * pageSize);
    }

    public List<Employee> getEmployee() {
        return employeeList;
    }
}
