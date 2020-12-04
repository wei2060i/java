package com.mybatis_study.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sky
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
	private Integer id;
	private String lastName;
	private String email;
	private Integer gender;
	private Integer dId;
}