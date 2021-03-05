package com.yaoshi.codegen.autocode;



import com.yaoshi.codegen.autocode.table.QuerySQL;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liusy
 */

public enum DbType {
    MYSQL("mysql");

    private final String value;
    protected final static Map<DbType, QuerySQL> dbTypes = new HashMap<>();

    static {
        dbTypes.put(DbType.MYSQL, QuerySQL.MYSQL);
    }

    DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
