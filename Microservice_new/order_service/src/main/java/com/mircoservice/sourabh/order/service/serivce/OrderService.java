package com.mircoservice.sourabh.order.service.serivce;

import com.mircoservice.sourabh.order.service.dto.InventoryResponse;
import com.mircoservice.sourabh.order.service.dto.OrderLineItemDto;
import com.mircoservice.sourabh.order.service.dto.OrderRequest;
import com.mircoservice.sourabh.order.service.event.OrderPlacedEvent;
import com.mircoservice.sourabh.order.service.model.Order;
import com.mircoservice.sourabh.order.service.model.OrderLineItems;
import com.mircoservice.sourabh.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webCilentBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());
        List<String> skuCode = order.getOrderLineItemsList().stream().map(
                OrderLineItems::getSkuCode
        ).collect(Collectors.toList());


// Call Inventory Service, and order if product is in

   List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

            order.setOrderLineItemsList(orderLineItems);
        InventoryResponse[] InventoryResponseArray =   webCilentBuilder.build().get()
                     .uri("http://inventory-service/api/inventory", uriBuilder -> uriBuilder
                             .queryParam("skuCode",skuCode).build())
                     .retrieve().bodyToMono(InventoryResponse[].class).block();


        boolean allProductsInStock= Arrays.stream(InventoryResponseArray).allMatch(InventoryResponse::isInStock);


               if(Boolean.TRUE.equals(allProductsInStock))
               {
                   orderRepository.save(order);
                   kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber()));
                   return "order placed Successfully";
               }else
               {
                   throw  new IllegalArgumentException("PRODUCT IS OUT OF STOCK! Please Come back later");
               }

    }

    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItemDto orderLineItemDto1 = new OrderLineItemDto();
        orderLineItemDto1.setPrice(orderLineItemDto.getPrice());
        orderLineItemDto1.setQuantity(orderLineItemDto.getQuantity());
        orderLineItemDto1.setSkuCode(orderLineItemDto.getSkuCode());

        return null;

    }
}
