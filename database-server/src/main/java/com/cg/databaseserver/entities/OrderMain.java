/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 17:47:37
 * @modify date 2021-01-13 17:47:37
 * @desc [description]
 */
package com.cg.databaseserver.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.cg.databaseserver.enums.OrderStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderMain {

    @Id
    @SequenceGenerator(name = "order_id_sequence", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(generator = "order_id_sequence", strategy = GenerationType.SEQUENCE)
    private Long orderId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderMain")
    private Set<ProductInOrder> products = new HashSet<>();

    private String buyerEmail;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private BigDecimal orderAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}