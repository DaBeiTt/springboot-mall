package org.hsiaomartin.springbootmall.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hsiaomartin.springbootmall.constant.InformationType;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "information")
public class Information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infoId;

    @Enumerated(EnumType.STRING)
    private InformationType type;

    private String title;
    private String description;
    private String content;
    private String imageUrl;
    private Date createdDate;
    private Date lastModifiedDate;
}
