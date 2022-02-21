package com.${projectName}.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
* @author sky
*/
@Entity
@Table(name = "t_${domain?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}")
@org.hibernate.annotations.Table(appliesTo = "t_${domain?replace("([a-z])([A-Z]+)","$1_$2","r")?lower_case}", comment = "${domainName}")
@Getter
@Setter
public class ${domain?cap_first}Entity extends BaseEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(columnDefinition = "bigint unsigned COMMENT '主键'")
    private Long id;

}