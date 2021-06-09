package com.meet.hbase.service.impl;

import com.meet.hbase.dto.MessageConstant;
import com.meet.hbase.dto.RoomMessageDto;
import com.meet.hbase.dto.SingleMessageDto;
import com.meet.hbase.dto.MessageConstant;
import com.meet.hbase.service.IHBaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * @Author: 肖宁 xiaoning@visionet.com.cn
 * @Date: 2021/6/2 下午3:30
 * @Description
 */
@Service
public class HBaeServiceImpl implements IHBaseService {
    @Autowired
    private HbaseTemplate hbaseTemplate;
    @Override
    public List<Result> getListWithPrefix(String tableName, String startRowKey, String stopRowKey, String prefix) {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        filterList.addFilter(new PrefixFilter(Bytes.toBytes(prefix)));
        Scan scan = new Scan();
        if (filterList.getFilters().size() > 0) {
            scan.setFilter(filterList);
        }
//        scan.setStartRow(Bytes.toBytes(startRowKey));
//        scan.setStopRow(Bytes.toBytes(stopRowKey));
        return hbaseTemplate.find(tableName, scan, (rowMapper, rowNum) -> rowMapper);
    }

    @Override
    public List<Result> getListRowKey(String tableName, List<String> rowKeys, String familyColumn, String column) {
        return null;
    }

    @Override
    public <T> List<Result> searchAll(String tableName, Class<T> c) {
        return hbaseTemplate.find(tableName,new Scan(),(rowMapper, rowNum) -> rowMapper);
    }

    @Override
    public <T> List<Result> findFamily(String tableName, String family) {
//        FilterList filterList=new FilterList();
//        SubstringComparator comp = new SubstringComparator("hong");
//        Filter likeFilter = new SingleColumnValueFilter(Bytes.toBytes("family"),
//                Bytes.toBytes("gender"), CompareFilter.CompareOp.EQUAL, comp);
//        filterList.addFilter(likeFilter);
        Filter filters = new SingleColumnValueFilter(Bytes.toBytes(family),
                Bytes.toBytes("sendFrom"), CompareFilter.CompareOp.EQUAL, Bytes.toBytes("xiaoning"));
//        FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
//        filters.addFilter(new SingleColumnValueFilter(Bytes.toBytes(family),Bytes.toBytes("gender"),
//                CompareFilter.CompareOp.EQUAL,new SubstringComparator("fe")));

        Scan scan = new Scan();
        scan.setCaching(5000);
        scan.addFamily(Bytes.toBytes(family));
        scan.setFilter(filters);
        return hbaseTemplate.find(tableName, scan,(rowMapper, rowNum) -> rowMapper);
    }

    @Override
    public <T> List<T> getSingleMessages(Class<T> c, String sendTo, String sendFrom, String date) {
        FilterList filterList = new FilterList();
        if(StringUtils.isNotEmpty(sendTo)) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.SingleFamily.getValue()), Bytes.toBytes("sendTo"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(sendTo)));
        }
        if(StringUtils.isNotEmpty(sendFrom)){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.SingleFamily.getValue()), Bytes.toBytes("sendFrom"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(sendFrom)));//精确匹配
        }
        if(date != null){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.SingleFamily.getValue()), Bytes.toBytes("date"),
                    CompareFilter.CompareOp.EQUAL, new SubstringComparator(date)));//模糊匹配
        }
        Scan scan = new Scan();
        scan.setCaching(5000);
        scan.addFamily(Bytes.toBytes(MessageConstant.StoreType.SingleFamily.getValue()));
        scan.setFilter(filterList);
        return hbaseTemplate.find(MessageConstant.StoreType.SingleTable.getValue(), scan, new RowMapper<T>() {
            @Override
            public T mapRow(Result result, int rowNum) throws Exception {
                T pojo = c.newInstance();
                BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(pojo);
                List<Cell> ceList = result.listCells();
                for (Cell cellItem : ceList) {
                    String cellName = new String(CellUtil.cloneQualifier(cellItem));
                    if (!"class".equals(cellName)) {
                        beanWrapper.setPropertyValue(cellName, new String(CellUtil.cloneValue(cellItem)));
                    }
                }
                return pojo;
            }
        });

