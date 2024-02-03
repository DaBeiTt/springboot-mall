package org.hsiaomartin.springbootmall.dto;

import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.constant.ProductCategory;

@Getter
@Setter
public class ProductQueryParams {

    private ProductCategory category;
    private String search;
    private String orderBy;
    private String sort;
    private Integer limit;
    private Integer offset;
}
