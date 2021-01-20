package com.cg.cartservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.cartservice.entities.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>  {
	
	//public UserDetails findByUserDetailsId(long userDetailsId);

}
