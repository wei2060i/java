package com.skyadmin.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author sky
* @date 2022/2/6 16:25
*/
@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping("/role")
public class RoleApi {

    private static final Logger logger = LoggerFactory.getLogger(RoleApi.class);




}