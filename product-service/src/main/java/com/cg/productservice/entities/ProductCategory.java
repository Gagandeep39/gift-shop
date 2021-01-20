/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 19:18:42
 * @modify date 2021-01-13 19:18:42
 * @desc [description]
 */
package com.cg.productservice.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategory  {

    @Id
    @SequenceGenerator(name = "category_id_sequence", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(generator = "category_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long categoryId;

    @NotNull
    @NotBlank
    private String categoryName;
    @NotNull
    @NotBlank
    private String categoryDescription;
    @NotNull
    @NotBlank
    private String categoryImageUrl;

}
