package com.yaoshi.codegen.maven.plugin;


import com.yaoshi.codegen.autocode.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author liusy
 */

@Mojo(name = "create")
public class Codegen extends AbstractMojo {
    @Parameter(required = true)
    private String outputDir;
    @Parameter(required = true)
    private String author;
    @Parameter(required = true)
    private String mainGroup;
    @Parameter(required = true)
    private String dbUrl;
    @Parameter(required = true)
    private String userName;
    @Parameter(required = true)
    private String password;
    @Parameter
    private String specified;
    @Parameter(required = true)
    private String basePackage;
    @Parameter
    private String tableFilter;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        CodeGenerateConfig
                .custom()
                .setGlobal(GlobalConfig
                        .custom()
                        //代码输出目录
                        .setOutputDir(outputDir)
                        //构建的maven-group
                        .setMainGroup(mainGroup)
                        //作者
                        .setAuthor(author)
                        .build())
                .setDataSource(DataSourceConfig
                        .custom()
                        //数据库驱动
                        .setDriverName("com.mysql.jdbc.Driver")
                        .setUrl(dbUrl)
                        .setUserName(userName)
                        .setPassword(password)
                        //表过滤 如果填写此项说明只生成 此表相关代码
                        .setSpecified(specified)
                        .build()
                )
                .setPackageInfo(PackageConfig
                        .custom()
                        //包的根路径  生成代码都在这个包下
                        .setBasePackage(basePackage)
                        .build())
                //忽视表开头
                .setStrategy(new StrategyConfig(tableFilter))
                //模板路径
                .setTemplate("/META-INF/tpl")
                .execute();
    }
}
