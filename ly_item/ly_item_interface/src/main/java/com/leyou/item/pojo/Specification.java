package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/15 17:24
 */

@Data
@Table(name = "tb_specification")
public class Specification {
    @Id
    private Long categoryId;
    private String specifications;

}
