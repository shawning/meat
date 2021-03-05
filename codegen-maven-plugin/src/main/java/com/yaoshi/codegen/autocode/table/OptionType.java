package com.yaoshi.codegen.autocode.table;

public enum OptionType {
    NONE(0), //无
    INT(1), //数字
    STR(2);//字符串

    private Integer type;

    OptionType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
