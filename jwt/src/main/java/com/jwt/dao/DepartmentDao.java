package com.jwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwt.bean.Department;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends BaseMapper<Department> {

    @Select("SELECT td.dept_name departmentName,td.id FROM tbl_department td  WHERE id=#{id}")
    Department getDepartment(@Param("id") String id);

    @Select("SELECT td.id,td.id S,td.dept_name departmentName FROM tbl_department td WHERE id=#{id}")
    @Results(
            @Result(property ="emps",column = "S",many =@Many(select = "com.jwt.dao.EmpleyeeDao.getEmpleyeeById"))
    )
    Department getDepartmentById(String id);
}
