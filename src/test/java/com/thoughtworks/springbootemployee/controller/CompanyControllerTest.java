package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
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
import static io.restassured.module.mockmvc.RestAssuredMockMvc.reset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyControllerTest {

    @Autowired
    private CompanyController companyController;


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(companyController);

        companyController.employees = new ArrayList<>(Arrays.asList(
                new Employee(1, "Jay", 10, "Male"),
                new Employee(2, "Andy", 20, "Male"),
                new Employee(3, "Leo", 30, "Male"),
                new Employee(4, "Wesley", 40, "Male"),
                new Employee(5, "Hilary", 50, "Female")));

        companyController.companyList = new ArrayList<>(Arrays.asList(
                new Company("A", 5, companyController.employees, 1),
                new Company("B", 5, companyController.employees, 2),
                new Company("C", 5, companyController.employees, 3)
        ));


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
}
