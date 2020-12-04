package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.bean.Department;
import com.crud.dao.DepartmentMapper;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper DepartmentMapper;
	@Override
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		return DepartmentMapper.selectByExample(null);
	}

}
