package com.jwt;

import com.jwt.bean.Department;
import com.jwt.bean.Empleyee;
import com.jwt.dao.DepartmentDao;
import com.jwt.dao.EmpleyeeDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtApplicationTests {
    @Autowired
    private EmpleyeeDao empleyeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @Test
    public void testOneToMany(){
        Department d = departmentDao.getDepartmentById("1184132521349087234");
        System.out.println(d);
    }

    @Test
    public void testOnetoOne(){
        Empleyee empleyee = empleyeeDao.getEmpleyee("1184124732035825665");
        System.out.println(empleyee.toString());
        Department d = departmentDao.getDepartment("1184132521349087234");
        System.out.println(d);
    }
    @Test
    public void contextLoads() {
//      Empleyee empleyee = new Empleyee();
//      empleyee.setLastName("无天").setGender("1").setEmail("119@qq.com").insert();
        Department department=new Department();
   //     department.setDepartmentName("市场部").insert();
        System.out.println(department.toString());
    }

}
