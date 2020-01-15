package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 10:36
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryListByParentId(Long pid) {
        // 非空字段作为查询条件进行查询
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }
}
