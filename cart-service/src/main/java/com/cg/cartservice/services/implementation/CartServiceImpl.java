package com.cg.cartservice.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cartservice.dto.CartDto;
import com.cg.cartservice.dto.ItemDto;
import com.cg.cartservice.entities.Cart;
import com.cg.cartservice.entities.ProductInOrder;
import com.cg.cartservice.entities.ProductInfo;
import com.cg.cartservice.entities.User;
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

	ProductInfo productInfo;
	ProductInOrder productInOrder;
	Cart cart;
	User user;
	// UserDetails details;

	@Override
	public Cart fetchCartById(Long cartId) {

		cart = this.cartRepo.findById(cartId).orElseThrow(() -> new CustomException("Wrong id "));
		if (cart.getProducts().isEmpty()) {
			throw new CustomException("cart is empty");
		}

		// cart=this.cartRepo.findByCartId(cartId);

		return cart;
	}

	@Override
	public Cart addToCart(ItemDto itemDto, Long id) {

		productInfo = new ProductInfo();

		productInOrder = new ProductInOrder();

		UserDetails details;

		Cart cart = new Cart();

		details = this.userRepo.findById(id).orElseThrow(() -> new CustomException("Wrong id "));
		cart = details.getCart();

		productInfo = this.productInfoRepo.findByproductId(itemDto.getProductId());

		productInOrder.setProductCategory(productInfo.getProductCategory().getCategoryDescription());
		productInOrder.setProductId(productInfo.getProductId().toString());
		productInOrder.setProductName(productInfo.getProductName());
		productInOrder.setProductDescription(productInfo.getProductDescription());
		productInOrder.setProductIcon(productInfo.getProductIcon());
		productInOrder.setProductPrice(productInfo.getProductPrice());
		productInOrder.setProductStock(itemDto.getQuantity());
		productInOrder.setCart(cart);

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
