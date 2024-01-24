package com.anarghya.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anarghya.client.FeignCartClient;
import com.anarghya.client.FeignClientDemo;
import com.anarghya.client.FeignClientMedicine;
import com.anarghya.exception.EntityNotFoundException;
import com.anarghya.model.CartModuleEntityRequest;
import com.anarghya.model.CustomerAddress;
import com.anarghya.model.CustomerModel;
import com.anarghya.model.MedicineModule;
import com.anarghya.model.OrderDetailsEntity;
import com.anarghya.model.OrderResponseModel;
import com.anarghya.repository.CustomerAddressRepo;
import com.anarghya.repository.OrderModuleRepo;

@Service
public class OrderModuleServiceImpl implements OrderModuleService {

	@Autowired
	private OrderModuleRepo orderRepo;

	@Autowired
	private FeignClientDemo feign;

	@Autowired
	private FeignCartClient feignCart;

	@Autowired
	private CustomerAddressRepo customerAddressRepo;
	
	@Autowired
	private FeignClientMedicine feignMedicine;

	@Override
	public String upsertOrderDetails(OrderDetailsEntity orderDetails) {

		if (orderDetails != null) {
			LocalDate date = orderDetails.getOrderDate();
			LocalDate dispatchDate = date.plusDays(15); // Add 15 days to the order date

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Adjust the pattern as needed
			String dispatchDateAsString = dispatchDate.format(formatter);

			orderDetails.setOrderDispatchDate(dispatchDateAsString);
			orderRepo.save(orderDetails);

			return "OrderPlaced";
		} else {
			return "OrderNotPlaced";
		}
	}

	public OrderResponseModel getDetailsCart(Integer cartId) {
		CartModuleEntityRequest cartModuleEntity = new CartModuleEntityRequest();
		OrderResponseModel orderResponse = new OrderResponseModel();

		ArrayList<MedicineModule> medicineList = new ArrayList<>();
		CartModuleEntityRequest cartEntity = feignCart.viewCartInfo(cartId).getBody();
		List<MedicineModule> medicines = cartEntity.getMedicines();
		medicines.forEach(m -> medicineList.add(m));
		
		
		cartModuleEntity.setMedicines(medicineList);
		orderResponse.setCartModule(cartModuleEntity);
		return orderResponse;

	}

	public OrderResponseModel getOrderById(Integer orderId) {
		OrderResponseModel orderResponseModel=new OrderResponseModel();
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
		   OrderDetailsEntity orderDetailsEntity = findById.get();
		   Long customerId = orderDetailsEntity.getCustomerId();
		   CustomerModel viewCustomer = feign.viewCustomer(customerId);
		   Integer cartId = orderDetailsEntity.getCartId();
		   CartModuleEntityRequest cartModuleEntityRequest = feignCart.viewCartInfo(cartId).getBody();
		   orderResponseModel.setCartModule(cartModuleEntityRequest);
		   orderResponseModel.setCustomerModel(viewCustomer);
		   orderResponseModel.setOrderEntity(orderDetailsEntity);
		   return orderResponseModel;
		}
		return null;
	}

	@Override
	public String deleteOrderById(Integer orderId) {

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
		OrderResponseModel orderResponseModel = new OrderResponseModel();
		CustomerModel customer = new CustomerModel();

//		List<OrderDetailsEntity> orderDetails = new ArrayList<OrderDetailsEntity>();
//				orderRepo.findAll().forEach(p -> orderDetails.add(p));
//
//		List<CustomerModel> customerModel = feign.getCustomerDetails();
//		
//		for (OrderDetailsEntity orderModel : orderDetails) {
//			orderResponseModel.setOrderId(orderModel.getOrderId());
//			orderResponseModel.setOrderDate(orderModel.getOrderDate());
//			orderResponseModel.setOrderDispatchDate(orderModel.getOrderDispatchDate());
//			orderResponseModel.setCost(orderModel.getCost());
//			orderResponseModel.setStatus(orderModel.getStatus());
//			orderResponseModel.setTotalCost(orderModel.getTotalCost());
//			mainResponse.add(orderResponseModel);
//		}
//		
//		for (CustomerModel customerData : customerModel) {
//			customer.setCustomerId(customerData.getCustomerId());
//			customer.setCustomerName(customerData.getCustomerName());
//			customer.setEmailId(customerData.getEmailId());
//			customer.setMoblieNo(customerData.getMoblieNo());
//			customer.setPassword(customerData.getPassword());
//			orderResponseModel.setCustomerModel(customerModel);
//			
//		}
		return mainResponse;
	}

