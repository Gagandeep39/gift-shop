/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-29 10:54:45
 * @modify date 2021-01-29 10:54:45
 * @desc [description]
 */
package com.cg.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoogleSignInRequest {

  private String token;
  private String email;
  
}
