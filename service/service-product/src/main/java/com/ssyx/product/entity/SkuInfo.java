package com.ssyx.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * sku信息
 * </p>
 *
 * @author ${author}
 * @since 2025-02-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 平台属性分组id
     */
    private Long attrGroupId;

    /**
     * 商品类型：0->普通商品 1->秒杀商品
     */
    private Boolean skuType;

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 展示图片
     */
    private String imgUrl;

    /**
     * 限购个数/每天（0：不限购）
     */
    private Integer perLimit;

    /**
     * 上架状态：0->下架；1->上架
     */
    private Boolean publishStatus;

    /**
     * 审核状态：0->未审核；1->审核通过
     */
    private Boolean checkStatus;

    /**
     * 是否新人专享：0->否；1->是
     */
    private Boolean isNewPerson;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * sku编码
     */
    private String skuCode;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 锁定库存
     */
    private Integer lockStock;

    /**
     * 预警库存
     */
    private Integer lowStock;

    /**
     * 销量
     */
    private Integer sale;

    /**
     * 仓库
     */
    private Long wareId;

    private Long version;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;


}
