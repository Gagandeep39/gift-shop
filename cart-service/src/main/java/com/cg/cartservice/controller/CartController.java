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
import com.cg.cartservice.services.CartService;
import com.cg.cartservice.services.CartServiceImpl;
import com.cg.cartservice.services.OrderMainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService; 
	
	@Autowired
	OrderMainService order;
	
	Cart cart;
	OrderMain Order;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

  @GetMapping("/{id}")//working
  public Cart fetchCartById(@PathVariable Long id) {
    return cart=this.cartService.fetchCartById(id);
  }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////

  @PostMapping("/checkout/{id}")//working
  public OrderMain checkOut(@PathVariable Long id) {
    return Order=this.order.checkOut(id);
  }
/////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  @PutMapping("/{id}")//working
  public Cart addToCart(@RequestBody ItemDto itemDto, @PathVariable Long id) {
	  
	  
	  return cart=  this.cartService.addToCart(itemDto, id);
	  
	  
   
  }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////

  @DeleteMapping("/delete/{productId}/{id}")
  public Cart deleteFromCart(@PathVariable Long productId, @PathVariable Long id) {
	  
	  System.out.println("Product Id: "+productId+"User ID: "+id);
	  
	  return cart= this.cartService.deleteFromCart(productId, id);
    //
  }
  
/////////////////////////////////////////////////////////////////////////////////////////////////////////

  // Merge local cart with Server cart
  @PostMapping("/merge/{id}")
  public Cart mergeCart(@RequestBody CartDto cartDto,@PathVariable Long id) {
	  
	   cart=  this.cartService.mergeCart(cartDto,id);
	   System.out.println("In cart controller before return : cart :"+cart);
	   return cart;
   
  }

}
