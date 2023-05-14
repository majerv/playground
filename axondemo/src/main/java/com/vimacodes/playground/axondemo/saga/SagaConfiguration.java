package com.vimacodes.playground.axondemo.saga;

import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.modelling.saga.repository.SagaStore;
import org.axonframework.modelling.saga.repository.inmemory.InMemorySagaStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaConfiguration {

    @Bean
    public Cache orderCache() {
        return new WeakReferenceCache();
    }

    @Bean
    public SagaStore mySagaStore() {
        return new InMemorySagaStore(); // default is JpaSagaStore
    }

    /*@Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerPooledStreamingEventProcessor
                ("ProcessOrderSagaProcessor",
                        org.axonframework.config.Configuration::eventStore,
                        (configuration, builder) -> builder.initialToken(
                                StreamableMessageSource::createTailToken));
    }*/

}
