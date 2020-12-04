package com.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bean.Employee;
import com.mapper.EmployeeMapper;
import com.service.IEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Autowired
    public EmployeeMapper employeeMapper;

    @Autowired
    public IEmployeeService iEmployeeService;
    @Test
    public void contextLoads() {
        System.out.println(33);
    }
    /*
        insert  非空的字段才会才会出现在sql语句里面
        insertAllColumn 无论是否是空 插入索引字段
     */
    @Test
    public void testInsert(){
        Employee employee=new Employee();
        employee.setGender(1);
        employee.setEmail("wu@qq.com");
        employee.setLastName("唐昊1号");
        employee.setAge(3);
        int res = employeeMapper.insert(employee);
        System.out.println(res);
        //插入  自动获取 id 回显
        System.out.println(employee.getId());
    }
    /*
    修改 updateById 会非空判断  是空不参与更新
        updateAllColumnById 空的字段  数据库会把原来的值置空
     */
    @Test
    public void textUpdate(){
        Employee employee=new Employee();
        employee.setId(4);
        employee.setGender(1);
        employee.setEmail("wu@qq.com");
        employee.setLastName("唐昊1号");
        employee.setAge(33);
        int i = employeeMapper.updateById(employee);
        System.out.println(i);
    }
    @Test
    public void textselect(){
     //   Employee employee = employeeMapper.selectById(4);

//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("id",4);
//        Employee employee1 = employeeMapper.selectOne(queryWrapper);

//        List<Integer> list=new ArrayList<>();
//        list.add(2);
//        list.add(4);
//        List<Employee> employees = employeeMapper.selectBatchIds(list);

        //map 参数 条件查询   参数是数据库列名
//        Map<String,Object> map=new HashMap<>();
//        map.put("last_name","唐昊");
//        final List<Employee> employees = employeeMapper.selectByMap(map);

        //Page()分页  查的是所有
        IPage<Employee> employees = employeeMapper.selectPage(new Page<Employee>(), null);
        List<Employee> records = employees.getRecords();
        System.out.println(records);

    }
    @Test
    public void testDelete(){
       //deleteById()   deleteByMap()   deleteBatchIds()
    }
    @Test
    public void selectPage(){
       /* QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("gender",1);
        queryWrapper.between("age",0,30);*/
        QueryWrapper<Employee> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("gender",0).or().between("age",80,100);
        IPage iPage = employeeMapper.selectPage(new Page<Employee>(), queryWrapper);
        List records = iPage.getRecords();
        System.out.println(records);
      //  employeeMapper.update()  employeeMapper.delete()
    }
    @Test
    public void AR(){
        Employee employee=new Employee();
        /*基于对象的增删改查
        employee.setGender(1);
        employee.setEmail("wu@qq.com");
        employee.setLastName("唐昊1号");
        employee.setAge(55);
        boolean bool=employee.insert();
         employee.updateById();
        */
  //      employee.setId(8);
  //      Employee employee1 = employee.selectById();
//      List<Employee> employees = employee.selectAll();
//      employee.selectList(null);
//      boolean b = employee.deleteById(1);
        IPage<Employee> page = employee.selectPage(new Page<Employee>(1, 1), new QueryWrapper<Employee>().like("last_name", "唐昊"));
        System.out.println(page.getRecords());
    }

    @Test
    public void TestPage(){
        IPage<Employee> employeeIPage = employeeMapper.selectPage(new Page<Employee>(1, 2), null);
        List<Employee> records = employeeIPage.getRecords();
        System.out.println(records);
        System.out.println("总条数"+employeeIPage.getTotal());
        System.out.println("当前页码"+employeeIPage.getCurrent());
        System.out.println("总页码"+employeeIPage.getPages());
        System.out.println("每页显示的条数"+employeeIPage.getSize());
    }

    @Test
    public void TestServicePage(){
        IPage<Employee> page = iEmployeeService.page(new Page<Employee>(1, 2));
        List<Employee> records = page.getRecords();
        records.forEach(i->{
            System.out.println(i.toString());
        });
    }

    @Test
    public void TestServiceMap(){
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("gender",11);
        List<Employee> t= (List<Employee>) iEmployeeService.listByMap(columnMap);
        t.forEach(i ->{
            System.out.println(i.toString());
        });
        //查询一条记录
        Map<String, Object> map = iEmployeeService.getMap(null);
        map.forEach((i,j)->{
            System.out.println(i+"-------"+j);
        });
    }

    @Test
    public void TestService(){
        List<Employee>  l= new ArrayList<Employee>();
        Employee e = new Employee();
        e.setGender(11);
        e.setAge(11);
        e.setEmail("22222@23");
        e.setLastName("小舞");
        Employee e1 = new Employee();
        e1.setGender(12);
        e1.setAge(22);
        e1.setEmail("22222@23");
        e1.setLastName("小舞");
        l.add(e);
        l.add(e1);
        boolean b = iEmployeeService.saveBatch(l, 1);
        System.out.println(b);
    }
}
