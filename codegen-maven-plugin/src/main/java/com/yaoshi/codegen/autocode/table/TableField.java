package com.yaoshi.codegen.autocode.table;

import com.yaoshi.codegen.autocode.util.TableUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liusy
 */
public class TableField implements Serializable {
    /*
       实体名称 第一个字母大写
     */
    private String entityName;
    /*
       属性名称 第一个字母小写
     */
    private String propsName;
    /*
       属性名称 数据库字段
     */
    private String name;
    /*
       字段注释
    */
    private String comment;
    /*
       字段类型
    */
    private DbColumnType dbColumnType;
    /*
       字段是否必填
    */
    private Boolean required;
    /**
     * 字段整数位
     */
    private Integer integerLength = 0;
    /**
     * 字段小数位
     */
    private Integer fraction = 0;

    /**
     * 选项值类型
     */
    private OptionType optionType = OptionType.NONE;

    /**
     * 字段选项值
     */
    private List<FieldOption> options = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        String[] splitToList = name.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(name.length());
        for (int i = 0; i < splitToList.length; i++) {
            sb.append(TableUtil.firstToUpperCase(splitToList[i]));
        }
        setEntityName(sb.toString());
        setPropsName(TableUtil.firstToLowerCase(sb.toString()));
        this.name = name;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(OptionType optionType) {
        this.optionType = optionType;
    }

    public String getPropsName() {
        return this.propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public DbColumnType getDbColumnType() {
        return this.dbColumnType;
    }

    public void setDbColumnType(DbColumnType dbColumnType) {
        this.dbColumnType = dbColumnType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Integer getIntegerLength() {
        return integerLength;
    }

    public void setIntegerLength(Integer integerLength) {
        this.integerLength = integerLength;
    }

    public Integer getFraction() {
        return fraction;
    }

    public void setFraction(Integer fraction) {
        this.fraction = fraction;
    }

    public List<FieldOption> getOptions() {
        return options;
    }

    public void setOptions(List<FieldOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "TableField{" +
                "entityName='" + entityName + '\'' +
                ", propsName='" + propsName + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", dbColumnType=" + dbColumnType +
                ", required=" + required +
                ", integerLength=" + integerLength +
                ", fraction=" + fraction +
                '}';
    }
}
