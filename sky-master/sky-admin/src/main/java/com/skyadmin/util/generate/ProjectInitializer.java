package com.skyadmin.util.generate;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * @author 代码生成
 * @date 2022/2/6 0:17
 */
public class ProjectInitializer {

    public static void main(String[] args) throws Exception {
        String configPath = "config.json";
        String config = GenerateUtils.readJsonFile(configPath);
        if (config == null) {
            System.out.println("请完善config.json");
            return;
        }
        JSON json = JSON.parseObject(config);
        System.out.println("config:" + json.toJSONString());
        Config conf = json.toJavaObject(Config.class);
        GenerateUtils.projectName = conf.getProjectName();
        GenerateUtils.projectPath = conf.getProjectPath();
        GenerateUtils.moduleDomainMap = conf.getModuleMap();
        GenerateUtils.generatedBlocks = new ArrayList<GenerateUtils.Block>() {{
            add(GenerateUtils.Block.DAO);
            add(GenerateUtils.Block.SERVICE_API);
            add(GenerateUtils.Block.SERVICE_IMPL);
            add(GenerateUtils.Block.WEB_API);
        }};
        GenerateUtils.projectPackages = conf.getProjectPackages();
        GenerateUtils.generatedBaseEntity();

        for (String currentModule : conf.getModuleMap().keySet()) {
            GenerateUtils.currentModule = currentModule;
            GenerateUtils.generateDAO();
            GenerateUtils.generateService();
            GenerateUtils.generateApi();
        }
    }

}
