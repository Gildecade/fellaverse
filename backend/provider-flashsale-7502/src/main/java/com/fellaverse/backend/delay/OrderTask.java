package com.fellaverse.backend.delay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTask extends TaskBase{
    private Long orderId;

    public OrderTask(String identifier, Long id) {
        this.identifier = identifier;
        this.orderId = id;
    }
}
