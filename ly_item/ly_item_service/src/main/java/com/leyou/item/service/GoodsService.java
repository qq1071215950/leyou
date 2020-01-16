package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exceptionx.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.SkuMapper;
import com.leyou.item.mapper.SpuDetailMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.mapper.StockMapper;
import com.leyou.item.pojo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
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
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private SkuMapper skuMapper;

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
    @Transactional
    public void save(SpuBo spuBo) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuBo,spu);
        // 保存spu
        spu.setSaleable(true);
        spu.setValid(true);
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(spu.getCreateTime());
        this.spuMapper.insert(spu);
        // 保存spu详情
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetailMapper.insert(spuDetail);
        // 保存sku和库存信息
        saveSkuAndStock(spuBo.getSkus(), spuBo.getId());
    }
    private void saveSkuAndStock(List<Sku> skus, Long spuId) {
        for (Sku sku : skus) {
            if (!sku.getEnable()) {
                continue;
            }
            // 保存sku
            sku.setSpuId(spuId);
            // 默认不参与任何促销
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(sku.getCreateTime());
            this.skuMapper.insert(sku);

            // 保存库存信息
            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            this.stockMapper.insert(stock);
        }
    }

    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(spuId);
        if (spuDetail == null){
            throw new LyException(ExceptionEnum.SPU_DETAIL_ERROR);
        }
        return spuDetail;
    }

    public List<Sku> querySkulistSpuId(Long spuId) {
       Sku sku = new Sku();
       sku.setSpuId(spuId);
       List<Sku> list = skuMapper.select(sku);
       if (CollectionUtils.isEmpty(list)){
           throw new LyException(ExceptionEnum.   SKU_NOT_EXIT);
       }
       // 查询库存
    /*    for (Sku sku1 : list){
            Stock stock = stockMapper.selectByPrimaryKey(sku1.getId());
            if (stock == null){
                throw new LyException(ExceptionEnum.SKU_STORE_NOT_FULL);
            }
            sku1.setStock(stock.getStock());
        }*/
        List<Long> ids = list.stream().map(Sku::getId).collect(Collectors.toList());
        List stockList = stockMapper.selectByIdList(ids);
        if (CollectionUtils.isEmpty(stockList)){
            throw new LyException(ExceptionEnum.SKU_STORE_NOT_FULL);
        }
        Map<Long, Long> stockMap = (Map<Long, Long>) stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
        list.forEach(s ->s.setStock(stockMap.get(s.getId())));
        return list;
    }
}

