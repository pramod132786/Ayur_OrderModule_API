package com.anarghya.service;

import java.util.List;

import com.anarghya.model.CustomerAddress;
import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;

public interface OrderModuleService {

	public OrderResponseModel addOrder(Integer cartId,Long customerId);
	
	public OrderDetailsEntity updateOrder(Integer orderId,OrderDetailsEntity orderDetails);
	
	public OrderDetailsEntity updateOrderStatus(Integer orderId,String status);
	
	public OrderDetailsEntity cancelOrder(Integer orderId);
	
	public Double calculateTotalCost(Integer orderId);
	
	public OrderResponseModel getOrderById(Integer orderId);
	
	public List<OrderDetailsEntity> showOrderByCustomer(Long customerId);
	
	
	
	
	
	public List<OrderDetailsEntity> getOrderDetails();

//===================================================================
	
	public String upsertOrderDetails(OrderDetailsEntity orderDetails);

	public OrderResponseModel getDetailsCart(Integer cartId);

	

	public String deleteOrderById(Integer orderId);
	
	public List<OrderResponseModel> getCustomerOrderDetails();
	
	
	
	public CustomerAddress addAddress(CustomerAddress customerAddress,Long customerId);
	
	public List<CustomerAddress> getAddressesByCustomerId(Long customerId);
	
	
	
	

}
