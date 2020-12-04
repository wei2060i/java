package com.mybatis_study.dao;


import com.mybatis_study.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author sky
 */
public interface EmployeeMapper {


    //多条记录封装一个map:Map<Integer,Employee>:键是这条记录的主键,值是记录封装后的javaBean
    //@MapKey:告诉mybatis封装这个map的时候使用哪个属性作为map的key

    @MapKey("id")
    Map<String, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    //返回一条记录的map;key就是列名,值就是对应的值

    Map<String, Object> getEmpByIdReturnMap(Integer id);

    List<Employee> getEmpByLastNameLike(String lastName);

    Employee getEmpById(@Param("ids") List<Integer> ids);

    Employee getEmpByIdAndLastName(Integer id, String lastName);

    Employee getEmployeeById(Integer id);

    Integer addEmp(Employee employee);

    Integer addEmpOracle(Employee employee);

    boolean updateEmp(Employee employee);

    boolean deleteEmpById(Integer id);

    //部门多列传值测试,参数也可以分开写

    List<Employee> getEmployeeByDeptId(Map<String, Object> map);

    List<Employee> getEmployeeTestInnerParameter(Employee employee);
}