//===================================================================================	

	@Override
	public List<OrderDetailsEntity> getOrderDetails() {
		List<OrderDetailsEntity> entity = new ArrayList<OrderDetailsEntity>();
		orderRepo.findAll().forEach(p -> entity.add(p));
		return entity;
	}
	

	@Override
	public OrderResponseModel addOrder(Integer cartId, Long customerId) {
		CartModuleEntityRequest cartModuleEntity = new CartModuleEntityRequest();
		OrderResponseModel orderResponse = new OrderResponseModel();

		ArrayList<MedicineModule> medicineList = new ArrayList<>();
		OrderDetailsEntity orderEntity = new OrderDetailsEntity();
		CustomerModel customer = new CustomerModel();
		MedicineModule medicineEntity = new MedicineModule();

		if (cartId != null) {
//			List<OrderDetailsEntity> existingOrders = orderRepo.findByCustomerId(customerId);
//			if (!existingOrders.isEmpty()) {
//				orderEntity = existingOrders.get(0);
//				CartModuleEntityRequest cartEntity = feignCart.viewCartInfo(cartId).getBody();
//				System.out.println(cartEntity.getMedicines().toString());
//				CustomerModel customerDetails = feign.viewCustomer(customerId);
//				customer.setCustomerId(customerDetails.getCustomerId());
//				customer.setCustomerName(customerDetails.getCustomerName());
//				customer.setEmailId(customerDetails.getEmailId());
//				customer.setMoblieNo(customerDetails.getMoblieNo());
//				cartModuleEntity.setCartId(cartEntity.getCartId());
//				cartModuleEntity.setCost(cartEntity.getCost());
//				cartModuleEntity.setQuantity(cartEntity.getQuantity());
//				List<MedicineModule> medicines = cartEntity.getMedicines();
//				medicines.forEach(m -> medicineList.add(m));
//				cartModuleEntity.setMedicines(medicineList);
//				orderResponse.setCartModule(cartModuleEntity);
//				orderResponse.setOrderEntity(orderEntity);
//				orderResponse.setCustomerModel(customer);
//
//				return orderResponse;
//				
//			} else {
				orderEntity = new OrderDetailsEntity();
				orderEntity.setOrderDate(LocalDate.now());
				
				LocalDate date = orderEntity.getOrderDate();
				LocalDate dispatchDate = date.plusDays(15); // Add 15 days to the order date

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Adjust the pattern as needed
				String dispatchDateAsString = dispatchDate.format(formatter);

				orderEntity.setOrderDispatchDate(dispatchDateAsString);
				orderEntity.setStatus("pending");
				 String trackId = "TRACK-" + (int) (Math.random() * 10000000);
				 
				 orderEntity.setTrackId(trackId);
				CartModuleEntityRequest cartEntity = feignCart.viewCartInfo(cartId).getBody();
				System.out.println(cartEntity.getMedicines().toString());
				CustomerModel customerDetails = feign.viewCustomer(customerId);
				customer.setCustomerId(customerDetails.getCustomerId());
				customer.setCustomerName(customerDetails.getCustomerName());
				customer.setEmailId(customerDetails.getEmailId());
				customer.setMoblieNo(customerDetails.getMoblieNo());
				cartModuleEntity.setCartId(cartEntity.getCartId());
				cartModuleEntity.setCost(cartEntity.getCost());
				cartModuleEntity.setQuantity(cartEntity.getQuantity());
				List<MedicineModule> medicines = cartEntity.getMedicines();
				
			        for (MedicineModule medicineModule : medicines) {
						int quantity = medicineModule.getQuantity();
						Long id = medicineModule.getId();
						feignMedicine.decreaseQuantity(id, quantity);
			
			     }
				medicines.forEach(m -> medicineList.add(m));
				cartModuleEntity.setMedicines(medicineList);
				orderEntity.setTotalCost(cartEntity.getCost());
				orderEntity.setQuantity(cartEntity.getQuantity());
				orderEntity.setCartId(cartEntity.getCartId());
//				orderEntity.set
				orderEntity.setCustomerId(customerDetails.getCustomerId());
				orderEntity.setCustomerEmailId(customerDetails.getEmailId());
				orderResponse.setCartModule(cartModuleEntity);
				orderResponse.setOrderEntity(orderEntity);
				orderResponse.setCustomerModel(customer);
				
				orderRepo.save(orderEntity);
				return orderResponse;
			}
//		}	
			return null;
	}

	@Override
	public OrderDetailsEntity updateOrder(Integer orderId, OrderDetailsEntity orderDetails) {
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
			return orderRepo.save(orderDetails);
		}
		return null;
	}

	@Override
	public OrderDetailsEntity updateOrderStatus(Integer orderId, String status) {
		System.out.println(orderId);
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
			OrderDetailsEntity orderDetailsEntity = findById.get();
			orderDetailsEntity.setStatus(status);
			orderRepo.save(orderDetailsEntity);
			return orderDetailsEntity;
		}

		else {
			return null;
		}
	}

	@Override
	public OrderDetailsEntity cancelOrder(Integer orderId) {
//		OrderDetailsEntity findByCustomerId = orderRepo.findByCustomerId(customerId);
		Optional<OrderDetailsEntity> findById = orderRepo.findById(orderId);
		if (findById.isPresent()) {
			OrderDetailsEntity orderDetailsEntity = findById.get();
			String status = orderDetailsEntity.getStatus();
			if (status.contains("pending") || status.contains("approved")) {
				orderDetailsEntity.setStatus("cancel");
				OrderDetailsEntity save = orderRepo.save(orderDetailsEntity);
				return save;

			} else {

			}
			return null;
		} else {

		}
		return null;
	}

	@Override
	public Double calculateTotalCost(Integer orderId) {
		return null;
	}

	@Override
	public List<OrderDetailsEntity> showOrderByCustomer(Long customerId) {
		ArrayList<OrderDetailsEntity> orders = new ArrayList<>();
		List<OrderDetailsEntity> findByCustomerId = orderRepo.findByCustomerId(customerId);
		if (findByCustomerId.isEmpty()) {
			return null;
		} else {
			findByCustomerId.forEach(c -> orders.add(c));
			return orders;
		}
	}

	// @Override
