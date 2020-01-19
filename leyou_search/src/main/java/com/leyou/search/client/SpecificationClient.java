package com.leyou.search.client;

import com.leyou.item.api.SpecificationApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/19 9:45
 */
@FeignClient(value = "item-service")
public interface SpecificationClient extends SpecificationApi {

}
