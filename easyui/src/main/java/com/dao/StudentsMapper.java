package com.dao;

import com.bean.Students;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentsMapper {

	@Select("select * from student limit #{start},#{pagesize}")
	List<Students> queryStuByPage(@Param("start") Integer start,@Param("pagesize") Integer pagesize);
	@Select("SELECT COUNT(*) from student")
	Integer queryCountStu();
	@Update("update student set name=#{name},remark=#{remark},sort=#{sort} where id=#{id}")
    int updateStu(Students student);
	@Delete("delete from student where id=#{id}")
    int deleteStu(Integer id);

    int addStu(Students students);
}
