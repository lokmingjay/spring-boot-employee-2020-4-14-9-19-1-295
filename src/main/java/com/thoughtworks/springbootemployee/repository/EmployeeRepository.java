package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByGender(String gender);

    @Modifying
    @Query(value = "update EMPLOYEE e set e.name = :name where e.id = :employeeId", nativeQuery = true)
    void updateName(@Param("employeeId") Integer employeeId, @Param("name") String name);

    @Modifying
    @Query(value = "update EMPLOYEE e set e.company_id = null where e.company_id = :companyId", nativeQuery = true)
    void deleteEmployeeByCompanyId(@Param("companyId") int companyId);

    @Modifying
    @Query(value = "ALTER TABLE EMPLOYEE ALTER COLUMN ID RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
