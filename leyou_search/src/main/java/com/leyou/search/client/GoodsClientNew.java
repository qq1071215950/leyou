package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/19 9:31
 */
@FeignClient(value = "item-service")
public interface GoodsClientNew extends GoodsApi {

}
