package com.skyadmin.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
* @author sky
* @date 2022/2/6 15:12
*/
@Data
public class UserRoleDto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}