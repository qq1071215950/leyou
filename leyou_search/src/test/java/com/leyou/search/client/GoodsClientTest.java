package com.leyou.search.client;

import com.leyou.item.pojo.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 17:37
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsClientTest {

    @Autowired
    private GoodsClient goodsClient;
    @Test
    public void queryCategoryByids() {
        List<Category> list = goodsClient.queryCategoryByids(Arrays.asList(1L,2L,3L));
        Assert.assertEquals(3,list.size());
        for (Category category: list){
            System.out.println(category);
        }
    }
}
