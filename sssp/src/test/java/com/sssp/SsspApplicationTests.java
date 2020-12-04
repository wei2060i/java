package com.sssp;

import com.controller.EmployeeController;
import com.entity.Department;
import com.entity.Employee;
import com.repository.DepartmentRepository;
import com.repository.EmployeeRepository;
import com.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsspApplicationTests {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    public void contextLoads() {
        Employee byId = employeeRepository.getById(2);
        System.out.println(byId);
    }
    @Test
    public void testPage(){
        employeeService.testJpaSpecificationExecutor();
    }
}
