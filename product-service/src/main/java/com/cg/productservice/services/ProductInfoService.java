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
import com.cg.productservice.dto.ProductInfoRequest;
import com.cg.productservice.dto.StockDto;

public interface ProductInfoService {

  public List<ProductInfoDto> fetchAll();

  public List<ProductInfoDto> fetchByCategory(String category);

  public ProductInfoDto fetchById(Long id);

  public ProductInfoDto increaseStock(StockDto stockDto);

  public ProductInfoDto reduceStock(StockDto stockDto);
  
  public ProductInfoDto updateStock(StockDto stockDto);

  public boolean removeProduct(Long productId);

  public ProductInfoDto add(ProductInfoRequest productInfoDto);

  public ProductInfoDto update(ProductInfoRequest productInfoDto);

}
