package com.skyadmin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* @author sky
*/
@Entity
@Table(name = "t_role")
@org.hibernate.annotations.Table(appliesTo = "t_role", comment = "角色表")
@Getter
@Setter
public class RoleEntity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "varchar(64) COMMENT '角色名称'")
    private String roleName;

    @Column(columnDefinition = "varchar(64) COMMENT '角色描述'")
    private String roleDesc;

}