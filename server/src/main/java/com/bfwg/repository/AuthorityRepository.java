package com.bfwg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bfwg.entities.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Authority findByName(String name);
}
