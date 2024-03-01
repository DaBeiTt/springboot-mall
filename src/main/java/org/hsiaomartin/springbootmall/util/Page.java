package org.hsiaomartin.springbootmall.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Page <T> {

    private Integer limit;
    private Integer offset;
    private Integer total;
    private List<T> results;
}
