package com.anarghya.service;

import java.util.List;

import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;

public interface OrderModuleService {

	public List<OrderDetailsEntity> getOrderDetails();

	public String upsertOrderDetails(OrderDetailsEntity orderDetails);

	public String update(OrderDetailsEntity orderDetails, Integer orderId);

	public OrderDetailsEntity getOrderById(Integer orderId);

	public String deleteOrderById(Integer orderId);
	
	public List<OrderResponseModel> getCustomerOrderDetails();

}
