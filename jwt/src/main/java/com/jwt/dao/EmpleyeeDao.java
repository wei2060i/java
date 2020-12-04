package com.jwt.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwt.bean.Empleyee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 详情见测试类  测试全注解开发
 */
@Repository
public interface EmpleyeeDao extends BaseMapper<Empleyee> {

    @Select("SELECT te.id,te.last_name,te.gender,te.email,te.dept_id S " +
            "FROM tbl_empleyee te  WHERE id=#{id}")
    @Results(id="test",value = {
            @Result(property = "depart",column = "S",one =@One(select="com.jwt.dao.DepartmentDao.getDepartment",fetchType = FetchType.LAZY))
    })
    Empleyee getEmpleyee(@Param("id") String id);

    @Select("SELECT te.id,te.last_name,te.gender,te.email from tbl_empleyee te where dept_id=#{id}")
    List<Empleyee> getEmpleyeeById(String id);
}
