package com.leyou.item.mapper;

import com.leyou.item.pojo.Sku;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 14:05
 */
public interface SkuMapper extends Mapper<Sku> , IdListMapper {
}
