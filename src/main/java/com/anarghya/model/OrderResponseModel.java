package com.anarghya.model;

import java.util.List;

public class OrderResponseModel{

	private Integer orderId;
	private String orderDate;
	private String orderDispatchDate;
	private Integer cost;
	private Float totalCost;
	private String status;
	
	
	private List<CustomerModel> customerModel;
	public List<CustomerModel> getCustomerModel() {
		return customerModel;
	}

	public void setCustomerModel(List<CustomerModel> customerModel) {
		this.customerModel = customerModel;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDispatchDate() {
		return orderDispatchDate;
	}

	public void setOrderDispatchDate(String orderDispatchDate) {
		this.orderDispatchDate = orderDispatchDate;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
