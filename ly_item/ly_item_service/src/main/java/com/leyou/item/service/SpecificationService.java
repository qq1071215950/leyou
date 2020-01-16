package com.leyou.item.service;

import com.leyou.item.mapper.SpecificationMapper;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 17:27
 */
@Service
public class SpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;
    public Specification queryById(Long id) {
        return this.specificationMapper.selectByPrimaryKey(id);
    }
}
