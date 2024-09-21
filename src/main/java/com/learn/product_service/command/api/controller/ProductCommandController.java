package com.learn.product_service.command.api.controller;

import com.learn.product_service.command.api.commands.CreateProductCommand;
import com.learn.product_service.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    // this is a part of the axon framework
    private final CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    /**
     * we need to send this particular data(CreateProductCommand) to "Command Gateway"
     * @param productRestModel
     * @return
     */
    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel){
        CreateProductCommand createProductCommand =
            CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .name(productRestModel.getName())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
        //now controller is ready. lets go and create the aggregate.
    }
}
