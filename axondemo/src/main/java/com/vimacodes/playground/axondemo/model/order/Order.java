package com.vimacodes.playground.axondemo.model.order;

import com.vimacodes.playground.axondemo.model.order.cmd.ValidateOrderCommand;
import com.vimacodes.playground.axondemo.model.order.evt.OrderCreatedEvent;
import com.vimacodes.playground.axondemo.model.order.evt.OrderValidatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate(cache = "orderCache")
@Slf4j
public class Order {

    @AggregateIdentifier
    private String orderId;

    /*
    @CommandHandler
    public Order(CreateOrderCommand cmd) {
        final var orderId = cmd.getOrderId();
        log.info("Creating order with id: " + orderId);

        final var orderCreatedEvent = new OrderCreatedEvent(orderId);
        AggregateLifecycle.apply(orderCreatedEvent);
    }*/

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        final var orderId = event.getOrderId();
        log.info("Saving order id: " + orderId);
        this.orderId = orderId;
    }

    @CommandHandler
    public void handle(ValidateOrderCommand cmd) {
        log.info("Validating order...");
        AggregateLifecycle.apply(new OrderValidatedEvent(cmd.getOrderId()));
    }

    protected Order() {
        log.info("Order created");
    }

}
