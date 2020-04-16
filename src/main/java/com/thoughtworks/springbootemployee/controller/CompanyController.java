package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    public List<Employee> employees;
    public List<Company> companyList;

    @Autowired
    private CompanyService service;

    @GetMapping()
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer pageSize) {
        return service.getCompanies(page, pageSize);
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
