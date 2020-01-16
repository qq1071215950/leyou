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
    SPC_NOT_FOUND(408,"规格参数不存在")
    ;
    private int code;
    private String msg;

}
