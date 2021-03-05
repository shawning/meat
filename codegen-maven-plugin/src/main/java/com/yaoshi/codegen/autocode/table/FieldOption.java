package com.yaoshi.codegen.autocode.table;

import java.io.Serializable;

/**
 * @author liusy
 */
public class FieldOption implements Serializable {
    /**
     * 选项值key
     */
    private String optionKey;
    /**
     * 选项值类型
     */
    private String optionName;

    public FieldOption() {
    }

    public FieldOption(String optionKey, String optionName) {
        this.optionKey = optionKey;
        this.optionName = optionName;
    }

    public String getOptionKey() {
        return optionKey;
    }

    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
