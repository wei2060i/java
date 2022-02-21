package com.${projectName}.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
* @author sky
* @date 2022/1/15 18:27
*/
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Getter
    @Setter
    @CreatedDate
    @Column(columnDefinition = "datetime not null comment '创建时间'")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    @Column(columnDefinition = "datetime not null comment '更新时间'")
    private LocalDateTime modifiedAt;

}