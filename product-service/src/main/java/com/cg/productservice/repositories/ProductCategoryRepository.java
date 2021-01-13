/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 20:08:30
 * @modify date 2021-01-13 20:08:30
 * @desc [description]
 */
package com.cg.productservice.repositories;

import com.cg.productservice.entities.ProductCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
  
}
