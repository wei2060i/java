package com.repository;

import com.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    @Query(value = "select id,department_name from SSSP_DEPARTMENTS",nativeQuery = true)
    List<Department> getAll();
}
