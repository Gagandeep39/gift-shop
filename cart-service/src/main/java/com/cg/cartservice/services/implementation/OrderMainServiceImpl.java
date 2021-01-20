package com.cg.cartservice.services.implementation;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cartservice.entities.Address;
import com.cg.cartservice.entities.OrderMain;
import com.cg.cartservice.entities.ProductInOrder;
import com.cg.cartservice.entities.ProductInfo;
import com.cg.cartservice.entities.UserDetails;
import com.cg.cartservice.enums.OrderStatus;
import com.cg.cartservice.enums.PaymentType;
import com.cg.cartservice.exception.CustomException;
import com.cg.cartservice.repositories.CartRepository;
import com.cg.cartservice.repositories.OrderMainRepository;
import com.cg.cartservice.repositories.ProductInOrderRepository;
import com.cg.cartservice.repositories.ProductInfoRepository;
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
	@Autowired
	CartRepository cartRepository;
	@Autowired
	ProductInfoRepository productInfoRepository;

	@Override
	public Map<String, String> checkOut(Long id) {
		UserDetails user = this.userRepo.findById(id).orElseThrow(() -> new CustomException("user", "Invalid User ID"));
		Set<ProductInOrder> products = user.getCart().getProducts();
		if(products.size() == 0) throw new CustomException("cart", "Cart is empty");
		OrderMain orderMain = new OrderMain();
		orderMain.setUserId(user.getUserDetailsId());
		orderMain.setBuyerAddress(createUserAddress(user.getAddress()));// change addr
		orderMain.setBuyerCity(user.getAddress().getCity());
		orderMain.setBuyerEmail(user.getEmailId());
		orderMain.setBuyerName(user.getFirstName() + "  " + user.getLastName());
		orderMain.setBuyerPhone(user.getPhoneNo());
		orderMain.setBuyerPincode(user.getAddress().getPincode());
		orderMain.setBuyerState(user.getAddress().getState());
		orderMain.setOrderStatus(OrderStatus.NEW);
		// TODO - Add payment ID logic
		orderMain.setPaymentId((long) 1000011000);	// Dummyy
		orderMain.setPaymentType(PaymentType.ONLINE);
		orderRepo.save(orderMain);
		Float total = 0F;
		for (ProductInOrder p : products) {
			p.setCart(null);
			p.setOrderMain(orderMain);
			total += p.getProductPrice().floatValue();
      reduceStock(p.getProductId(), p.getProductStock());
      productRepo.save(p);
		}
		orderMain.setOrderAmount(BigDecimal.valueOf(total));
		orderRepo.save(orderMain);

		return Collections.singletonMap("orderId", orderMain.getOrderId().toString());
	}

	private String createUserAddress(Address address) {
		return address.getArea() + ", " + address.getCity() + ", " + address.getState() + " " + address.getPincode();
	}

	public ProductInfo reduceStock(Long productId, Integer quantity) {
		ProductInfo productInfo = productInfoRepository.findById(productId).orElseThrow(() ->  new CustomException("product", "Not Found"));
		if (quantity > productInfo.getProductStock())
			throw new CustomException("product", "Insfficient products");
		else
			productInfo.setProductStock(productInfo.getProductStock() - quantity);
		return productInfo;
	}

}
