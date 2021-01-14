/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-14 01:06:58
 * @modify date 2021-01-14 01:06:58
 * @desc [description]
 */
package com.cg.orderservice.repositories;

import com.cg.orderservice.entities.OrderMain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMainRepository extends JpaRepository<OrderMain, Long> {
  
}