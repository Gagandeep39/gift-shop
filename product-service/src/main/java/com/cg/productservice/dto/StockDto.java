/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 20:13:57
 * @modify date 2021-01-13 20:13:57
 * @desc [description]
 */
package com.cg.productservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class StockDto {

  private Long productId;
  private BigDecimal quantity;
  
}
