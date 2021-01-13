/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:40:25
 * @modify date 2021-01-13 22:40:25
 * @desc [description]
 */
package com.cg.productservice.services.implementation;

import java.util.List;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;
import com.cg.productservice.repositories.ProductInfoRepository;
import com.cg.productservice.services.ProductInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

  @Autowired
  private ProductInfoRepository productInfoRepository;

  @Override
  public List<ProductInfo> fetchAll() {
    // Auto-generated method stub
    return null;
  }

  @Override
  public List<ProductInfo> fetchByCategory(String category) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public ProductInfo fetchById() {
    // Auto-generated method stub
    return null;
  }

  @Override
  public ProductInfo increaseStock(StockDto stockDto) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public ProductInfo reduceStock(StockDto stockDto) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public boolean removeProduct(Long productId) {
    // Auto-generated method stub
    return false;
  }

  @Override
  public ProductInfo add(ProductInfoDto productInfoDto) {
    // Auto-generated method stub
    return null;
  }

  @Override
  public ProductInfo update(ProductInfoDto productInfoDto) {
    // Auto-generated method stub
    return null;
  }

}
