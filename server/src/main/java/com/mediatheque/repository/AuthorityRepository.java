package com.mediatheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mediatheque.model.Authority;
/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
  Authority findByName(String name);
}
