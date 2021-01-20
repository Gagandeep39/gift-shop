package com.cg.cartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.cg.cartservice.entities.ProductInfo;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
	
	Optional<ProductInfo> findByproductId(long productId);

}
