package org.hsiaomartin.springbootmall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hsiaomartin.springbootmall.constant.InformationType;

import java.util.Date;

@Getter
@Setter
@FieldNameConstants
@TableName("information")
public class Information {

    @TableId(type = IdType.AUTO)
    private Integer infoId;
    private InformationType type;
    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private Date createdDate;
    private Date lastModifiedDate;
}
