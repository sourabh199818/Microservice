package com.mircoservice.sourabh.order.service.controller;


import com.mircoservice.sourabh.order.service.dto.OrderRequest;
import com.mircoservice.sourabh.order.service.serivce.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        orderService.placeOrder(orderRequest);
        return "order placed Successfully";
    }


//    @GetMapping
}
