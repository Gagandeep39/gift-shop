/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:40:25
 * @modify date 2021-01-13 22:40:25
 * @desc [description]
 */
package com.cg.productservice.services.implementation;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;
import com.cg.productservice.exception.ProductIdNotFoundException;
import com.cg.productservice.repositories.ProductInfoRepository;
import com.cg.productservice.services.ProductInfoService;
import com.cg.productservice.util.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

	@Autowired
	private ProductInfoRepository productInfoRepository;

	@Override
	public List<ProductInfo> fetchAll() {
		return this.productInfoRepository.findAll();
	}

	@Override
	public List<ProductInfo> fetchByCategory(String category) {
		List<ProductInfo> allProducts = this.productInfoRepository.findAll();
		return allProducts.stream().filter(c -> c.getProductCategory().getCategoryName().equals(category)).collect(Collectors.toList());
	}

	@Override
	public ProductInfo fetchById(Long id) {
		return this.productInfoRepository.findById(id).orElseThrow(() ->  new ProductIdNotFoundException("Product id", "Not Found"));
	}
	
	@Override
	public boolean removeProduct(Long productId) {
		this.productInfoRepository.deleteById(productId);
		return true;
	}
	
	@Override
	public ProductInfoDto update(ProductInfoDto productInfoDto) {
		productInfoRepository.save(ProductMapper.DtoToEntity(productInfoDto));
		return productInfoDto;
	}

	
//   ****** NOT WORKING Category null*********
	@Override
	public Map<String, String> add(ProductInfoDto productInfoDto) {
		System.out.println(productInfoDto);
		return Collections.singletonMap("productId",
				productInfoRepository.save(ProductMapper.DtoToEntity(productInfoDto)).getProductId().toString());
	}

	
	//            ****************     NOT WORKING         ********************
	@Override
	public ProductInfo updateStock(StockDto stockDto) {
		productInfoRepository.save(ProductMapper.stockDtoToEntity(stockDto));
		return productInfoRepository.findById(stockDto.getProductId()).orElseThrow(() ->  new RuntimeException("error"));
		 
	}

	@Override
	public ProductInfo increaseStock(StockDto stockDto) {
		// Auto-generated method stub
		return null;
	}

	@Override
	public ProductInfo reduceStock(StockDto stockDto) {
		//  Auto-generated method stub
		return null;
	}

}
