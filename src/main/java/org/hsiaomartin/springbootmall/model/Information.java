package org.hsiaomartin.springbootmall.model;

import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.constant.InformationType;

import java.util.Date;

@Getter
@Setter
public class Information {

    private Integer infoId;
    private InformationType infoType;
    private String title;
    private String description;
    private String content;
    private String ImageUrl;
    private Date createdDate;
    private Date lastModefiedDate;
}
