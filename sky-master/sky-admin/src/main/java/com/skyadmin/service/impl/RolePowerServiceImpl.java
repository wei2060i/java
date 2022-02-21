package com.skyadmin.service.impl;

import com.skyadmin.repository.RolePowerRepository;
import com.skyadmin.service.RolePowerService;
import com.skyadmin.service.mapstruct.RolePowerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class RolePowerServiceImpl implements RolePowerService {
    private static final Logger log = LoggerFactory.getLogger(RolePowerServiceImpl.class);

    @Resource
    private RolePowerMapper rolePowerMapper;
    @Resource
    private RolePowerRepository rolePowerRepository;



}