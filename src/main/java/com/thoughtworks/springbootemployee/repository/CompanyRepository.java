package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Modifying
    @Query(value = "update COMPANY c set c.company_name = :name where c.id = :companyId", nativeQuery = true)
    void updateCompany(@Param("companyId") Integer companyId, @Param("name") String name);

    @Modifying
    @Query(value = "ALTER TABLE COMPANY ALTER COLUMN ID RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
