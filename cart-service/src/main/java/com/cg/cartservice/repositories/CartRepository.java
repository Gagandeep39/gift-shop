/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:55:53
 * @modify date 2021-01-13 22:55:53
 * @desc [description]
 */
package com.cg.cartservice.repositories;

import java.util.Optional;

import com.cg.cartservice.entities.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	
	public Cart findByCartId(Long cartId);

	Optional<Cart> findByUserDetails_UserDetailsId(Long id);
  
}
