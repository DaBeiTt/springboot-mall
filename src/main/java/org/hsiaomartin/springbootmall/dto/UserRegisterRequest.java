package org.hsiaomartin.springbootmall.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
