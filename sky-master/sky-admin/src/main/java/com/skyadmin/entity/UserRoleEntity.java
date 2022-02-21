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
@Table(name = "t_user_role")
@org.hibernate.annotations.Table(appliesTo = "t_user_role", comment = "用户角色表")
@Getter
@Setter
public class UserRoleEntity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "bigint unsigned COMMENT '角色id'")
    private Long roleId;

    @Column(columnDefinition = "bigint unsigned COMMENT 'uid'")
    private Long uid;
}