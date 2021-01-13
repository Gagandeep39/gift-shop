/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:34:12
 * @modify date 2021-01-13 22:34:12
 * @desc [description]
 */
package com.cg.productservice.controller;

import java.util.List;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;
import com.cg.productservice.services.ProductInfoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

  private final ProductInfoService productInfoService;

  @GetMapping
  public List<ProductInfo> fetchAllProducts() {
    return null;
  }

  @GetMapping("/category/{category}")
  public List<ProductInfo> fetchByCategory(@PathVariable String category) {
    return null;
  }

  @GetMapping("/{productId}")
  public ProductInfo fetchById(){
    return null;
  }

  @PutMapping("/increment")
  public ProductInfo increaseStock(StockDto stockDto){
    return null;
  }

  @PostMapping("/decrement")
  public ProductInfo reduceStock(StockDto stockDto){
    return null;
  }

  @DeleteMapping
  public boolean removeProduct(Long productId){
    return false;
  }

  @PostMapping
  public ProductInfo add(ProductInfoDto productInfoDto){
    return null;
  }

  @PutMapping
  public ProductInfo update(ProductInfoDto productInfoDto){
    return null;
  }
  
}
