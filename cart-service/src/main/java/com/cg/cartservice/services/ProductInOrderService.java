package com.cg.cartservice.services;

import java.util.List;

import com.cg.cartservice.entities.ProductInOrder;

public interface ProductInOrderService {

  ProductInOrder create(ProductInOrder productInOrder);

  ProductInOrder update(ProductInOrder productInOrder);

  Long delete(Long id);

  List<ProductInOrder> fetchByOrderId(Long id);

  List<ProductInOrder> fetchByCartId(Long id);

}
