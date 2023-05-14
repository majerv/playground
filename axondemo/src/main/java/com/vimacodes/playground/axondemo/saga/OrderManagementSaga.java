package com.vimacodes.playground.axondemo.saga;

import com.vimacodes.playground.axondemo.model.order.evt.OrderValidatedEvent;
import com.vimacodes.playground.axondemo.model.order.cmd.ValidateOrderCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

@Saga(sagaStore = "mySagaStore")
@Slf4j
public class OrderManagementSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderCreatedEvent event) throws Exception {
        log.info("Saga started");

        final var orderId = event.getOrderId();
        log.info("Received order created event:" + orderId);

        commandGateway.send(new ValidateOrderCommand(orderId));
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderValidatedEvent event) {
        final var orderId = event.getOrderId();

        log.info("Received order validated event:" + orderId);
        log.info("Saga ended");

        SagaLifecycle.end();
    }

}