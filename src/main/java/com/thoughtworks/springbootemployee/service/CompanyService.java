package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.StartDocument;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    public List<Company> getCompanies(Integer page,Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyRepository.getCompanies(page,pageSize);
        }
        return companyRepository.getCompanies();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.getCompanyById(companyId);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
                Company targetCompany =  companyRepository.getCompanyById(companyId);
                return targetCompany.getEmployees();
    }
}