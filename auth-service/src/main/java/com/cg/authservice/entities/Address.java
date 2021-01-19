/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 16:55:54
 * @modify date 2021-01-13 16:55:54
 * @desc Stores user
 */
package com.cg.authservice.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {
  @Id
  @JsonIgnore
  @SequenceGenerator(name = "address_id_sequence", initialValue = 100000, allocationSize = 1)
  @GeneratedValue(generator = "address_id_sequence", strategy = GenerationType.SEQUENCE)
  private Long addressId;
  @Column(length = 50)
  @Size(min = 3, max = 50)
  @NotBlank
  private String city;
  @Column(length = 50)
  @Size(min = 3, max = 50)
  @NotBlank
  private String state;
  @Column(length = 50)
  @Size(min = 3, max = 50)
  @NotBlank
  private String area;
  @Size(min = 6, max = 6, message = "Must be 6 digits only")
  @Column(length = 6)
  @NotBlank
  private String pincode;

}
