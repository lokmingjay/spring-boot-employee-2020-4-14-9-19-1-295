package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> getCompanies(Integer page, Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
        }
        return companyRepository.findAll();
    }


    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company createNewCompany(Company newCompany) {
        return companyRepository.save(newCompany);
    }

    public void updateCompanyById(Integer companyId, Company targetCompany) {
        String name = targetCompany.getCompanyName();
        companyRepository.updateCompany(companyId, name);
    }

    public void deleteEmployeeByCompanyId(Integer  companyId) {
        //companyRepository.deleteById(companyId);
        employeeRepository.deleteEmployeeByCompanyId(companyId);




    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        Company targetCompany = companyRepository.findById(companyId).orElse(null);
        if (targetCompany != null) {
            return targetCompany.getEmployees();
        }
        return null;
    }
}
