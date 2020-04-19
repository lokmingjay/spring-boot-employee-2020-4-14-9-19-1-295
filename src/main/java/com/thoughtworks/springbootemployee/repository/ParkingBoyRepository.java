package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.ParkingBoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface ParkingBoyRepository extends JpaRepository<ParkingBoy,Integer> {

    @Modifying
    @Query(value ="update parking_boys p set p.nick_name = :nickName where p.id = :parkingBoyId", nativeQuery = true )
    void updateName(@Param("parkingBoyId") Integer parkingBoyId,  @Param("nickName") String nickName);
}
