package com.yaoshi.codegen.autocode.table;

/**
 * @author liusy
 */

public enum DbColumnType {
    // 基本类型
    BASE_INT("int", "INTEGER", null, "input-number", false, true),
    BASE_BOOLEAN("boolean", "BOOLEAN", null, "input-number", false, false),
    BASE_FLOAT("float", "FLOAT", null, "input-number", false, true),
    BASE_DOUBLE("double", "DOUBLE", null, "input-number", false, true),

    // 包装类型
    STRING("String", "VARCHAR", null, "input", false, false),
    LONG("Long", "BIGINT", null, "input-number", false, true),
    INTEGER("Integer", "INTEGER", null, "input-number", false, true),
    FLOAT("Float", "FLOAT", null, "input-number", false, true),
    DOUBLE("Double", "DOUBLE", null, "input-number", false, true),
    BOOLEAN("Integer", "INTEGER", null, "switch", false, true),
    BYTE_ARRAY("byte[]", "BINARY", null, "input", false, false),
    CHARACTER("Character", "VARCHAR", null, "input", false, false),
    OBJECT("Object", "VARCHAR", null, "input", false, false),
    DATE("Date", "TIMESTAMP", "java.util.Date", "date-picker", false, false),
    DATETIME("Datetime", "TIMESTAMP", "java.util.Date", "date-picker", false, false),
    TIME("Time", "TIMESTAMP", "java.sql.Time", "date-picker", false, false),
    BLOB("Blob", "VARCHAR", "java.sql.Blob", "input", false, false),
    CLOB("Clob", "VARCHAR", "java.sql.Clob", "input", false, false),
    TIMESTAMP("Timestamp", "TIMESTAMP", "java.sql.Timestamp", "date-picker", false, false),
    BIG_INTEGER("BigInteger", "BIGINT", "java.math.BigInteger", "input-number", false, true),
    BIG_DECIMAL("BigDecimal", "DECIMAL", "java.math.BigDecimal", "input-number", false, true),
    LOCAL_DATE("LocalDate", "TIMESTAMP", "java.time.LocalDate", "date-picker", false, false),
    LOCAL_TIME("LocalTime", "TIMESTAMP", "java.time.LocalTime", "date-picker", false, false),
    LOCAL_DATE_TIME("LocalDateTime", "TIMESTAMP", "java.time.LocalDateTime", "date-picker", false, false);

    /**
     * 类型
     */
    private final String type;

    /**
     * 包路径
     */
    private final String pkg;

    /*
    ui 元素
     */
    private final String uiElement;

    /*
    是否下拉
     */
    private final Boolean isSelect;

    /*
    mybatisType类型
     */
    private final String mybatisType;
    /*
    小数位状态
     */
    private final Boolean digitsStatus;

    DbColumnType(final String type, final String mybatisType, final String pkg,
                 final String uiElement, final Boolean isSelect, final Boolean digitsStatus) {
        this.type = type;
        this.mybatisType = mybatisType;
        this.pkg = pkg;
        this.uiElement = uiElement;
        this.isSelect = isSelect;
        this.digitsStatus = digitsStatus;
    }

    public String getType() {
        return this.type;
    }

    public String getPkg() {
        return this.pkg;
    }

    public String getUiElement() {
        return uiElement;
    }

    public String getMybatisType() {
        return mybatisType;
    }

    public Boolean getDigitsStatus() {
        return digitsStatus;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }
}
