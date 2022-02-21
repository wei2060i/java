package com.${projectName}.service.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
* @author sky
* @date 2022/2/6 15:12
*/
@Data
public class ${domain?cap_first}Dto {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}