package com.skyadmin.service.impl;

import com.skyadmin.repository.SpAdminRepository;
import com.skyadmin.service.SpAdminService;
import com.skyadmin.service.mapstruct.SpAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class SpAdminServiceImpl implements SpAdminService {
    private static final Logger log = LoggerFactory.getLogger(SpAdminServiceImpl.class);

    @Resource
    private SpAdminMapper spAdminMapper;
    @Resource
    private SpAdminRepository spAdminRepository;



}