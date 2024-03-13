package org.hsiaomartin.springbootmall.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessObject {

    private String event;
    private String message;

    public SuccessObject(String event, String message) {

        this.event = event;
        this.message = message;
    }
}
