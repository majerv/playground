package com.vimacodes.playground.axondemo.model.order.cmd;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class ValidateOrderCommand {
    @TargetAggregateIdentifier
    String orderId;
}
