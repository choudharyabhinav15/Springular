package com.mediatheque.service;

import java.util.List;
import com.mediatheque.model.Authority;

public interface AuthorityService {
  List<Authority> findById(Long id);

  List<Authority> findByname(String name);

}
