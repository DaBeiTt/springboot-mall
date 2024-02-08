package org.hsiaomartin.springbootmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderQueryParams {

    private Integer userId;
    private Integer limit;
    private Integer offset;

    public OrderQueryParams(Integer userId, Integer limit, Integer offset) {
        this.userId = userId;
        this.limit = limit;
        this.offset = offset;
    }
}
