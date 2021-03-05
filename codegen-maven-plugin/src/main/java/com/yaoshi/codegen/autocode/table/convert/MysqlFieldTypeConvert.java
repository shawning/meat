package com.yaoshi.codegen.autocode.table.convert;


import com.yaoshi.codegen.autocode.table.DbColumnType;
import com.yaoshi.codegen.autocode.table.TableField;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liusy
 */
public class MysqlFieldTypeConvert implements FieldTypeConvert {
    public static MysqlFieldTypeConvert build() {
        return new MysqlFieldTypeConvert();
    }

    @Override
    public DbColumnType fieldTypeConvert(String columnType) {
        String t = columnType.toLowerCase();
        if (t.contains("char") || t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bigint")) {
            return DbColumnType.LONG;
        } else if (t.contains("tinyint")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("int")) {
            return DbColumnType.INTEGER;
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            return DbColumnType.DATE;
        } else if (t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bit")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("decimal")) {
            return DbColumnType.BIG_DECIMAL;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BLOB;
        } else if (t.contains("binary")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("double")) {
            return DbColumnType.DOUBLE;
        } else if (t.contains("json") || t.contains("enum")) {
            return DbColumnType.STRING;
        }
        return DbColumnType.STRING;
    }

    @Override
    public TableField fieldTypeLengthAndFraction(TableField tableField, String fieldType) {
        if (StringUtils.isBlank(fieldType)) {
            tableField.setIntegerLength(0);
            tableField.setFraction(0);
        } else {
            Pattern pattern = Pattern.compile("\\([1-9][0-9]*,?\\d*\\)");
            Matcher matcher = pattern.matcher(fieldType);
            if (matcher.find()) {
                String group = matcher.group();
                String[] split = group.split(",");
                if (split.length == 2) {
                    int integerLength = Integer.parseInt(split[0].replace("(", ""));
                    int fraction = Integer.parseInt(split[1].replace(")", ""));
                    if (integerLength <= fraction){
                        throw new RuntimeException("decimal的小数位不能大于总位数");
                    }
                    tableField.setIntegerLength(integerLength-fraction);
                    tableField.setFraction(fraction);
                } else {
                    tableField.setIntegerLength(Integer.parseInt(split[0].replace("(", "").replace(")", "")));
                }
            } else {
                tableField.setIntegerLength(0);
                tableField.setFraction(0);
            }
        }

        return tableField;
    }
}
