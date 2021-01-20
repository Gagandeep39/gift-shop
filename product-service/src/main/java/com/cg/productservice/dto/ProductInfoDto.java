/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 20:15:21
 * @modify date 2021-01-13 20:15:21
 * @desc [description]
 */
package com.cg.productservice.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.cg.productservice.enums.ProductStatus;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductInfoDto {

  // Use this for performing validations
	
	@NotNull
	@Min(100000)
	@Max(999999)
	private Long productId;
	
	@NotNull
    private String productName;
    
    @NotNull
    @DecimalMax(value = "9999.999")
    private BigDecimal productPrice;
    
    @NotNull
    @DecimalMax(value = "999.999")
    private Integer productStock;
    
    @NotNull
    private String productDescription;
    
    @NotNull
    private String productIcon;
    
    @NotNull
    @Pattern(regexp = "^(ENABLED|DISABLED)$", message = "Status must be enabled or disabled")
    private ProductStatus productStatus;
    
    @NotNull
    @Min(100000)
	@Max(999999)
    private Long categoryId;
	
}
