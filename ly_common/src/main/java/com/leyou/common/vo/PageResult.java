package com.leyou.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 14:03
 */
@Data
public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> items;
    public PageResult(){

    }
    public PageResult(Long total,List<T> items){
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items){
        this.total = total;
        this.items = items;
        this.totalPage = totalPage;
    }
}
