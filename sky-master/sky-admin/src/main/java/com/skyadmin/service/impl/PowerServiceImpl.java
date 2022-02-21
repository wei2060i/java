package com.skyadmin.service.impl;

import com.skyadmin.repository.PowerRepository;
import com.skyadmin.service.PowerService;
import com.skyadmin.service.mapstruct.PowerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
 /**
 * @author sky
 * @date 2022/2/6 2:23
 */
@Service
public class PowerServiceImpl implements PowerService {
    private static final Logger log = LoggerFactory.getLogger(PowerServiceImpl.class);

    @Resource
    private PowerMapper powerMapper;
    @Resource
    private PowerRepository powerRepository;



}