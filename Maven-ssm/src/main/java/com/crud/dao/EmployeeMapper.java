package com.crud.dao;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);
    
    List<Employee> selectByExampleWithDept(EmployeeExample example);
    Employee selectByPrimaryKeyWithDept(Integer empId);
    int updateByExampleSelective(@Param("record") Employee record,@Param("record") EmployeeExample example);

    int updateByExample(Employee record,EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
}