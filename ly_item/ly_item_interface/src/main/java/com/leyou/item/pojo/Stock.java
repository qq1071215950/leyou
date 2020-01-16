package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 13:58
 */

@Table(name = "tb_stock")
@Data
public class Stock {
    @Id
    private Long skuId;
    private Integer seckillStock;// 秒杀可用库存
    private Integer seckillTotal;// 已秒杀数量
    private Long stock;// 正常库存
}
