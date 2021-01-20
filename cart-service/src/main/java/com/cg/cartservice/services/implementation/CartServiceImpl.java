package com.cg.cartservice.services.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cartservice.dto.CartDto;
import com.cg.cartservice.dto.ItemDto;
import com.cg.cartservice.entities.Cart;
import com.cg.cartservice.entities.ProductInOrder;
import com.cg.cartservice.entities.ProductInfo;
import com.cg.cartservice.repositories.CartRepository;
import com.cg.cartservice.repositories.ProductInOrderRepository;
//import com.cg.cartservice.repositories.ProductInfo;
import com.cg.cartservice.repositories.ProductInfoRepository;
import com.cg.cartservice.repositories.UserDetailsRepository;
import com.cg.cartservice.services.CartService;
import com.cg.cartservice.services.ProductInOrderService;
import com.cg.cartservice.exception.*;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	ProductInOrderRepository productInOrderRepository;

	@Autowired
	ProductInfoRepository productInfoRepo;

	@Autowired
	CartRepository cartRepo;

	@Autowired
	ProductInOrderService proOrder;

	@Autowired
	UserDetailsRepository userRepo;

	@Override
	public Cart fetchCartById(Long cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CustomException("Wrong id "));
		// if (cart.getProducts().isEmpty()) throw new CustomException("cart is empty");
		return cart;
	}

	@Override
	public Cart addToCart(ItemDto itemDto, Long userId) {

		ProductInfo productInfo = productInfoRepo.findByproductId(itemDto.getProductId())
			.orElseThrow(() -> new CustomException("Product Not found"));
		if (productInfo.getProductStock() < itemDto.getQuantity())
			throw new RuntimeException("Insufficient stock");

		ProductInOrder productInOrder;
		Cart cart = userRepo.findById(userId).orElseThrow(() -> new CustomException("Wrong id ")).getCart();
		Optional<ProductInOrder> old = cart.getProducts().stream().filter(e -> e.getProductId().equals(itemDto.getProductId())).findFirst();
		if (old.isPresent()){
			productInOrder = old.get();
			productInOrder.setProductStock( old.get().getProductStock() + itemDto.getQuantity());
			productInOrder.setProductPrice(BigDecimal.valueOf(productInfo.getProductPrice().floatValue() * productInOrder.getProductStock()));
		}
		else {
			productInOrder = new ProductInOrder();
			productInOrder.setProductCategory(productInfo.getProductCategory().getCategoryDescription());
			productInOrder.setProductId(productInfo.getProductId());
			productInOrder.setProductName(productInfo.getProductName());
			productInOrder.setProductDescription(productInfo.getProductDescription());
			productInOrder.setProductIcon(productInfo.getProductIcon());
			productInOrder.setCart(cart);
			productInOrder.setProductStock(itemDto.getQuantity());
			productInOrder.setProductPrice(productInfo.getProductPrice());
		}
		productInOrderRepository.save(productInOrder);

		return cart;
	}
	
	@Override
	public Cart deleteFromCart(Long productId, Long id) {
		Cart cart = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User ot found")).getCart();
		List<ProductInOrder> products = productInOrderRepository.findByCart_CartId(cart.getCartId());
		if (products.size() == 0) throw new RuntimeException("Cart is empty");
		Optional<ProductInOrder> toBeDeleted = products.stream().filter(p -> p.getProductId().equals(productId)).findFirst();
		if(toBeDeleted.isPresent()){
			toBeDeleted.get().setCart(null);
			productInOrderRepository.deleteById(toBeDeleted.get().getProductInOrderId());
		}
		else throw new RuntimeException("Product not in cart");
		return fetchCartById(cart.getCartId());
	}

	@Override
	public Cart mergeCart(CartDto cartDto, Long id) {

		System.out.println("in cart service :");

		Cart userCart = this.userRepo.findById(id).get().getCart();// get user cart

		System.out.println("User cart: " + userCart);

		List<ProductInOrder> products = new ArrayList<>();
		ProductInOrder prod = null;
		// List<CartDto> products1=new ArrayList<>();

		products = userCart.getProducts().stream().collect(Collectors.toList());

		System.out.println("Products which r already in user cart: " + products);

		for (ItemDto itemDto : cartDto.getItemDtoList()) {

			long id1 = itemDto.getProductId();

			products.add(this.productInOrderRepository.findByProductId(id1));

			// ItemDto id1=cartDto.getItemDtoList().stream().filter(e ->
			// e.getProductId().equals(productInOrder.getProductId()));

		}

		System.out.println("All user products in cart: " + products);

		userCart.setProducts(products.stream().collect(Collectors.toSet()));
		// prod.setCart(userCart);

		System.out.println("now user cart: " + userCart);

		// cartDto.getItemDtoList().forEach(productInOrder -> {//for each product in
		// cartDto
		// Set<ProductInOrder> set = userCart.getProducts();
		// Optional<ProductInOrder> old = set.stream().filter(e ->
		// e.getProductId().equals(productInOrder.getProductId())).findFirst();
		//
		// // if (old.isPresent()) {
		// prod = old.get();
		// System.out.println("product in order before new addition: "+prod);
		// productInfo=this.productInfoRepo.findByproductId(productInOrder.getProductId());
		//
		// prod.setProductCategory(productInfo.getProductCategory().getCategoryDescription());
		// prod.setProductId(productInfo.getProductId().toString());
		// prod.setProductName(productInfo.getProductName());
		// prod.setProductDescription(productInfo.getProductDescription());
		// prod.setProductIcon(productInfo.getProductIcon());
		// prod.setProductPrice(productInfo.getProductPrice());
		// prod.setProductStock(productInfo.getProductStock());
		//
		// prod.setCart(userCart);
		//
		//
		//// } else {
		//// addToCart(itemDto, id);
		//// }
		// System.out.println("product in order after new addition: "+prod);
		// productInOrderRepository.save(prod);
		// });
		return userCart;
	}

	@Override
	public Cart fetchByUserId(Long userId) {
		return cartRepo.findByUserDetails_UserDetailsId(userId).orElseThrow(() -> new RuntimeException());
	}

}
