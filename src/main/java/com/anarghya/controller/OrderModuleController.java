package com.anarghya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;
import com.anarghya.service.OrderModuleService;

@RestController
public class OrderModuleController {
	@Autowired
	private OrderModuleService service;

	

	@GetMapping("/getAll")
	public List<OrderResponseModel> getCustomerOrderDetails() {
		
		List<OrderResponseModel> mainResponse = service.getCustomerOrderDetails();
		
		return mainResponse;

	}
	
	@GetMapping("/get")
	public List<OrderDetailsEntity> getOrders(){
		return service.getOrderDetails();
	}
	
	

	@GetMapping("/orderDetails")
	public List<OrderDetailsEntity> getOrderDetails() {

		return service.getOrderDetails();

	}

	@PostMapping("/save")
	public ResponseEntity<String> saveDetails(@RequestBody OrderDetailsEntity orderDetails) {
		System.out.println(orderDetails);
		String addOrderDetails = service.upsertOrderDetails(orderDetails);

		return new ResponseEntity<String>(addOrderDetails, HttpStatus.CREATED);
	}

	@PutMapping("/updateOrder")
	public ResponseEntity<String> updateDetails(@RequestBody OrderDetailsEntity orderDettails) {
		String orderDetails = service.upsertOrderDetails(orderDettails);
		return new ResponseEntity<>(orderDetails, HttpStatus.CREATED);
	}

	@GetMapping("/edit/{orderId}")
	public OrderDetailsEntity getOrderById(@PathVariable Integer orderId) {

		return service.getOrderById(orderId);
	}

	@PutMapping("/update/{orderId}")
	public ResponseEntity<String> updateById(@PathVariable Integer orderId,
			@RequestBody OrderDetailsEntity updateOrderDetails) {

		String updatedDetails = service.update(updateOrderDetails, orderId);
		return new ResponseEntity<>(updatedDetails, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer orderId) {

		String deleteOrderById = service.deleteOrderById(orderId);

		return new ResponseEntity<>(deleteOrderById, HttpStatus.GONE);
	}
}
