package com.skyadmin.service.impl;

import com.skyadmin.repository.UserRoleRepository;
import com.skyadmin.service.UserRoleService;
import com.skyadmin.service.mapstruct.UserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    private static final Logger log = LoggerFactory.getLogger(UserRoleServiceImpl.class);

    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserRoleRepository userRoleRepository;



}