package com.leyou.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/14 16:11
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    PRICE_CAN_NOT_NULL(400,"价格不能为空"),
    CATEGORY_CAN_NOT_FOUND(404,"分类没有找到"),
    BRAND_DATE_NOT_EXIT(405,"当前没有数据"),
    ADD_BRAND_FAIL(406,"新增失败"),
    PICTURE_TYPE_NOT_CORRECT(407,"文件类型不匹配"),
    GOODS_NOT_EXIT(409,"商品信息不存在"),
    BRAND_NOT_EXIT(410,"品牌信息不存在"),
    SPU_DETAIL_ERROR(411,"商品详情出错"),
    SKU_NOT_EXIT(412,"商品sku不存在"),
    SKU_STORE_NOT_FULL(413,"商品库存不足"),
    SPC_NOT_FOUND(408,"规格参数不存在")
    ;
    private int code;
    private String msg;

}
