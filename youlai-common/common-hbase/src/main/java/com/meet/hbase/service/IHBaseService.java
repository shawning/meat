package com.meet.hbase.service;

import com.meet.hbase.dto.RoomMessageDto;
import com.meet.hbase.dto.SingleMessageDto;
import org.apache.hadoop.hbase.client.Result;

import java.util.List;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/2 下午3:26
 * @Description
 */
public interface IHBaseService {
    /**
     * key 前缀匹配
     * @param tableName
     * @param startRowKey
     * @param stopRowKey
     * @param prefix
     * @return
     */
    List<Result> getListWithPrefix(String tableName, String startRowKey, String stopRowKey, String prefix);

    /**
     * key精确查找
     * @param tableName
     * @param rowKeys
     * @param familyColumn
     * @param column
     * @return
     */
    List<Result> getListRowKey(String tableName, List<String> rowKeys, String familyColumn, String column);
    <T> List<Result> searchAll(String tableName, Class<T> c);
    <T> List<Result> findFamily(String tableName, String family);

    /**
     *
     * @param sendTo 列-接受者
     * @param sendFrom 列-发送者
     * @param date 列-发送时间
     * @param <T>
     * @return
     */
    <T> List<T> getSingleMessages(Class<T> c, String sendTo, String sendFrom, String date);

    /**
     *
     * @param rooId 列-房间号
     * @param sendFrom 列-发送者
     * @param date 列-时间
     * @param <T>
     * @return
     */
    <T> List<T> getChatRoomMessages(Class<T> c, String rooId, String sendFrom, String date);

    /**
     *
     * @param singleMessageDto
     * @return
     */
    SingleMessageDto saveSingleChatMessage(SingleMessageDto singleMessageDto);
    RoomMessageDto saveChatMessage(RoomMessageDto roomMessageDto);
}
