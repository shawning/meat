package com.meet.hbase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/1 下午1:23
 * @Description
 */
@Configuration
public class HbaseConfig {
    @Value("${hbase.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${hbase.zookeeper.property.clientPort}")
    private String clientPort;

    @Value("${zookeeper.znode.parent}")
    private String znodeParent;


    @Bean
    public org.apache.hadoop.conf.Configuration hBaseConfiguration(){
        org.apache.hadoop.conf.Configuration conf = org.apache.hadoop.hbase.HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", zookeeperQuorum);
        conf.set("hbase.zookeeper.property.clientPort", clientPort);
        conf.set("zookeeper.znode.parent", znodeParent);
        conf.set("zookeeper.sasl.client", "false");
        return conf;
    }


    @Bean
    public HbaseTemplate hbaseTemplate(org.apache.hadoop.conf.Configuration conf) {
        return new HbaseTemplate(conf);
    }
}
