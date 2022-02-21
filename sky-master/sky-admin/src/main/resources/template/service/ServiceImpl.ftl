package com.${projectName}.service.impl;

import com.${projectName}.repository.${domain?cap_first}Repository;
import com.${projectName}.service.${domain?cap_first}Service;
import com.${projectName}.service.mapstruct.${domain?cap_first}Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class ${domain?cap_first}ServiceImpl implements ${domain?cap_first}Service {
    private static final Logger log = LoggerFactory.getLogger(${domain?cap_first}ServiceImpl.class);

    @Resource
    private ${domain?cap_first}Mapper ${domain}Mapper;
    @Resource
    private ${domain?cap_first}Repository ${domain}Repository;



}