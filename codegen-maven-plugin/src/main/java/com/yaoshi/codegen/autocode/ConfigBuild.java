package com.yaoshi.codegen.autocode;

public class ConfigBuild {

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {
        private com.yaoshi.codegen.autocode.GlobalConfig globalConfig;
        private com.yaoshi.codegen.autocode.DataSourceConfig dataSourceConfig;
        private PackageConfig packageConfig;
        private StrategyConfig strategyConfig;
        private String templateLoaderPath;


        public Builder setGlobal(com.yaoshi.codegen.autocode.GlobalConfig globalConfig) {
            this.globalConfig = globalConfig;
            return this;
        }

        public Builder setDataSource(com.yaoshi.codegen.autocode.DataSourceConfig dataSourceConfig) {
            this.dataSourceConfig = dataSourceConfig;
            return this;
        }

        public Builder setPackageInfo(PackageConfig packageConfig) {
            this.packageConfig = packageConfig;
            return this;
        }

        public Builder setStrategy(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
            return this;
        }

        public Builder setTemplate(String templateLoaderPath) {
            this.templateLoaderPath = templateLoaderPath;
            return this;
        }

        public void execute() {
            com.yaoshi.codegen.autocode.Generate.custom()
                    .initGlobal(this.globalConfig)
                    .initJdbc(this.dataSourceConfig)
                    .initTables(this.strategyConfig)
                    .initPackageInfo(this.packageConfig)
                    .initTemplate(this.templateLoaderPath)
                    .execute();

        }
    }
}
