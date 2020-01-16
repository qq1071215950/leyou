package com.leyou.item.bo;

import com.leyou.item.pojo.Spu;
import lombok.Data;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 10:04
 */
@Data
public class SpuBo extends Spu {
    private String cname;// 商品分类名称
    private String bname;// 品牌名称

}