//	public CustomerAddress addAddress(CustomerAddress customerAddress, Long customerId) {
//		CustomerModel customer = feign.viewCustomer(customerId);
//		if (customer == null) {
//			throw new EntityNotFoundException("Customer not found with ID: " + customerId);
//		}
//		customerAddress.setCustomermodel(customer);
//		CustomerAddress savedAddress = customerAddressRepo.save(customerAddress);
//		return savedAddress;
//	}
	public CustomerAddress addAddress(CustomerAddress customerAddress, Long customerId) {
		// Fetch customer details using Feign client
//		CustomerAddress customerAddressEntity=new CustomerAddress();
		CustomerModel customer = feign.viewCustomer(customerId);
		

		// Check if the customer exists
		if (customer == null) {
			throw new EntityNotFoundException("Customer not found with ID: " + customerId);
		}

		// Associate the customer with the address
//		customerAddressEntity.setCustomermodel(customer);
//		customerAddress.setCustomermodel(customerAddressEntity.getCustomermodel());
		customerAddress.setCustomerId(customer.getCustomerId());
		System.out.println(customer.getCustomerName());

		// Save the address
		CustomerAddress savedAddress = customerAddressRepo.save(customerAddress);
		return savedAddress;
	}

	@Override
	public List<CustomerAddress> getAddressesByCustomerId(Long customerId) {
		return customerAddressRepo.findByCustomerId(customerId);
	}
}
