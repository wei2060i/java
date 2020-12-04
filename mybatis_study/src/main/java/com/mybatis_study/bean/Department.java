package com.mybatis_study.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author sky
 * @date 2020/12/3 13:40
 */
@Data
public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    private List<Employee> employees;
}
