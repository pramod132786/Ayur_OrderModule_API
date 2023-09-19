package com.anarghya.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.anarghya.model.CustomerModel;

@FeignClient(value="feignDemo",url="http://localhost:8081")
public interface FeignClientDemo {
	
	@GetMapping("/getcustomer")
	public List<CustomerModel> getCustomerDetails();

	
}
