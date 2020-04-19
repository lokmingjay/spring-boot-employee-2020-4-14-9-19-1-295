package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    @Modifying
    @Query(value ="update COMPANY c set c.company_name = :name where c.id = :companyId", nativeQuery = true )
    void updateCompany(@Param("companyId") Integer companyId, @Param("name") String name);

    @Modifying
    @Query(value = "ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
//    public List<Employee> employeeList = new ArrayList<>(Arrays.asList(
//            new Employee(1, "Jay", 10, "Male"),
//            new Employee(2, "Andy", 20, "Male"),
//            new Employee(3, "Leo", 30, "Male"),
//            new Employee(4, "Wesley", 40, "Male"),
//            new Employee(5, "Hilary", 50, "Female")));
//    public List<Company> companyList = new ArrayList<>(Arrays.asList(
//            new Company("A", 5, employeeList, 1),
//            new Company("B", 5, employeeList, 2),
//            new Company("C", 5, employeeList, 3)
//    ));
//
//
//    public List<Company> getCompanies(int page, int pageSize) {
//        return companyList.subList((page - 1) * pageSize, page * pageSize);
//    }
//
//    public List<Company> getCompanies() {
//        return companyList;
//    }
//
//    public Company getCompanyById(Integer companyId) {
//        return companyList.stream()
//                .filter(company -> company.getId() == companyId)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Company addNewRepository(Company newCompany) {
//             companyList.add(newCompany);
//             return newCompany;
//    }
//
//    public Company updateCompany(int companyId, Company targetCompany) {
//        companyList.removeIf(company -> company.getId() == companyId);
//        companyList.add(targetCompany);
//        return targetCompany;
//    }
//
//    public void removeCompany(int companyId) {
//        companyList.removeIf(company -> company.getId() == companyId);
//    }
}
