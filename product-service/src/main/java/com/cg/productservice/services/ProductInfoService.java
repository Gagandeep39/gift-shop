package com.cg.productservice.services;

import java.util.List;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;

public interface ProductInfoService {

  public List<ProductInfo> fetchAll();

  public List<ProductInfo> fetchByCategory();

  public ProductInfo fetchById();

  public ProductInfo increaseStock(StockDto stockDto);

  public ProductInfo reduceStock(StockDto stockDto);

  public boolean removeProduct(Long productId);

  public ProductInfo add(ProductInfoDto productInfoDto);

  public ProductInfo update(ProductInfoDto productInfoDto);
  
}
