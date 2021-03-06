package com.yaoshi.codegen.autocode.table;

public enum QuerySQL {
    MYSQL("mysql", "show tables", "show table status", "show full fields from `%s`", "NAME", "COMMENT", "FIELD", "TYPE", "COMMENT", "KEY", "show table status where name in ('%s')", "NULL");
    private final String dbType;
    private final String tablesSql;
    private final String tableCommentsSql;
    private final String tableFieldsSql;
    private final String tableName;
    private final String tableComment;
    private final String fieldName;
    private final String fieldType;
    private final String fieldComment;
    private final String fieldKey;
    private final String tablesSqlSpecified;
    private final String fieldRequired;

    QuerySQL(final String dbType, final String tablesSql, final String tableCommentsSql, final String tableFieldsSql,
             final String tableName, final String tableComment, final String fieldName, final String fieldType,
             final String fieldComment, final String fieldKey, final String tablesSqlSpecified, final String fieldRequired) {
        this.dbType = dbType;
        this.tablesSql = tablesSql;
        this.tableCommentsSql = tableCommentsSql;
        this.tableFieldsSql = tableFieldsSql;
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldComment = fieldComment;
        this.fieldKey = fieldKey;
        this.tablesSqlSpecified = tablesSqlSpecified;
        this.fieldRequired = fieldRequired;
    }

    public String getDbType() {
        return dbType;
    }

    public String getTablesSql() {
        return tablesSql;
    }

    public String getTableCommentsSql() {
        return tableCommentsSql;
    }

    public String getTableFieldsSql() {
        return tableFieldsSql;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public String getFieldKey() {
        return fieldKey;
    }

    public String getTablesSqlSpecified() {
        return tablesSqlSpecified;
    }

    public String getFieldRequired() {
        return fieldRequired;
    }
}
