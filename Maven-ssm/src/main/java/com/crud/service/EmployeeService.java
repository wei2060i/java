package com.crud.service;

import java.util.List;

import com.crud.bean.Employee;

public interface EmployeeService {

	List<Employee> getAll();

	void saveEmp(Employee employee);

	boolean checkUser(String empName);

	Employee getEmp(Integer id);

	void updateEmp(Employee employee);

	void deleteEmp(Integer id);

	void deleteBatch(List<Integer> ids);


}
