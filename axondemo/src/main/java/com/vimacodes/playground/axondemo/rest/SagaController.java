package com.vimacodes.playground.axondemo.rest;

import com.vimacodes.playground.axondemo.saga.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sagas")
@Slf4j
public class SagaController {

    @Autowired
    CommandGateway commandGateway;

    @Autowired
    EventGateway eventGateway;

    // should be "POST", just for in-browser testing ;)
    @GetMapping("/{id}")
    public String createNewSaga(@PathVariable String id) throws Exception {
//        commandGateway.send(new CreateOrderCommand(id));
          eventGateway.publish(new OrderCreatedEvent(id));
        return id + " created";
    }

}
