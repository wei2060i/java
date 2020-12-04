package com.controller;

import com.entity.Employee;
import com.service.DepartmentService;
import com.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(value = "/emp/{id}",method =RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Integer id){
        employeeService.delete(id);
        return "true";
    }
    @RequestMapping(value = "/emp/{id}",method =RequestMethod.PUT)
    public String update(Employee employee){
        employee.setCreateTime(employeeService.get(employee.getId()).getCreateTime());
        employeeService.save(employee);
        return "redirect:/emps";
    }
    @RequestMapping(value = "/emp/{id}",method =RequestMethod.GET)
    public String input(@PathVariable("id") Integer id, Map<String,Object> map){
        Employee employee = employeeService.get(id);
        map.put("employee",employee);
        map.put("departments",departmentService.getAll());
        return "update";
    }
    @ResponseBody
    @RequestMapping(value = "/ajaxValidateLastName",method = RequestMethod.POST)
    public String validateLastName(@RequestParam(value = "lastName",required = true) String lastName){
        Employee employee= employeeService.getBByLastName(lastName);
        if(employee==null){
            return "0";
        }else{
            return "1";
        }
    }
    @RequestMapping("/add")
    public String Add(Employee employee){
        employeeService.save(employee);
        return "redirect:/emps";
    }
    @RequestMapping(value = "/emp",method = RequestMethod.GET)
    public String input(Map<String,Object> map){
        map.put("departments",departmentService.getAll());
        return "input";
    }
    @RequestMapping("/emps")
    public String list(@RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNoStr,
                       Map<String,Object> map){
        int pageNo=1;
        try{
            pageNo=Integer.valueOf(pageNoStr);
            if(pageNo <1){
                pageNo=1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Page<Employee> page = employeeService.getPage(pageNo, 2);
        map.put("page",page);
        return "list";
    }

}
