package com.cg.productservice.util;

import com.cg.productservice.dto.ProductInfoDto;
import com.cg.productservice.dto.StockDto;
import com.cg.productservice.entities.ProductInfo;

public class ProductMapper {
	
	public static ProductInfo DtoToEntity(ProductInfoDto dto) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(dto.getProductId());
		productInfo.setProductName(dto.getProductName());
		productInfo.setProductPrice(dto.getProductPrice());
		productInfo.setProductStock(dto.getProductStock());
		productInfo.setProductDescription(dto.getProductDescription());
		productInfo.setProductIcon(dto.getProductIcon());
		productInfo.setProductStatus(dto.getProductStatus());
//		productInfo.setProductCategory(dto.getCategoryId());
		return productInfo;
	}
	
	public static ProductInfo stockDtoToEntity(StockDto stockDto) {
		ProductInfo productInfo = new ProductInfo();
		productInfo.setProductId(stockDto.getProductId());
		productInfo.setProductStock(stockDto.getQuantity());
//		productInfo.setProductName(productInfo.getProductName());
//		productInfo.setProductDescription(productInfo.getProductDescription());
//		productInfo.setProductIcon(productInfo.getProductIcon());
//		productInfo.setProductPrice(productInfo.getProductPrice());
//		productInfo.setProductStatus(productInfo.getProductStatus());
//		productInfo.setProductCategory(productInfo.getProductCategory());
		return productInfo;
	}

}
