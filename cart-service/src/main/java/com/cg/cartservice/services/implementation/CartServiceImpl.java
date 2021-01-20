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
import com.cg.cartservice.entities.UserDetails;
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
	ProductInOrderRepository productRepo;

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
		if (cart.getProducts().isEmpty()) throw new CustomException("cart is empty");
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
		productRepo.save(productInOrder);

		return cart;
	}

	@Override
	public Cart deleteFromCart(Long productId, Long id) {

		System.out.println("In service delete method");

		UserDetails details;
		Cart cart = new Cart();

		ProductInOrder prod;

		details = this.userRepo.findById(id).orElseThrow(() -> new CustomException("Wrong id "));
		cart = details.getCart();

		System.out.println("cart: " + cart);

		System.out.println("ProductInOrder before deletion: " + productRepo.findAll());

		// prod=cart.getProducts().stream().filter(e ->
		// e.getProductId().equals(productId)).findFirst();

		this.productRepo.deleteById(productId);

		System.out.println("ProductInOrder after deletion: " + productRepo.findAll());

		return cart = fetchCartById(cart.getCartId());

		// UserDetails details=new UserDetails();
		//
		// Cart cart=new Cart();
		//
		//
		//
		// List<ProductInOrder> prod=new ArrayList<>();
		// prod=this.proOrder.fetchByCartId(id);
		// System.out.println("Products: "+prod );

		// details=this.userRepo.findById(id).get();

		// System.out.println("User details: "+details);
		// prod=details.getCart().getProducts().stream().collect(Collectors.toList());

		// System.out.println("products in cart: "+prod);

		// prod=cart.getProducts().stream().filter(e ->
		// e.getProductId().equals(productId)).findFirst();

		// Optional<ProductInOrder> id1=prod.stream().filter(e ->
		// e.getProductId().equals(productId)).findAny();
		//
		// ProductInOrder id2=id1.get();
		//
		// System.out.println("Product in order id: "+id2.getProductInOrderId());
		//
		//
		// this.productRepo.deleteById(id2.getProductInOrderId());
		//

		// return cart=this.fetchCartById(cart.getCartId());
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

			products.add(this.productRepo.findByProductId(id1));

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
		// productRepo.save(prod);
		// });
		return userCart;
	}

	@Override
	public Cart fetchByUserId(Long userId) {
		return cartRepo.findByUserDetails_UserDetailsId(userId).orElseThrow(() -> new RuntimeException());
	}

}
