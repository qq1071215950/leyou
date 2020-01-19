package com.leyou.search.repository;

import com.leyou.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/19 9:35
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {

}
