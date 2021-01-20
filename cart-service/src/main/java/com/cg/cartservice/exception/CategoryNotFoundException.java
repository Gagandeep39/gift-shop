/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-20 10:24:21
 * @modify date 2021-01-20 10:24:21
 * @desc [description]
 */
package com.cg.cartservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;
	private String errorName;
  private String errorDescription;

}
