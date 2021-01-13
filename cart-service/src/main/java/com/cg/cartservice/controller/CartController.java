/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 23:00:29
 * @modify date 2021-01-13 23:00:29
 * @desc [description]
 */
package com.cg.cartservice.controller;

import com.cg.cartservice.dto.CartDto;
import com.cg.cartservice.dto.ItemDto;
import com.cg.cartservice.entities.Cart;
import com.cg.cartservice.entities.OrderMain;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

  @GetMapping("/{id}")
  public Cart fetchCartById(@PathVariable Long id) {
    return null;
  }

  @PostMapping("/checkout/{id}")
  public OrderMain checkOut(@PathVariable Long id) {
    return null;
  }

  @PutMapping("/{id}")
  public Cart addToCart(ItemDto itemDto, @PathVariable Long id) {
    return null;
  }

  @DeleteMapping("/{id}")
  public Cart deleteFromCart(ItemDto itemDto, @PathVariable Long id) {
    return null;
  }

  // Merge local cart with Server cart
  @PostMapping("/merge")
  public Cart mergeCart(CartDto cartDto) {
    return null;
  }

}
