package com.cg.cartservice.services.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	@Autowired
	private ProductInfoRepository productRepository;

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
		Cart finalCart = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User ot found")).getCart();
		List<ProductInOrder> productInOrders = new ArrayList<>();


		// Create ProfuctPrderList for local items
		for (ItemDto item : cartDto.getItemDtoList()) {
			ProductInfo newProduct = productRepository.findById(item.getProductId()).orElseThrow(() -> new RuntimeException("Invalid product id"));
			ProductInOrder productInOrder = new ProductInOrder();
			productInOrder.setProductCategory(newProduct.getProductCategory().getCategoryDescription());
			productInOrder.setProductId(newProduct.getProductId());
			productInOrder.setProductName(newProduct.getProductName());
			productInOrder.setProductDescription(newProduct.getProductDescription());
			productInOrder.setProductIcon(newProduct.getProductIcon());
			productInOrder.setProductStock(item.getQuantity());
			productInOrder.setProductPrice(newProduct.getProductPrice());
			;
			productInOrders.add(productInOrder);
		}

		// Merge
    productInOrders.forEach(productInOrder -> {
        Set<ProductInOrder> set = finalCart.getProducts();
        Optional<ProductInOrder> old = set.stream().filter(e -> e.getProductId().equals(productInOrder.getProductId())).findFirst();
            ProductInOrder prod;
        if (old.isPresent()) {
            prod = old.get();
            prod.setProductStock(productInOrder.getProductStock() + prod.getProductStock());
        } else {
            prod = productInOrder;
            prod.setCart(finalCart);
            finalCart.getProducts().add(prod);
        }
        productInOrderRepository.save(prod);
    });
    cartRepo.save(finalCart);
		return finalCart;
	}

	@Override
	public Cart fetchByUserId(Long userId) {
		return cartRepo.findByUserDetails_UserDetailsId(userId).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
