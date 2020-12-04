package com.mybatis_study.dao;

import java.util.List;

import com.mybatis_study.bean.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * @author sky
 */
public interface EmployeeMapperDynamicSQL {

    //携带了哪个字段查询条件就带上这个字段的值

    List<Employee> getEmployeesByConditionIf(Employee employee);

    List<Employee> getEmployeeByConditionTrim(Employee employee);

    List<Employee> getEmpByConditionChoose(Employee employee);

    void updateEmp(Employee employee);

    //查询员工id 在给定集合中的

    List<Employee> getEmpByConditionForeach(@Param("ids") List<Integer> ids);

    void addEmployees(@Param("employees") List<Employee> employees);

    void addEmployeesOracle(@Param("employees") List<Employee> employees);

}
