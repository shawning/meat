package com.yaoshi.codegen.autocode;

/**
 * 代码生成
 * @author liusy
 */
public class AutoCodeGenerate {

    public static void main(String[] args) {
        com.yaoshi.codegen.autocode.CodeGenerateConfig
                .custom()
                .setGlobal(com.yaoshi.codegen.autocode.GlobalConfig
                        .custom()
                        //代码输出目录
                        .setOutputDir("/Users/xiaoning/home")
                        .setMainGroup("com.meet")
                        //作者
                        .setAuthor("xiaoning")
                        .build())
                .setDataSource(com.yaoshi.codegen.autocode.DataSourceConfig
                        .custom()
                        //数据库驱动
                        .setDriverName("com.mysql.jdbc.Driver")
                        .setUrl("jdbc:mysql://rm-uf68d07q0f4x39900ho.mysql.rds.aliyuncs.com:3306/youlai?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true&allowMultiQueries=true")
                        .setUserName("chat")
                        .setPassword("1qaz!QAZ")
                        //表过滤 如果填写此项说明只生成 此表相关代码
                        .setSpecified("biz_user")
                        .build()
                )
                .setPackageInfo(PackageConfig
                        .custom()
                        //包的根路径  生成代码都在这个包下
                        .setBasePackage("com.meet.app")
                        .build())
                //忽视表开头
                .setStrategy(new com.yaoshi.codegen.autocode.StrategyConfig("t_"))
                //模板路径
                .setTemplate("META-INF/tpl")
                .execute();
    }
}
