package com.mircoservice.sourabh.order.service.serivce;

import com.mircoservice.sourabh.order.service.dto.OrderLineItemDto;
import com.mircoservice.sourabh.order.service.dto.OrderRequest;
import com.mircoservice.sourabh.order.service.model.Order;
import com.mircoservice.sourabh.order.service.model.OrderLineItems;
import com.mircoservice.sourabh.order.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        Order order = new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

   List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDto()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

               order.setOrderLineItemsList(orderLineItems);

               orderRepository.save(order);

    }

    private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
        OrderLineItemDto orderLineItemDto1 = new OrderLineItemDto();
        orderLineItemDto1.setPrice(orderLineItemDto.getPrice());
        orderLineItemDto1.setQuantity(orderLineItemDto.getQuantity());
        orderLineItemDto1.setSkuCode(orderLineItemDto.getSkuCode());

        return null;

    }
}
