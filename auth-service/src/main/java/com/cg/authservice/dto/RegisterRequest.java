/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-19 23:15:58
 * @modify date 2021-01-19 23:15:58
 * @desc [description]
 */
package com.cg.authservice.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cg.authservice.entities.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

  @NotBlank
  @Size(min = 5, max = 20)
  private String username;
  @NotBlank
  @Size(min = 5, max = 20)
  private String password;
  @NotBlank
  @Email
  private String emailId;
  @NotBlank
  @Size(min = 10, max = 10, message = "Length of phone number must be 10")
  private String phoneNo;
  // @NotNull
  // @Past
  // private LocalDate dob;
  @Valid
  private Address address;
  @NotBlank
  @Size(min = 5, max = 30)
  private String firstName;
  @NotBlank
  @Size(min = 5, max = 30)
  private String lastName;

}