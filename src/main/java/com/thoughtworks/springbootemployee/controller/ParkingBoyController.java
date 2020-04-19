package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.service.ParkingBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-boys")
public class ParkingBoyController {

    @Autowired
    private ParkingBoyService service;

    @GetMapping
    public List<ParkingBoy> findAll() {
        return service.findAll();
    }

    @GetMapping(params = {"page","pageSize"})
    public List<ParkingBoy> findByPage(@RequestParam Integer page,
                                       @RequestParam Integer pageSize) {
        return service.findByPage(page,pageSize);
    }

    @GetMapping("/{parkingBoyId}")
    public ParkingBoy findById(@PathVariable("parkingBoyId") Integer parkingBoyId){
        return service.findById(parkingBoyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingBoy createNewParkingBoy(@RequestBody ParkingBoy parkingBoy ) {
        return service.addParkingBoy(parkingBoy);
    }

    @PutMapping("/{parkingBoyId}")
    public void updateParkingBoyInfo(@PathVariable("parkingBoyId") Integer parkingBoyId,@RequestBody ParkingBoy parkingBoy) {
         service.updateName(parkingBoyId,parkingBoy.getNickName());
    }

    @DeleteMapping("/{parkingBoyId}")
    public void deleteParkingBoy(@PathVariable("parkingBoyId") Integer parkingBoyId) {
        service.deleteParkingBoy(parkingBoyId);
    }
}
