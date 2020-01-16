package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptionx.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 13:59
 */
@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 分页查询品牌
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @param key
     * @return
     */
    @Transactional
    public PageResult<Brand> queryBrandByPage(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        PageHelper.startPage(page, rows);
        Example example = new Example(Brand.class);
        if (!StringUtils.isEmpty(key)){
            example.createCriteria().orLike("name","%"+key+"%")
            .orEqualTo("letter",key.toUpperCase());
        }
        if (!StringUtils.isEmpty(sortBy)){
            String orderBy = sortBy + ( desc? " DESC" : "ASC");
            example.setOrderByClause(orderBy);
        }
        List<Brand> list = brandMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnum.BRAND_DATE_NOT_EXIT);
        }
        PageInfo<Brand> info = new PageInfo<>(list);
        return new PageResult<>(info.getTotal(), list);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if (count != 1 ){
            throw new LyException(ExceptionEnum.ADD_BRAND_FAIL);
        }
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
            if (count != 1 ){
                throw new LyException(ExceptionEnum.ADD_BRAND_FAIL);
            }
        }
    }

    public Brand queryById(Long id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        if (brand == null) {
            throw new LyException(ExceptionEnum. BRAND_NOT_EXIT);
        }
        return brand;
    }
}
