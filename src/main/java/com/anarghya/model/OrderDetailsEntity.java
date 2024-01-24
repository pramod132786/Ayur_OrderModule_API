package com.anarghya.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Order_Details")
public class OrderDetailsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	private LocalDate orderDate;
	private String orderDispatchDate;
	private Integer quantity;
	private Double totalCost;
	private String status;
	private Long customerId;
	private Integer cartId;

	private String customerEmailId;
//	private String name;
	private String address;
	private String trackId;
   private String medicineId;
    
    
	
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
//	public List<MedicineModule> getMedicine() {
//		return medicine;
//	}
//	public void setMedicine(List<MedicineModule> medicine) {
//		this.medicine = medicine;
//	}
	public Integer getCartId() { 
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderDispatchDate() {
		return orderDispatchDate;
	}
	public void setOrderDispatchDate(String orderDispatchDate) {
		this.orderDispatchDate = orderDispatchDate;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
//	public List<MedicineModule> getMedicine() {
//		return medicine;
//	}
//	public void setMedicine(List<MedicineModule> medicine) {
//		this.medicine = medicine;
//	}
	
	
	
}
