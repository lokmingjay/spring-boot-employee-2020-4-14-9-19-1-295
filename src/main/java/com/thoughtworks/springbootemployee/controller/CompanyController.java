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


    @Autowired
    private CompanyService service;

    @GetMapping
    public List<Company> getCompanies(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer pageSize) {
        return service.getCompanies(page, pageSize);
    }

    @GetMapping("/{companyId}")
    public Company getCompanyById(@PathVariable("companyId") Integer companyId) {
        return service.getCompanyById(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getEmployeeByCompanyId(@PathVariable("companyId") Integer companyId) {
        return service.getEmployeesByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company createNewCompany(@RequestBody Company newCompany) {
        return service.createNewCompany(newCompany);
    }

    @PutMapping("/{companyId}")
    public void changeCompany(@PathVariable("companyId") Integer companyId, @RequestBody Company targetCompany) {
        service.updateCompanyById(companyId, targetCompany);
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployeesByCompanyId(@PathVariable("companyId") Integer companyId) {
        service.deleteEmployeeByCompanyId(companyId);
    }



}
