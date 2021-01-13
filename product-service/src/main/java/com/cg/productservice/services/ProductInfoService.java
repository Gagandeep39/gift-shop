/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:34:14
 * @modify date 2021-01-13 22:34:14
 * @desc [description]
 */
package com.cg.productservice.services;

import java.util.List;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;

public interface ProductInfoService {

  public List<ProductInfo> fetchAll();

  public List<ProductInfo> fetchByCategory(String category);

  public ProductInfo fetchById();

  public ProductInfo increaseStock(StockDto stockDto);

  public ProductInfo reduceStock(StockDto stockDto);

  public boolean removeProduct(Long productId);

  public ProductInfo add(ProductInfoDto productInfoDto);

  public ProductInfo update(ProductInfoDto productInfoDto);

}
