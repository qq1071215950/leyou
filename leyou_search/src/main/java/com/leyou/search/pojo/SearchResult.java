package com.leyou.search.pojo;

import com.leyou.common.vo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;

import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/19 11:09
 */
public class SearchResult extends PageResult<Goods> {

    private List<Category> categories;

    private List<Brand> brands;

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<Category> categories, List<Brand> brands) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brands = brands;
    }
}
