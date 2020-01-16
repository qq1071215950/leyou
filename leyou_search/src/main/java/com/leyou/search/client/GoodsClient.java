package com.leyou.search.client;

import com.leyou.item.pojo.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 17:31
 */
@FeignClient(value = "item-service")
public interface GoodsClient {
    @GetMapping("category/list/ids")
    public List<Category> queryCategoryByids(@RequestParam List<Long> ids);
}
