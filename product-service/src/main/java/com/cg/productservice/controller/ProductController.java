/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:34:12
 * @modify date 2021-01-13 22:34:12
 * @desc [description]
 */
package com.cg.productservice.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;
import com.cg.productservice.services.ProductInfoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    return productInfoService.fetchAll();
  }

  @GetMapping("/category/{category}")
  public List<ProductInfo> fetchByCategory(@PathVariable String category) {
    return productInfoService.fetchByCategory(category);
  }

  @GetMapping("/{productId}")
  public ProductInfo fetchById(@PathVariable Long productId) {
    return productInfoService.fetchById(productId);
  }

  @PutMapping("/stock")
  public ProductInfo updateStock(@RequestBody StockDto stockDto) {
    return productInfoService.updateStock(stockDto);
  }

  @PutMapping("/increment")
  public ProductInfo increaseStock(StockDto stockDto) {
    return null;
  }

  @PostMapping("/decrement")
  public ProductInfo reduceStock(StockDto stockDto) {
    return null;
  }

  @DeleteMapping("/{productId}")
  public boolean removeProduct(@PathVariable Long productId) {
    return productInfoService.removeProduct(productId);
  }

  @PostMapping
  public Map<String, String> add(@Valid @RequestBody ProductInfoDto productInfoDto) {
    System.out.println(productInfoDto);
    return productInfoService.add(productInfoDto);
  }

  @PutMapping
  public ProductInfoDto update(@Valid @RequestBody ProductInfoDto productInfoDto) {
    return productInfoService.update(productInfoDto);
  }

}
