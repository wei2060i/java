package com.${projectName}.api;

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
@RequestMapping("/${domain}")
public class ${domain?cap_first}Api {

    private static final Logger logger = LoggerFactory.getLogger(${domain?cap_first}Api.class);




}