//        return hbaseTemplate.find(tableName, scan,(rowMapper, rowNum) -> rowMapper);
    }

    @Override
    public <T> List<T> getChatRoomMessages(Class<T> c, String rooId, String sendFrom, String date) {
        FilterList filterList = new FilterList();
        if(StringUtils.isNotEmpty(rooId)) {
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.RoomFamily.getValue()), Bytes.toBytes("rooId"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(rooId)));
        }
        if(StringUtils.isNotEmpty(sendFrom)){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.RoomFamily.getValue()), Bytes.toBytes("sendFrom"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(sendFrom)));
        }
        if(date != null){
            filterList.addFilter(new SingleColumnValueFilter(Bytes.toBytes(MessageConstant.StoreType.RoomFamily.getValue()), Bytes.toBytes("date"),
                    CompareFilter.CompareOp.EQUAL, new SubstringComparator(date)));
        }
        Scan scan = new Scan();
        scan.setCaching(5000);
        scan.addFamily(Bytes.toBytes(MessageConstant.StoreType.RoomFamily.getValue()));
        scan.setFilter(filterList);
        return hbaseTemplate.find(MessageConstant.StoreType.RoomTable.getValue(), scan, new RowMapper<T>() {
            @Override
            public T mapRow(Result result, int rowNum) throws Exception {
                T pojo = c.newInstance();
                BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(pojo);
                List<Cell> ceList = result.listCells();
                for (Cell cellItem : ceList) {
                    String cellName = new String(CellUtil.cloneQualifier(cellItem));
                    if (!"class".equals(cellName)) {
                        beanWrapper.setPropertyValue(cellName, new String(CellUtil.cloneValue(cellItem)));
                    }
                }
                return pojo;
            }
        });
//        return hbaseTemplate.find(tableName, scan,(rowMapper, rowNum) -> rowMapper);
    }

    @Override
    public SingleMessageDto saveSingleChatMessage(SingleMessageDto singleMessageDto) {
        if (singleMessageDto == null) {
            return null;
        }
        if(!createOrExistTable(MessageConstant.StoreType.SingleTable.getValue(), MessageConstant.StoreType.SingleFamily.getValue())){
            return null;
        }
        return hbaseTemplate.execute(MessageConstant.StoreType.SingleTable.getValue(), new TableCallback<SingleMessageDto>() {
            @Override
            public SingleMessageDto doInTable(HTableInterface table) throws Throwable {
                PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(singleMessageDto.getClass());
                BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(singleMessageDto);
                Put put = new Put(Bytes.toBytes(singleMessageDto.getMessageId()));
                for (PropertyDescriptor propertyDescriptor : pds) {
                    String properName = propertyDescriptor.getName();
                    String value = beanWrapper.getPropertyValue(properName).toString();
                    if (!StringUtils.isBlank(value)) {
                        put.add(Bytes.toBytes(MessageConstant.StoreType.SingleFamily.getValue()), Bytes.toBytes(properName), Bytes.toBytes(value));
                    }
                }
                table.put(put);
                return singleMessageDto;
            }
        });
    }

    @Override
    public RoomMessageDto saveChatMessage(RoomMessageDto roomMessageDto) {
        if (roomMessageDto == null) {
            return null;
        }
        if(!createOrExistTable(MessageConstant.StoreType.RoomTable.getValue(), MessageConstant.StoreType.RoomFamily.getValue())){
            return null;
        }
        return hbaseTemplate.execute(MessageConstant.StoreType.RoomTable.getValue(), new TableCallback<RoomMessageDto>() {
            @Override
            public RoomMessageDto doInTable(HTableInterface table) throws Throwable {
                PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(roomMessageDto.getClass());
                BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(roomMessageDto);
                Put put = new Put(Bytes.toBytes(roomMessageDto.getMessageId()));
                for (PropertyDescriptor propertyDescriptor : pds) {
                    String properName = propertyDescriptor.getName();
                    String value = beanWrapper.getPropertyValue(properName).toString();
                    if (!StringUtils.isBlank(value)) {
                        put.add(Bytes.toBytes(MessageConstant.StoreType.RoomFamily.getValue()), Bytes.toBytes(properName), Bytes.toBytes(value));
                    }
                }
                table.put(put);
                return roomMessageDto;
            }
        });
    }
    public boolean createOrExistTable(String tableName,String family) {
        HBaseAdmin admin;
        try {
            // 从hbaseTemplate 获取configuration对象,用来初始化admin
            admin = new HBaseAdmin(hbaseTemplate.getConfiguration());
            if(admin.tableExists(tableName)){
                return true;
            }
            String[] cols = new String[] {family};
            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                tableDescriptor.addFamily(new HColumnDescriptor(col));
            }
            admin.createTable(tableDescriptor);
            return admin.tableExists(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
