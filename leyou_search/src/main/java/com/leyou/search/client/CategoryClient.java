package com.leyou.search.client;

import com.leyou.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/19 9:32
 */
@FeignClient(value = "item-service")
public interface CategoryClient extends CategoryApi {
}
