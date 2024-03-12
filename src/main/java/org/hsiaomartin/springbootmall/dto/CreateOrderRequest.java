package org.hsiaomartin.springbootmall.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SessionScope
@Component
public class CreateOrderRequest {

    @NotEmpty
    private List<BuyItem> buyItemList = new ArrayList<>();
}
