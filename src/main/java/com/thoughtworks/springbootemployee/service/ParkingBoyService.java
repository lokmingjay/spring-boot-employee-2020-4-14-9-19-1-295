package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.ParkingBoy;
import com.thoughtworks.springbootemployee.repository.ParkingBoyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ParkingBoyService {


    @Autowired
    private ParkingBoyRepository repository;

    public List<ParkingBoy> findAll() {
        return repository.findAll();
    }

    public ParkingBoy addParkingBoy(ParkingBoy parkingBoy) {
        return repository.save(parkingBoy);
    }

    public ParkingBoy findById(Integer parkingBoyId) {
        return repository.findById(parkingBoyId).orElse(null);
    }

    public void updateName(Integer parkingBoyId, String nickName) {
        repository.updateName(parkingBoyId,nickName);
    }

    public List<ParkingBoy> findByPage(Integer page, Integer pageSize) {
        return repository.findAll(PageRequest.of(page,pageSize)).getContent();
    }

    public void deleteParkingBoy(Integer parkingBoyId) {
        repository.deleteById(parkingBoyId);
    }
}
