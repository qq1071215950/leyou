package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 13:58
 */
public interface BrandMapper extends Mapper<Brand> {
    @Insert("INSERT INTO tb_category_brand (category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(Long cid, Long id);
}
