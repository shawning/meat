package com.meet.hbase.dto;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/3 上午10:04
 * @Description
 */
public class MessageConstant {
    public enum StoreType{
        SingleTable("single"),
        RoomTable("chat"),
        SingleFamily("message"),
        RoomFamily("message");
        private String value;
        StoreType(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

}
