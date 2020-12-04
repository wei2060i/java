package com.service;

import com.entity.Employee;
import com.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public void testJpaSpecificationExecutor(){
        Sort.Order order=new Sort.Order(Sort.Direction.ASC,"id");
        Sort sort=new Sort(order);
        //分页
        Pageable pageable=new PageRequest(0,3,sort);
        Specification<Employee> specification=new Specification(){
            @Override
            public Predicate toPredicate(Root root,
                                         CriteriaQuery query,
                                         CriteriaBuilder cb) {
                Path path=root.get("id");
                return cb.gt(path,3);
            }
        };
        Page<Employee> all = employeeRepository.findAll(specification, pageable);
        System.out.println("总页数"+all.getTotalPages());
        System.out.println("当前第几页"+all.getNumber()+1);
        System.out.println("总记录数"+all.getTotalElements());
        System.out.println("当前页面的记录数"+all.getNumberOfElements());
        System.out.println("此页面上的集合"+all.getContent());
    }
    @Transactional(readOnly = true)
    public Employee get(Integer id){
        return employeeRepository.getById(id);
    }
    @Transactional
    public void delete(Integer id){
        Employee  employee= new Employee();
        employee.setId(id);
        employeeRepository.delete(employee);
    }
    @Transactional
    public void save(Employee employee){
        if(employee.getId()==null) {
            //设置创建时间
            employee.setCreateTime(new Date());
        }
        employeeRepository.saveAndFlush(employee);
    }
    @Transactional(readOnly = true)
    public Employee getBByLastName(String lastName){
        return employeeRepository.getByLastName(lastName);
    }
    @Transactional(readOnly = true)
    public Page<Employee> getPage(int pageNo, int pageSize){
        PageRequest pageable=new PageRequest(pageNo-1,pageSize);
        return employeeRepository.findAll(pageable);
    }







}
