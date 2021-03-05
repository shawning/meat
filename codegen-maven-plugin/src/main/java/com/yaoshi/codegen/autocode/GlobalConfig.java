package com.yaoshi.codegen.autocode;

/**
 * 全局配置
 * @author liusy
 */
public class GlobalConfig {
    //生成代码目标目录
    private final String outputDir;
    //代码中作者信息
    private final String author;
    //代码maven group
    private final String mainGroup;


    public GlobalConfig(Builder builder) {
        this.outputDir = builder.outputDir;
        this.author = builder.author;
        this.mainGroup = builder.mainGroup;
    }

    public static Builder custom() {
        return new Builder();
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    public String getAuthor() {
        return author;
    }

    public String getMainGroup() {
        return mainGroup;
    }

    public static class Builder {
        private String outputDir;
        private String author;
        private String mainGroup;

        public Builder setOutputDir(String outputDir) {
            if (!outputDir.endsWith("/")) {
                outputDir = outputDir + "/";
            }
            this.outputDir = outputDir;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setMainGroup(String mainGroup) {
            this.mainGroup = mainGroup;
            return this;
        }
        public GlobalConfig build() {
            return new GlobalConfig(this);
        }
    }
}
