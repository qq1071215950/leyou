package com.leyou.item.mapper;

import com.leyou.item.pojo.Stock;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 14:04
 */
public interface StockMapper extends Mapper<Stock>,IdListMapper{
}
