package com.yaoshi.codegen.autocode.jdbc;


public class DataSource {
    private String jdbcUrl;
    private String userName;
    private String password;
    private String driverClassName;
    private JDBCUtil jdbcUtil;

    public static DataSource build(String jdbcUrl, String userName, String password, String driverClassName) {
        return new DataSource(jdbcUrl, userName, password, driverClassName);
    }

    public DataSource() {
    }

    public DataSource(String jdbcUrl, String userName, String password, String driverClassName) {
        this.jdbcUrl = jdbcUrl;
        this.userName = userName;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    public JDBCUtil JdbcTemplate() {
        return jdbcTemplate();
    }

    /**
     * sqlServer 数据源
     *
     * @return
     */
    private synchronized JDBCUtil dataSource() {
        if (jdbcUtil == null) {
            jdbcUtil = new JDBCUtil(driverClassName, jdbcUrl, userName, password);
        }
        return jdbcUtil;
    }

    /**
     * initJdbc
     *
     * @return
     */
    private synchronized JDBCUtil jdbcTemplate() {
        return dataSource();
    }
}
