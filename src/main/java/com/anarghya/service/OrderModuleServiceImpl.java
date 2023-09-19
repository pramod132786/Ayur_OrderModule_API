package com.anarghya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anarghya.client.FeignClientDemo;
import com.anarghya.model.CustomerModel;
import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;
import com.anarghya.repository.OrderModuleRepo;

@Service
public class OrderModuleServiceImpl implements OrderModuleService {

	@Autowired
	private OrderModuleRepo orderRepo;
	
	@Autowired
	private FeignClientDemo feign;

	@Override
	public List<OrderDetailsEntity> getOrderDetails() {
		List<OrderDetailsEntity> entity = new ArrayList<OrderDetailsEntity>();
		orderRepo.findAll().forEach(p -> entity.add(p));
		// TODO Auto-generated method stub
		return entity;
	}

	@Override
	public String upsertOrderDetails(OrderDetailsEntity orderDetails) {

		if (orderDetails != null) {
			orderRepo.save(orderDetails);
			return "OrderPlaced";
		} else {
			return "OrderNotPlaced";
		}

	}

	public String update(OrderDetailsEntity orderDetails, Integer orderId) {
		orderRepo.save(orderDetails);
		return "Order Updated";

	}

	public OrderDetailsEntity getOrderById(Integer orderId) {
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
			return findById.get();
		}

		return null;
	}

	@Override
	public String deleteOrderById(Integer orderId) {
		// TODO Auto-generated method stub

		if (orderRepo.existsById(orderId)) {
			orderRepo.deleteById(orderId);
			return "Record orderId : " + orderId + " is Deleted";
		} else {
			return "Record is not deleted";
		}

	}

	@Override
	public List<OrderResponseModel> getCustomerOrderDetails() {
		List<OrderResponseModel> mainResponse = new ArrayList<OrderResponseModel>();
		OrderResponseModel orderResponseModel =new OrderResponseModel();
		CustomerModel customer= new CustomerModel();

		List<OrderDetailsEntity> orderDetails = new ArrayList<OrderDetailsEntity>();
				orderRepo.findAll().forEach(p -> orderDetails.add(p));

		List<CustomerModel> customerModel = feign.getCustomerDetails();
		
		for (OrderDetailsEntity orderModel : orderDetails) {
			orderResponseModel.setOrderId(orderModel.getOrderId());
			orderResponseModel.setOrderDate(orderModel.getOrderDate());
			orderResponseModel.setOrderDispatchDate(orderModel.getOrderDispatchDate());
			orderResponseModel.setCost(orderModel.getCost());
			orderResponseModel.setStatus(orderModel.getStatus());
			orderResponseModel.setTotalCost(orderModel.getTotalCost());
			mainResponse.add(orderResponseModel);
		}
		
		for (CustomerModel customerData : customerModel) {
			customer.setCustomerId(customerData.getCustomerId());
			customer.setCustomerName(customerData.getCustomerName());
			customer.setEmailId(customerData.getEmailId());
			customer.setMoblieNo(customerData.getMoblieNo());
			customer.setPassword(customerData.getPassword());
			orderResponseModel.setCustomerModel(customerModel);
			
		}
		return  mainResponse;
	}

}
