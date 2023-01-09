package com.mservice.orderservice.service.impl;


import com.mservice.orderservice.dto.OrderLineItemDto;
import com.mservice.orderservice.dto.OrderRequest;
import com.mservice.orderservice.entity.Order;
import com.mservice.orderservice.entity.OrderLineItems;
import com.mservice.orderservice.repository.OrderRepository;
import com.mservice.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems =  orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(this::mapToDo)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDo(OrderLineItemDto orderLineItemDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemDto.getPrice());
        orderLineItems.setQuantity(orderLineItemDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
        return orderLineItems;
    }
}
