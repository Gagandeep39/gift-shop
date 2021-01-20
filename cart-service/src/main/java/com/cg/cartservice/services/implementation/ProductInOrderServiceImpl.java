package com.cg.cartservice.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cartservice.entities.ProductInOrder;
import com.cg.cartservice.repositories.CartRepository;
import com.cg.cartservice.repositories.ProductInOrderRepository;
import com.cg.cartservice.repositories.ProductInfoRepository;
import com.cg.cartservice.services.ProductInOrderService;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

	@Autowired
	ProductInOrderRepository productRepo;

	@Autowired
	ProductInfoRepository productInfoRepo;

	@Autowired
	CartRepository cartRepo;

	List<ProductInOrder> productsInOrder = new ArrayList<ProductInOrder>();

	@Override
	public ProductInOrder create(ProductInOrder productInOrder) {
		return productRepo.save(productInOrder);
	}

	@Override
	public ProductInOrder update(ProductInOrder productInOrder) {
		return productRepo.save(productInOrder);
	}

	@Override
	public Long delete(Long id) {
		productRepo.deleteById(id);
		return id;
	}

	@Override
	public List<ProductInOrder> fetchByOrderId(Long id) {
		return productsInOrder.stream().filter(orderId -> orderId.getOrderMain().getOrderId().equals(id))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductInOrder> fetchByCartId(Long id) {
		productsInOrder = this.productRepo.findAll();
		System.out.println(productsInOrder);
		return productsInOrder.stream().filter(x -> x.getCart().getCartId().equals(id)).collect(Collectors.toList());
	}

}
