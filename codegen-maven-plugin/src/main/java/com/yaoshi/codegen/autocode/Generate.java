package com.yaoshi.codegen.autocode;

import com.yaoshi.codegen.autocode.jdbc.DataSource;
import com.yaoshi.codegen.autocode.jdbc.JDBCUtil;
import com.yaoshi.codegen.autocode.table.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代码创建执行单元
 *
 * @author liusy
 */
public class Generate {
    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {
        private JDBCUtil jdbcTemplate;
        private DataSourceConfig dataSourceConfig;
        private List<TableInfo> tableInfoList;
        private GlobalConfig globalConfig;
        private PackageConfig packageConfig;
        private String templateLoaderPath;

        /**
         * 加载全局配置
         *
         * @param globalConfig
         * @return
         */
        Builder initGlobal(GlobalConfig globalConfig) {
            this.globalConfig = globalConfig;
            return this;
        }

        /**
         * 加载jdbc
         *
         * @param dataSourceConfig
         * @return
         */
        Builder initJdbc(DataSourceConfig dataSourceConfig) {
            this.jdbcTemplate = DataSource.build(
                    dataSourceConfig.getUrl(),
                    dataSourceConfig.getUserName(),
                    dataSourceConfig.getPassword(),
                    dataSourceConfig.getDriverName())
                    .JdbcTemplate();
            this.dataSourceConfig = dataSourceConfig;
            return this;
        }

        /**
         * 加载需要生成代码的表
         *
         * @return
         */
        Builder initTables(StrategyConfig strategyConfig) {
            QuerySQL querySQL = DbType.dbTypes.get(this.dataSourceConfig.getDbType());
            String sql = querySQL.getTableCommentsSql();
            String specified = this.dataSourceConfig.getSpecified();
            if (StringUtils.isNotBlank(specified)) {
                sql = String.format(querySQL.getTablesSqlSpecified(), specified);
            }
            System.out.println(sql);
            List<Map<String, Object>> maps = jdbcTemplate.excuteQuery(sql, null);
            System.out.println("size=" + maps.size());
            this.tableInfoList = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                System.out.println(map);
                TableInfo tableInfo = new TableInfo();
                String string = map.get(querySQL.getTableName()).toString();
                tableInfo.setName(string, strategyConfig.getIgnorePrefix());
                tableInfo.setComment(map.get(querySQL.getTableComment()) == null ? null : map.get(querySQL.getTableComment()).toString());
                this.tableInfoList.add(tableInfo);
            }

            for (TableInfo tableInfos : this.tableInfoList) {
                List<Map<String, Object>> mapsField = jdbcTemplate.excuteQuery(String.format(querySQL.getTableFieldsSql(), tableInfos.getName()), null);
                List<TableField> list = new ArrayList<>();
                List<TableField> selectfields = new ArrayList<>();
                for (Map<String, Object> stringObjectMap : mapsField) {
                    TableField tableField = new TableField();
                    tableField.setName(stringObjectMap.get(querySQL.getFieldName()) == null ? "" : stringObjectMap.get(querySQL.getFieldName()).toString());
                    String comment = stringObjectMap.get(querySQL.getFieldComment()) == null ? "" : stringObjectMap.get(querySQL.getFieldComment()).toString();
                    if (comment.contains("_int") || comment.contains("_str")) {
                        if (comment.contains("_int")) {
                            tableField.setOptionType(OptionType.INT);
                        }
                        if (comment.contains("_str")) {
                            tableField.setOptionType(OptionType.STR);
                        }
                        tableField.setComment(comment);
                        String[] split = comment.replace("_int", "").replace("_str", "").split("@");
                        if (split.length == 0) {
                            tableField.setComment(comment);
                        } else {
                            tableField.setComment(split[0]);
                            List<FieldOption> fieldOptions = new ArrayList<>();
                            String[] split1 = split[1].split("&");
                            for (String op : split1) {
                                String[] split2 = op.split(":");
                                FieldOption fieldOption = new FieldOption();
                                fieldOption.setOptionKey(split2[0]);
                                fieldOption.setOptionName(split2[1]);
                                fieldOptions.add(fieldOption);
                            }
                            tableField.setOptions(fieldOptions);
                        }
                        selectfields.add(tableField);
                    } else {
                        tableField.setComment(comment);
                    }
                    String fieldType = stringObjectMap.get(querySQL.getFieldType()) == null ? null : stringObjectMap.get(querySQL.getFieldType()).toString();
                    tableField.setDbColumnType(this.dataSourceConfig.getFieldTypeConvert().fieldTypeConvert(fieldType));
                    this.dataSourceConfig.getFieldTypeConvert().fieldTypeLengthAndFraction(tableField, fieldType);
                    String req = stringObjectMap.get(querySQL.getFieldRequired()).toString();
                    switch (req) {
                        case "YES":
                            tableField.setRequired(false);
                            break;
                        case "NO":
                            tableField.setRequired(true);
                            break;
                        default:
                            tableField.setRequired(false);
                            break;
                    }
                    list.add(tableField);
                }
                if (list.size() <= 3) {
                    tableInfos.setCommonFields(list);
                    tableInfos.setAdvanced(false);
                } else {
                    tableInfos.setAdvanced(true);
                    tableInfos.setCommonFields(list.subList(0, 4));
                    tableInfos.setAdvancedFields(list.subList(4, list.size()));
                }
                tableInfos.setSelectfields(selectfields);
                tableInfos.setFields(list);
            }

            System.out.println(this.tableInfoList);
            return this;
        }

        /**
         * 加载基础路径
         *
         * @param packageConfig
         * @return
         */
        Builder initPackageInfo(PackageConfig packageConfig) {
            this.packageConfig = packageConfig;
            return this;
        }

        /**
         * 加载模板 生成代码
         *
         * @param templateLoaderPath
         * @return
         */
        Builder initTemplate(String templateLoaderPath) {
            this.templateLoaderPath = templateLoaderPath;
            return this;
        }

        public void execute() {
            TemplateConfig.
                    custom()
                    .globalConfig(this.globalConfig)
                    .tableInfoList(this.tableInfoList)
                    .packageInfo(this.packageConfig)
                    .templateLoading(this.templateLoaderPath)
                    .execute();
        }
    }
}
