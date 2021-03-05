package com.yaoshi.codegen.autocode;

/**
 * @author liusy
 */
public class StrategyConfig {

    private String ignorePrefix;

    public StrategyConfig() {
    }

    public StrategyConfig(String ignorePrefix) {
        this.ignorePrefix = ignorePrefix;
    }

    public String getIgnorePrefix() {
        return ignorePrefix;
    }

    public void setIgnorePrefix(String ignorePrefix) {
        this.ignorePrefix = ignorePrefix;
    }
}
