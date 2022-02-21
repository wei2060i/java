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
@Table(name = "t_power")
@org.hibernate.annotations.Table(appliesTo = "t_power", comment = "权限表")
@Getter
@Setter
public class PowerEntity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

    @Column(columnDefinition = "bigint unsigned COMMENT '父id'")
    private Long pid;

    @Column(columnDefinition = "tinyint(4) COMMENT '权限等级'")
    private Integer level;

    @Column(columnDefinition = "varchar(2048) COMMENT '权限'")
    private String permissions;

    @Column(columnDefinition = "varchar(2048) COMMENT '路由'")
    private String route;

}