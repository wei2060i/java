package com.skyadmin.service.mapstruct;

import com.skyadmin.entity.UserRoleEntity;
import com.skyadmin.service.dto.UserRoleDto;
import com.skyadmin.util.basemapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author sky
* @date 2022/2/6 15:13
*/
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper extends BaseMapper<UserRoleDto, UserRoleEntity> {


}