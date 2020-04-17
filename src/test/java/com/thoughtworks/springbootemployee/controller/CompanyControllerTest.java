package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;
    @Autowired
    private CompanyRepository companyRepository;
    private List<Employee> employeeList;


    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(companyController);

        employeeList = new ArrayList<>(Arrays.asList(
                new Employee(1, "Jay", 10, "Male",1),
                new Employee(2, "Andy", 20, "Male",1),
                new Employee(3, "Leo", 30, "Male",1),
                new Employee(4, "Wesley", 40, "Male",1),
                new Employee(5, "Hilary", 50, "Female",1)));
        companyRepository.save( new Company("A", 5, null, 1));
        companyRepository.save( new Company("B", 5, null, 2));
        companyRepository.save( new Company("C", 5, null, 3));

//        companyRepository.companyList = new ArrayList<>(Arrays.asList(
//                new Company("A", 5, companyRepository.employeeList, 1),
//                new Company("B", 5, companyRepository.employeeList, 2),
//                new Company("C", 5, companyRepository.employeeList, 3)
//        ));
    }

    @Test
    public void should_get_companies_success() {
        // given...
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies");

        List<Company> companyList = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assert.assertEquals(3, companyList.size());
        //Assert.assertEquals("Jay",companyList.get(0).getEmployees().get(0).getName());
    }

    @Test
    public void should_get_companies_with_page_success() {
        // given...
        HashMap<String, Integer> parameters = new HashMap<String, Integer>();
        parameters.put("page", 1);
        parameters.put("pageSize", 3);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .params(parameters)
                .when()
                .get("/companies");

        List<Company> companyList = response.getBody().as(new TypeRef<List<Company>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        Assert.assertEquals(3, companyList.size());


    }

    @Test
    public void should_get_company_by_id_success() {
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies/1");

        Company company = response.getBody().as(Company.class);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Assert.assertEquals("A", company.getCompanyName());

    }

    @Test
    public void should_create_new_company_success() {
        Company company = new Company();
        company.setId(4);
        company.setCompanyName("D");

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .post("/companies");

        Company targetCompany = response.getBody().as(Company.class);
        Assert.assertEquals("D", targetCompany.getCompanyName());
    }

    @Test
    public void should_update_company_success() {
        Company company = new Company();
        company.setId(1);
        company.setCompanyName("AA");

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(company)
                .when()
                .put("/companies/1");

        Company targetCompany = response.getBody().as(Company.class);
        Assert.assertEquals("AA", targetCompany.getCompanyName());
    }

//    @Test
//    public void should_delete_company_success() {
//
//        MockMvcResponse response = given().contentType(ContentType.JSON)
//                .when()
//                .delete("/companies/1");
//
//        Assert.assertNull(.companyList.stream().filter(company -> company.getId() == 1).findFirst().orElse(null));
//    }

    @Test
    public void should_get_employees_by_company_id_success() {

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/companies/1/employees");

        List<Employee> employeeList = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

    }
}
