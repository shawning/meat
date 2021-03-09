package com.youlai.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xiaoning
 * @desc VO 基类
 * @email
 * @date 2021/1/11
 */
@Data
@ToString
public class BaseVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "每页条数，默认10", example = "10")
    private Integer pageSize = 10;
    @ApiModelProperty(value = "第几页，默认1", example = "1")
    private Integer currentPage = 1;
    @ApiModelProperty(value = "排序参数", example = "id_descend")
    private String sorter;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }
}
