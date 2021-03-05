package com.yaoshi.codegen.autocode.table.convert;


import com.yaoshi.codegen.autocode.table.DbColumnType;
import com.yaoshi.codegen.autocode.table.TableField;

public interface FieldTypeConvert {

    DbColumnType fieldTypeConvert(String columnType);

    /**
     * 填充整数位和小数位
     *
     * @param tableField
     * @return
     */
    TableField fieldTypeLengthAndFraction(TableField tableField, String fieldType);
}
