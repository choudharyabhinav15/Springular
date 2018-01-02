package com.bfwg.service;

import java.util.List;
import com.bfwg.entities.Authority;

public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByname(String name);

}
