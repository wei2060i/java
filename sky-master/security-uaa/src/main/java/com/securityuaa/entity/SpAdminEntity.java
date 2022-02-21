package com.securityuaa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author sky
 */
@Entity
@Table(name = "t_sp_admin")
@org.hibernate.annotations.Table(appliesTo = "t_sp_admin", comment = "管理员表")
@Getter
@Setter
public class SpAdminEntity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "varchar(64) COMMENT '用户名'")
    private String username;

    @Column(columnDefinition = "varchar(128) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "varchar(64) COMMENT '全称'")
    private String fullName;

    @Column(columnDefinition = "varchar(64) COMMENT '手机号'")
    private String mobile;

    @Column(columnDefinition = "bit(1) COMMENT '是否被锁'")
    private Boolean locked;

}