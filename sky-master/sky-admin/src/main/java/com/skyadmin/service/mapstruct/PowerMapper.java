package com.skyadmin.service.mapstruct;

import com.skyadmin.entity.PowerEntity;
import com.skyadmin.service.dto.PowerDto;
import com.skyadmin.util.basemapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author sky
* @date 2022/2/6 15:13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PowerMapper extends BaseMapper<PowerDto, PowerEntity> {


}