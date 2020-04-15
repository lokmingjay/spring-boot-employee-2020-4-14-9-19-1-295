package com.thoughtworks.springbootemployee.model;

import com.thoughtworks.springbootemployee.model.Employee;

import java.util.List;

public class Company {

    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;

    public Company(String companyName, int employeesNumber, List<Employee> employees, int id) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


}
