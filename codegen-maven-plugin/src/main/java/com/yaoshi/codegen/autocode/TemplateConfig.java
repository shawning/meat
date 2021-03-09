package com.yaoshi.codegen.autocode;

import com.yaoshi.codegen.autocode.table.TableInfo;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateConfig {

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {
        private GlobalConfig globalConfig;
        private PackageConfig packageConfig;
        private List<TableInfo> tableInfoList;
        private String templateLoaderPath;

        Builder tableInfoList(List<TableInfo> tableInfoList) {
            this.tableInfoList = tableInfoList;
            return this;
        }

        Builder packageInfo(PackageConfig packageConfig) {
            this.packageConfig = packageConfig;
            return this;
        }

        Builder globalConfig(GlobalConfig globalConfig) {
            this.globalConfig = globalConfig;
            return this;
        }

        Builder templateLoading(String templateLoaderPath) {
            this.templateLoaderPath = templateLoaderPath;
            return this;
        }

        void execute() {
            try {
//                URL resource = AutoCodeGenerate.class.getResource(this.templateLoaderPath);
//                File file = new File(resource.getPath());  // will fail if not resolvable in the file system
//                System.out.println("Template loader path [" + resource.getPath() + "] resolved to file path [" + file.getAbsolutePath() + "]");
//                File[] ftlList = file.listFiles();
                //创建一个合适的Configration对象
                Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
                //configuration.setDirectoryForTemplateLoading(file);
                configuration.setClassForTemplateLoading(this.getClass(), "/META-INF/tpl");
                configuration.setObjectWrapper(new DefaultObjectWrapper(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
                configuration.setDefaultEncoding("UTF-8");
                String outputDir = this.globalConfig.getOutputDir();
                String[] ftlList = {"Controller.javaf", "Mapper.javaf", "Mapper.xmlf", "Entity.javaf", "Service.javaf", "ServiceImpl.javaf",
                        "Vo.javaf", "List.vuef", "Form.vuef", "Api.jsf", "Menu.jsf"};
                //获取或创建一个模版。
                for (TableInfo table : tableInfoList) {
                    for (String ftl : ftlList) {
                        Template template = configuration.getTemplate(ftl);
                        Writer writer = null;
                        String ftlName = ftl;
                        try {
                            if (ftl.endsWith(".javaf")) {
                                String basePackageDictory = this.packageConfig.getBasePackageDictory();
                                String filePath = outputDir + "code/java/" + basePackageDictory + ftlName.replace(".javaf", "").toLowerCase() + "/";
                                if (ftlName.endsWith("Impl.javaf")) {
                                    filePath = outputDir + "code/java/" + basePackageDictory + ftlName.replace("Impl.javaf", "").toLowerCase() + "/impl/";
                                }

                                File dicFile = new File(filePath);
                                if (!dicFile.exists()) {
                                    dicFile.mkdirs();
                                }
                                if (ftlName.startsWith("Entity")) {
                                    ftlName = ftlName.replace("Entity", "");
                                }
                                writer = new OutputStreamWriter(new FileOutputStream(filePath + table.getEntityName() + ftlName.substring(0, ftlName.length() - 1)),
                                        StandardCharsets.UTF_8);
                            } else if (ftl.endsWith(".xmlf")) {
                                String filePath = outputDir + "code/xml/";
                                File dicFile = new File(filePath);
                                if (!dicFile.exists()) {
                                    dicFile.mkdirs();
                                }
                                writer = new OutputStreamWriter(new FileOutputStream(filePath + table.getEntityName() + ftlName.substring(0, ftlName.length() - 1)),
                                        StandardCharsets.UTF_8);
                            } else if (ftl.endsWith(".vuef")) {
                                if ("Form.vuef".equals(ftlName)) {
                                    String filePath = outputDir + "code/vue/" + table.getPropsName() + "/modules/";
                                    File dicFile = new File(filePath);
                                    if (!dicFile.exists()) {
                                        dicFile.mkdirs();
                                    }
                                    writer = new OutputStreamWriter(new FileOutputStream(filePath + table.getEntityName() + ftlName.substring(0, ftlName.length() - 1)),
                                            StandardCharsets.UTF_8);
                                } else {
                                    String filePath = outputDir + "code/vue/" + table.getPropsName() + "/";
                                    File dicFile = new File(filePath);
                                    if (!dicFile.exists()) {
                                        dicFile.mkdirs();
                                    }
                                    writer = new OutputStreamWriter(new FileOutputStream(filePath + table.getEntityName() + ftlName.substring(0, ftlName.length() - 1)),
                                            StandardCharsets.UTF_8);
                                }
                            } else if (ftl.endsWith(".jsf")) {
                                String filePath = outputDir + "code/vue/api/";
                                if ("Menu.jsf".equals(ftlName)) {
                                    filePath = outputDir + "code/vue/menu/";
                                }
                                File dicFile = new File(filePath);
                                if (!dicFile.exists()) {
                                    dicFile.mkdirs();
                                }
                                writer = new OutputStreamWriter(new FileOutputStream(filePath + table.getPropsName() + ftlName.substring(0, ftlName.length() - 1)),
                                        StandardCharsets.UTF_8);
                            }
                            Map<String, Object> map = new HashMap<>(4);
                            map.put("tbl", table);
                            map.put("basePackage", this.packageConfig.getBasePackage());
                            map.put("author", this.globalConfig.getAuthor());
                            map.put("mainGroup", this.globalConfig.getMainGroup());
                            template.process(map, writer);
                        } catch (TemplateException | IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (writer != null) {
                                writer.close();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

