package com.vimacodes.playground.axondemo.model.order.evt;

import lombok.Value;

@Value
public class OrderValidatedEvent {
    String orderId;
}
