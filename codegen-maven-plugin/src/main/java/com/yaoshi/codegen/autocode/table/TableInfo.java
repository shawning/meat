package com.yaoshi.codegen.autocode.table;

import com.yaoshi.codegen.autocode.util.TableUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liusy
 */
public class TableInfo {
    private String entityName;
    private String lowEntityName;
    private String lowEntityNameMiddleLine;
    private String propsName;
    private String name;
    private String comment;
    private List<TableField> fields = new ArrayList<>();

    /*
    是否高级查询
     */
    private Boolean advanced = false;
    /*
    普通查询字段
     */
    private List<TableField> commonFields = new ArrayList<>();
    /*
    高级查询字段
     */
    private List<TableField> advancedFields = new ArrayList<>();
    /*
    下拉框字段
     */
    private List<TableField> selectfields = new ArrayList<>();


    public List<TableField> getSelectfields() {
        return selectfields;
    }

    public void setSelectfields(List<TableField> selectfields) {
        this.selectfields = selectfields;
    }

    public Boolean getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Boolean advanced) {
        this.advanced = advanced;
    }

    public List<TableField> getCommonFields() {
        return commonFields;
    }

    public void setCommonFields(List<TableField> commonFields) {
        this.commonFields = commonFields;
    }

    public List<TableField> getAdvancedFields() {
        return advancedFields;
    }

    public void setAdvancedFields(List<TableField> advancedFields) {
        this.advancedFields = advancedFields;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setName(String name, String strategyConfig) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        String replace = name.replaceFirst(strategyConfig, "");
        String[] split = replace.toLowerCase().split("_");
        StringBuilder sb = new StringBuilder(replace.length());
        for (String s : split) {
            sb.append(TableUtil.firstToUpperCase(s));
        }
        setEntityName(sb.toString());
        setPropsName(TableUtil.firstToLowerCase(sb.toString()));
        setLowEntityNameMiddleLine(replace.toLowerCase().replace("_", "-"));
        this.name = name;
    }


    public String getLowEntityNameMiddleLine() {
        return lowEntityNameMiddleLine;
    }

    public void setLowEntityNameMiddleLine(String lowEntityNameMiddleLine) {
        this.lowEntityNameMiddleLine = lowEntityNameMiddleLine;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<TableField> getFields() {
        return fields;
    }

    public void setFields(List<TableField> fields) {
        this.fields = fields;
    }

    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    public String getLowEntityName() {
        return lowEntityName;
    }

    public void setLowEntityName(String lowEntityName) {
        this.lowEntityName = lowEntityName;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "entityName='" + entityName + '\'' +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", fields=" + fields +
                ", propsName=" + propsName +
                '}';
    }
}
