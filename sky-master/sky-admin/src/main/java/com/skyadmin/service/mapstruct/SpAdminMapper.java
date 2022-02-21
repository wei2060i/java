package com.skyadmin.service.mapstruct;

import com.skyadmin.entity.SpAdminEntity;
import com.skyadmin.service.dto.SpAdminDto;
import com.skyadmin.util.basemapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author sky
* @date 2022/2/6 15:13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SpAdminMapper extends BaseMapper<SpAdminDto, SpAdminEntity> {


}