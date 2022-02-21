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
@Table(name = "t_role_power")
@org.hibernate.annotations.Table(appliesTo = "t_role_power", comment = "角色权限表")
@Getter
@Setter
public class RolePowerEntity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "bigint unsigned COMMENT '角色id'")
    private Long roleId;

    @Column(columnDefinition = "varchar(2048) COMMENT '权限ids'")
    private String powerIds;

}