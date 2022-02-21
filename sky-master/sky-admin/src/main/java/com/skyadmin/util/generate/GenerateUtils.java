package com.skyadmin.util.generate;

import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成工具
 *
 * @author sky
 * @date 2022/2/6 0:18
 */
public class GenerateUtils {
    public static String projectPath;
    public static String projectName;
    public static String currentModule;
    public static String projectPackages;
    public static Map<String, Map<String, String>> moduleDomainMap;
    public static List<Block> generatedBlocks;

    public static String commonModulePackage() {
        return projectPath + File.separator + projectName + File.separator + projectPackages;
    }

    public static String daoModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "entity";
    }

    public static String repositoryModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "repository";
    }

    public static String serviceModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "service";
    }

    public static String serviceImplModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "service" + File.separator + "impl";
    }

    public static String mapStructModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "service" + File.separator + "mapstruct";
    }

    public static String dtoModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "service" + File.separator + "dto";
    }

    public static String apiModulePackage() {
        return commonModulePackage() + File.separator + projectName.replace("-", "") + File.separator + "api";
    }

    public static void generatedBaseEntity() throws Exception {
        String templateName = "template/entity/BaseEntity.ftl";
        String suffix = ".java";
        generateFile(templateName, daoModulePackage(), "BaseEntity" + suffix, null, null);
    }

    public static void generateDAO() throws Exception {
        Map<String, String> domainIdMap = domainIdMap();
        for (String domain : domainIdMap.keySet()) {
            String suffix = "Entity.java";
            String domainName = domainIdMap.get(domain);
            String templateName = "template/entity/DomainLong.ftl";
            generateFile(templateName, daoModulePackage(), domain + suffix, domain, domainName);
            templateName = "template/entity/Repository.ftl";
            suffix = "Repository.java";
            generateFile(templateName, repositoryModulePackage(), domain + suffix, domain, domainName);
        }
    }

    public static void generateService() throws Exception {
        Map<String, String> domainIdMap = domainIdMap();
        for (String domain : domainIdMap.keySet()) {
            String suffix = "Service.java";
            String domainName = domainIdMap.get(domain);
            String templateName = "template/service/Service.ftl";
            generateFile(templateName, serviceModulePackage(), domain + suffix, domain, domainName);

            templateName = "template/service/ServiceImpl.ftl";
            suffix = "ServiceImpl.java";
            generateFile(templateName, serviceImplModulePackage(), domain + suffix, domain, domainName);

            templateName = "template/service/MapStruct.ftl";
            suffix = "Mapper.java";
            generateFile(templateName, mapStructModulePackage(), domain + suffix, domain, domainName);

            templateName = "template/service/Dto.ftl";
            suffix = "Dto.java";
            generateFile(templateName, dtoModulePackage(), domain + suffix, domain, domainName);
        }
    }

    public static void generateApi() throws Exception {
        Map<String, String> domainIdMap = domainIdMap();
        for (String domain : domainIdMap.keySet()) {
            String suffix = "Api.java";
            String domainName = domainIdMap.get(domain);
            String templateName = "template/api/api.ftl";
            generateFile(templateName, apiModulePackage(), domain + suffix, domain, domainName);
        }
    }


    public static void generateFile(String templateFileName, String path, String destFileName, String domain, String domainName) throws Exception {
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        File file = new File(path, destFileName);
        System.out.println(file.exists());
        if (file.exists()) {
            System.out.println("[ " + file.getName() + " ] is existed, ignore the file.");
            return;
        }
        System.out.println("[ " + file.getName() + " ]  start generating.");
        Template template = FreeMarkerTemplateUtils.getTemplate(templateFileName);
        FileOutputStream fos = new FileOutputStream(file);

        Map<String, Object> dataMap = paramsMap();
        dataMap.put("ID", "Long");
        if (domain != null) {
            //首字母小写
            dataMap.put("domain", toLowerCaseFirstOne(domain));
        }
        if (domainName != null) {
            dataMap.put("domainName", domainName);
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        System.out.println("[ " + file.getName() + " ]  end generating.");
    }

    public static Map<String, String> domainIdMap() {
        if (moduleDomainMap != null) {
            return moduleDomainMap.get(currentModule);
        }
        return null;
    }

    public static Map<String, Object> paramsMap() {
        Map<String, Object> dataMap = new HashMap<>(16);
        dataMap.put("projectName", toLowerCaseFirstOne(projectName.replace("-", "")));
        dataMap.put("projectPath", projectPath);
        dataMap.put("domainPackages", projectPackages);
        dataMap.put("modules", moduleDomainMap.keySet());
        dataMap.put("currentModule", currentModule);
        return dataMap;
    }

    /**
     * 首字母转小写
     *
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 读取json文件,返回json串
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public enum Block {
        /**
         * 要生成的模块
         */
        DAO,
        SERVICE_API,
        SERVICE_IMPL,
        WEB_API
    }
}
