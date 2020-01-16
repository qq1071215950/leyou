package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptionx.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.pojo.Spu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiange
 * @version 1.0
 * @date 2020/1/16 9:57
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;

    public PageResult<SpuBo> querySpuByPageAndSort(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(key)){
            criteria.andLike("title","%"+key+"%");
        }
        if (saleable != null){
            criteria.andEqualTo("saleable",saleable);
        }
        // 默认排序
        example.setOrderByClause("last_update_time DESC");
        List<Spu> spus = spuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnum. GOODS_NOT_EXIT);
        }
        // 解析分类和品牌名称
        List<SpuBo> list = loadCategoryAndBrandName(spus);
        PageInfo<SpuBo> info = new PageInfo<>(list);
        return new PageResult<SpuBo>(info.getTotal(),list);
    }

    private List<SpuBo> loadCategoryAndBrandName(List<Spu> spus) {
        List<SpuBo> list = new ArrayList<>();
        for (Spu spu: spus){
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu,spuBo);
            // 分类名称
            List<String> names = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
                    .stream().map(Category::getName).collect(Collectors.toList());
            // 品牌名称
            spuBo.setCname(org.apache.commons.lang3.StringUtils.join(names,"/"));
            Brand brand = brandService.queryById(spu.getBrandId());
            spuBo.setBname(brand.getName());
            list.add(spuBo);
        }
        return list;
    }
}

