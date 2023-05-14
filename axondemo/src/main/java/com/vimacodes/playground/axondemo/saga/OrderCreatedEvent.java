package com.vimacodes.playground.axondemo.saga;

import lombok.Value;

@Value
public class OrderCreatedEvent {
    String orderId;
}
