package com.skyadmin.service.impl;

import com.skyadmin.repository.RoleRepository;
import com.skyadmin.service.RoleService;
import com.skyadmin.service.mapstruct.RoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleRepository roleRepository;



}