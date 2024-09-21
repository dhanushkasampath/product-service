package com.learn.product_service.command.api.controller;

import com.learn.product_service.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return "Product Added";
    }
}
