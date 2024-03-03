package org.hsiaomartin.springbootmall.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {

    private Integer userId;

    // 設定返回的 Json 時此屬性名稱
    @JsonProperty("e_mail")
    private String email;

    // 返回 Json 時會忽略此屬性
    @JsonIgnore
    private String password;

    // 設定返回時，讓返回的時間格式與變數格式對應
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;
}
