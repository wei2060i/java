package com.${projectName}.service.mapstruct;

import com.${projectName}.entity.${domain?cap_first}Entity;
import com.${projectName}.service.dto.${domain?cap_first}Dto;
import com.${projectName}.util.basemapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author sky
* @date 2022/2/6 15:13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ${domain?cap_first}Mapper extends BaseMapper<${domain?cap_first}Dto, ${domain?cap_first}Entity> {


}