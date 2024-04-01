package com.coldie.mysqljpaappnew.repository;

import com.coldie.mysqljpaappnew.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContaining(String name, Sort sort);

    List<Employee> findByNameContainingAndLocationContaining(String name, String location);

    @Query("FROM Employee e WHERE e.name = :name OR e.location = :location")
    List<Employee> findEmployeesByNameOrLocation(@Param("name") String name, @Param("location") String location);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.name = :name")
    Integer deleteEmployeeByName(@Param("name") String name);
}
