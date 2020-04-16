package com.thoughtworks.springbootemployee.controller;

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
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {

    @Autowired
    private EmployeeController employeeController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(employeeController);
        employeeController.employees = new ArrayList<>(Arrays.asList(
                new Employee(1, "Jay", 10, "Male"),
                new Employee(2, "Andy", 20, "Male"),
                new Employee(3, "Leo", 30, "Male"),
                new Employee(4, "Wesley", 40, "Male"),
                new Employee(5, "Hilary", 50, "Female")));
    }

    @Test
    public void should_get_employees_by_id_success() {
        // given...
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/employees/1");

        // then...
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        Employee employee = response.getBody().as(Employee.class);
        Assert.assertEquals(1, employee.getId());
        Assert.assertEquals("Jay", employee.getName());

    }

    @Test
    public void should_find_employee_by_gender_success() {
        // given...
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .param("gender", "Male")
                .when()
                .get("/employees");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Employee> employeeList = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        Assert.assertEquals(4, employeeList.size());
        Assert.assertEquals("Jay", employeeList.get(0).getName());

    }

    @Test
    public void should_return_employee_when_given_page_and_size_success() {
        // given...
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .param("page","1")
                .param("pageSize","3")
                .when()
                .get("/employees");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        List<Employee> employeeList = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(3,employeeList.size());
    }

    @Test
    public void should_add_employee_success() {
        Employee employee = new Employee(6,"Peng",60,"Male");
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .post("/employees");

        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });

        Assert.assertEquals(6,employees.size());
        Assert.assertEquals("Peng",employees.get(5).getName());
    }

    @Test
    public void should_remove_employee_success() {
        MockMvcResponse response =given().contentType(ContentType.JSON)
                .when()
                .delete("/employees/1");
        Assert.assertEquals(HttpStatus.ACCEPTED.value(), response.getStatusCode());

        List<Employee> employees = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(4,employees.size());
    }

    @Test
    public void should_update_employee_success() {
        // given...
        Employee employee = new Employee(1,"JayJay",10,"Female");
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(employee)
                .when()
                .put("/employees/1");
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        List<Employee> employeeList = response.getBody().as(new TypeRef<List<Employee>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals("JayJay",employeeList.stream().filter(employee1 -> employee1.getId()==1).findFirst().get().getName());
    }
}