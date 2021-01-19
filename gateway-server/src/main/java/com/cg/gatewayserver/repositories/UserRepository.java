package com.cg.gatewayserver.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.gatewayserver.entities.User;

@Repository
public interface UserRepository extends JpaRepository<com.cg.gatewayserver.entities.User, Long> {

  Optional<User> findByUsername(String username);

}
