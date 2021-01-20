package com.cg.cartservice.services.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cartservice.entities.Cart;
import com.cg.cartservice.entities.OrderMain;
import com.cg.cartservice.entities.ProductInOrder;
import com.cg.cartservice.entities.UserDetails;
import com.cg.cartservice.enums.OrderStatus;
import com.cg.cartservice.enums.PaymentType;
import com.cg.cartservice.exception.CustomException;
import com.cg.cartservice.repositories.OrderMainRepository;
import com.cg.cartservice.repositories.ProductInOrderRepository;
import com.cg.cartservice.repositories.UserDetailsRepository;
import com.cg.cartservice.services.OrderMainService;

@Service
public class OrderMainServiceImpl implements OrderMainService {

	@Autowired
	UserDetailsRepository userRepo;

	@Autowired
	ProductInOrderRepository productRepo;

	@Autowired
	OrderMainRepository orderRepo;

	@Override
	public OrderMain checkOut(Long id) {

		UserDetails user = this.userRepo.findById(id).orElseThrow(() -> new CustomException("Wrong id "));

		Cart userCart = this.userRepo.findById(id).get().getCart();

		List<ProductInOrder> products = new ArrayList<>();

		OrderMain order = new OrderMain();

		long total = 0;

		products = userCart.getProducts().stream().collect(Collectors.toList());

		order.setBuyerAddress(user.getAddress().toString());// change addr
		order.setBuyerCity(user.getAddress().getCity());
		order.setBuyerEmail(user.getEmailId());
		order.setBuyerName(user.getFirstName() + "  " + user.getLastName());
		order.setBuyerPhone(user.getPhoneNo());
		order.setBuyerPincode(user.getAddress().getPincode());
		order.setBuyerState(user.getAddress().getState());

		for (ProductInOrder productInOrder : products) {

			total = total + productInOrder.getProductPrice().longValue();

			order.setOrderStatus(OrderStatus.NEW);
			order.setPaymentId((long) 1000011000);// change to proper payment id
			order.setPaymentType(PaymentType.ONLINE);

			order.setUserId(user.getUserDetailsId());

		}

		BigDecimal amount = new BigDecimal(total);

		order.setOrderAmount(amount);
		order.setProducts(products.stream().collect(Collectors.toSet()));

		this.orderRepo.save(order);

		userCart.setProducts(null);

		// Cart userCart = this.userRepo.findByUserDetailsId(id).getCart();

		return order;
	}

}
