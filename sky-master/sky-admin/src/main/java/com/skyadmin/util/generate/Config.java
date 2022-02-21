package com.skyadmin.util.generate;

import lombok.Data;

import java.util.Map;

/**
 * @author sky
 * @date 2022/2/6 0:54
 */
@Data
public class Config {
    private String projectName;
    private String projectPath;
    private String projectPackages;
    private Map<String, Map<String,String>> moduleMap;
}
