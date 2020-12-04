package com.repository;

import com.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

/*
    @Query(value = "select count(1) from employee",nativeQuery = true)
    public long getCount();

    @Query("select o from Employee o where o.name like %:name%")
    public List<Employee> queryLike1(@Param("name") String name);

    @Query("select o from Employee o where o.name like %?1%")
    public List<Employee> queryLike(String name);

    @Query("select o from Employee o where o.name=:name and o.age=:age")
    public List<Employee> queryParam(@Param("name") String name,@Param("age") Integer age);

    @Query("select o from Employee o where o.name=?1 and o.age=?2")
    public List<Employee> queryParams(String name,Integer age);

    @Query("select o from Employee o")
    public List<Employee> getEmployees();  */

    Employee getByLastName(String lastName);
    Employee getById(Integer id);
}
