/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:34:17
 * @modify date 2021-01-13 22:34:17
 * @desc [description]
 */
package com.cg.productservice.services;

import java.util.List;

import com.cg.productservice.entities.ProductCategory;

public interface ProductCategoryService {

  List<ProductCategory> fetchAllCategories();

  ProductCategory createCategory(ProductCategory productCategory);

  ProductCategory findById(Long id);

}
