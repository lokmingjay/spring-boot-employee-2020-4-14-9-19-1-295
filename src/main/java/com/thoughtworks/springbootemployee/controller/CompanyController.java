package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    public List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1, "Jay", 10, "Male"),
            new Employee(2, "Andy", 20, "Male"),
            new Employee(3, "Leo", 30, "Male"),
            new Employee(4, "Wesley", 40, "Male"),
            new Employee(5, "Hilary", 50, "Female")));

    private List<Company> companyList = new ArrayList<>(Arrays.asList(
            new Company("A", 5, employees, 1),
            new Company("B", 5, employees, 2),
            new Company("C", 5, employees, 3)
    ));


    @GetMapping()
    public List<Company> getEmployees(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyList.subList((page - 1) * pageSize, page * pageSize);
        }
        return companyList;
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable("companyId") Integer companyId) {
        return companyList.stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .get();
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable("companyId") int companyId) {
        Company targetCompany = companyList
                .stream()
                .filter(company -> company.getId() == companyId)
                .findFirst()
                .orElse(null);
        if (targetCompany != null) {
            return targetCompany.getEmployees();
        }
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createNewCompany(@RequestBody Company newCompany) {
        if (companyList.stream().noneMatch(company -> company.getId() == newCompany.getId())) {
            companyList.add(newCompany);
            return newCompany;
        }
        return null;
    }

    @PutMapping("/{companyId}")
    public Company changeCompany(@PathVariable("companyId") int companyId, @RequestBody Company targetCompany) {
        companyList.removeIf(company -> company.getId() == companyId);
        companyList.add(targetCompany);
        return targetCompany;
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable("companyId") int companyId) {
        companyList.removeIf(company -> company.getId() == companyId);
    }

}
