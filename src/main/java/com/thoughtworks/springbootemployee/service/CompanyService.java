package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.xml.stream.events.StartDocument;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getCompanies(Integer page,Integer pageSize) {
        if (page != null && pageSize != null) {
            return companyRepository.findAll(PageRequest.of(page,pageSize)).getContent();
        }
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }
//
//    public List<Employee> getEmployeesByCompanyId(int companyId) {
//                Company targetCompany =  companyRepository.getCompanyById(companyId);
//                return targetCompany.getEmployees();
//    }
//
    public Company createNewCompany(Company newCompany) {
        return companyRepository.save(newCompany);
//                Company targetCompany = companyRepository.getCompanyById(newCompany.getId());
//                if(targetCompany == null){
//                   return companyRepository.addNewRepository(newCompany);
//                }
//                return null;
    }
//
//    public Company updateCompany(int companyId, Company targetCompany) {
//        return companyRepository.updateCompany(companyId,targetCompany);
//    }
//
//    public void removeCompany(int companyId) {
//         companyRepository.removeCompany(companyId);
//    }
}
