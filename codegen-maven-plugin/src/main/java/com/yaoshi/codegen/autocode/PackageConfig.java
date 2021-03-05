package com.yaoshi.codegen.autocode;

public class PackageConfig {

    private final String basePackage;

    public static Builder custom() {
        return new Builder();
    }

    public PackageConfig(Builder builder) {
        this.basePackage = builder.basePackage;
    }

    public static class Builder {
        private String basePackage;

        public Builder setBasePackage(String basePackage) {
            this.basePackage = basePackage;
            return this;
        }

        public PackageConfig build() {
            return new PackageConfig(this);
        }
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public String getBasePackageDictory() {
        return basePackage.replace(".", "/") + "/";
    }
}
