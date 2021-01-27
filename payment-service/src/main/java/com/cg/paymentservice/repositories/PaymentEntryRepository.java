/**
 * @author Gagandeep Singh
 * @email singh.gagandeep3911@gmail.com
 * @create date 2021-01-27 23:16:58
 * @modify date 2021-01-27 23:16:58
 * @desc [description]
 */
package com.cg.paymentservice.repositories;

import com.cg.paymentservice.entities.PaymentEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentEntryRepository extends JpaRepository<PaymentEntry, Long> {
  
}
