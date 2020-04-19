package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.ParkingBoyRepository;
import io.restassured.http.ContentType;
import io.restassured.mapper.TypeRef;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingBoyControllerTest {

    @Autowired
    private ParkingBoyController parkingBoyController;
    @Autowired
    private ParkingBoyRepository parkingBoyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private List<ParkingBoy> parkingBoyList;
    private List<Employee> employeeList;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(parkingBoyController);
        parkingBoyRepository.save(new ParkingBoy(1,"Jay",null));
        parkingBoyRepository.save(new ParkingBoy(2,"Ming",null));
        employeeRepository.save(new Employee(1,"Jay",23,"Female",null));
        employeeRepository.save(new Employee(2,"Ming",23,"Female",null));


    }

    @Test
    public void should_get_all_parking_boys() {

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/parking-boys");
        List<ParkingBoy> parkingBoyList = response.getBody().as(new TypeRef<List<ParkingBoy>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        Assert.assertEquals(2, parkingBoyList.size());
        Assert.assertEquals(23,parkingBoyList.get(0).getEmployee().getAge().intValue());
    }

    @Test
    public void should_create_parking_boy() {
        ParkingBoy parkingBoy = new ParkingBoy(null,"Test",null);
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(parkingBoy)
                .when()
                .post("/parking-boys");

        ParkingBoy targetParkingBoy =response.getBody().as(ParkingBoy.class);

        Assert.assertEquals("Test",targetParkingBoy.getNickName());
    }

    @Test
    public void should_get_parking_boy_by_id() {

        MockMvcResponse response = given().contentType(ContentType.JSON)
                .when()
                .get("/parking-boys/1");
        ParkingBoy parkingBoy = response.getBody().as(ParkingBoy.class);
        Assert.assertEquals("Jay",parkingBoy.getNickName());
    }

    @Test
    public void should_update_parking_boy_info_by_id() {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.setNickName("Test");
        MockMvcResponse response = given().contentType(ContentType.JSON)
                .body(parkingBoy)
                .when()
                .put("/parking-boys/1");
        Assert.assertEquals("Test", parkingBoyRepository.findById(1).get().getNickName());
    }



}
