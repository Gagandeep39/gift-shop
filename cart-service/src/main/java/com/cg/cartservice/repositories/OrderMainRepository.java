/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-13 22:56:55
 * @modify date 2021-01-13 22:56:55
 * @desc [description]
 */
package com.cg.cartservice.repositories;

import com.cg.cartservice.entities.OrderMain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, Long> {
  
}
