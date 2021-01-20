/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-20 19:48:12
 * @modify date 2021-01-20 19:48:12
 * @desc [description]
 */
package com.cg.orderservice.repositories;

import java.util.List;

import com.cg.orderservice.entities.DeliveryHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {

  List<DeliveryHistory> findByOrderId(Long orderId);

}
