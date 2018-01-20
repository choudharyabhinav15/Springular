package com.mediatheque.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mediatheque.repository.AuthorityRepository;
import com.mediatheque.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mediatheque.model.Authority;

@Service
public class AuthorityServiceImpl implements AuthorityService {

  @Autowired
  private AuthorityRepository authorityRepository;

  @Override
  public List<Authority> findById(Long id) {
    // TODO Auto-generated method stub

    Authority auth = this.authorityRepository.findOne(id);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

  @Override
  public List<Authority> findByname(String name) {
    // TODO Auto-generated method stub
    Authority auth = this.authorityRepository.findByName(name);
    List<Authority> auths = new ArrayList<>();
    auths.add(auth);
    return auths;
  }

}
