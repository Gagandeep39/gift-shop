package com.cg.cartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cartservice.entities.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
	
	public ProductInfo findByproductId(long productId);

}
