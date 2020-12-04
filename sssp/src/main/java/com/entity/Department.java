package com.entity;


import javax.persistence.*;
import java.io.Serializable;


@Table(name = "SSSP_DEPARTMENTS")
@Entity
public class Department implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String departmentName;
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    @Override
    public String toString(){
        return "id="+id+"departmentName="+departmentName;
    }
}
