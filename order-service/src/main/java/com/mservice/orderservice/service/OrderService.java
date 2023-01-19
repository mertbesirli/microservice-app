package com.mservice.orderservice.service;

import com.mservice.orderservice.dto.OrderLineItemDto;
import com.mservice.orderservice.dto.OrderRequest;
import com.mservice.orderservice.entity.OrderLineItems;

public interface OrderService {

   String placeOrder(OrderRequest orderRequest);
}
