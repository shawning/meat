package com.yaoshi.codegen.autocode.util;

public class TableUtil {
    public static String firstToLowerCase(String param) {
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toLowerCase());
        sb.append(param.substring(1));
        return sb.toString();
    }

    public static String firstToUpperCase(String param) {
        StringBuilder sb = new StringBuilder(param.length());
        sb.append(param.substring(0, 1).toUpperCase());
        sb.append(param.substring(1));
        return sb.toString();
    }
}
