package com.learn.product_service.command.api.aggregate;

import com.learn.product_service.command.api.commands.CreateProductCommand;
import com.learn.product_service.command.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @TargetAggregateIdentifier
    private String productId;//this productId should be unique
    private String name;
    private BigDecimal price;
    private Integer quantity;

    //for this aggregate to work we need to create a constructor
    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
        // you can perform all the validations here
        ProductCreatedEvent productCreatedEvent =
            new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate(){

    }
    //once the aggregate is created we need to create the event and publish it into event store.

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productId = productCreatedEvent.getProductId();
        this.name = productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
    }
}
