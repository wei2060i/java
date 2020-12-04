package com.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crud.dao.DepartmentMapper;
import com.crud.dao.EmployeeMapper;
import com.crud.bean.Department;
import com.crud.bean.Employee;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	private DepartmentMapper Department;
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private SqlSession sqlSession;
	@Test
	public void testCRUD() {
		System.out.println(Department);
		//Department.insertSelective(new Department(null,"开发部"));
		//Department.insertSelective(new Department(null,"测试部"));
		//employeeMapper.insertSelective(new Employee(null,"小舞","M","jert@qq.com",1));
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i <600; i++) {
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
		//	mapper.insertSelective(new Employee(null,uid,"M",uid+"jert@qq.com",1));
		}
	}
}
