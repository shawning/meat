package com.yaoshi.codegen.autocode;


import com.yaoshi.codegen.autocode.table.convert.FieldTypeConvert;
import com.yaoshi.codegen.autocode.table.convert.MysqlFieldTypeConvert;

public class DataSourceConfig {
    public static Builder custom() {
        return new Builder();
    }

    private final String driverName;
    private final String userName;
    private final String password;
    private final String url;
    private final String specified;//指定需要生成的表格
    private DbType dbType;
    private FieldTypeConvert fieldTypeConvert;

    public DataSourceConfig(Builder builder) {
        this.driverName = builder.driverName;
        this.userName = builder.userName;
        this.password = builder.password;
        this.url = builder.url;
        this.specified = builder.specified;
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return this.url;
    }

    public String getSpecified() {
        return specified;
    }

    public DbType getDbType() {
        if (dbType != null) {
            return dbType;
        }
        if (this.driverName.contains("mysql")) {
            dbType = DbType.MYSQL;
        }
        return dbType;
    }


    public static class Builder {
        private String driverName;
        private String userName;
        private String password;
        private String url;
        private String specified;//指定需要生成的表格

        public Builder setDriverName(String driverName) {
            this.driverName = driverName;
            return this;
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setSpecified(String specified) {
            this.specified = specified;
            return this;
        }

        public DataSourceConfig build() {
            return new DataSourceConfig(this);
        }
    }


    /**
     * 字段转换
     *
     * @return
     */
    public FieldTypeConvert getFieldTypeConvert() {
        if (fieldTypeConvert != null) {
            return fieldTypeConvert;
        }
        switch (dbType) {
            case MYSQL:
                fieldTypeConvert = MysqlFieldTypeConvert.build();
                break;
            default:
                break;
        }
        return fieldTypeConvert;
    }
}
