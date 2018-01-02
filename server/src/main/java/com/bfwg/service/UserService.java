package com.bfwg.service;

import java.util.List;
import com.bfwg.entities.User;
import com.bfwg.model.UserAccount;
import com.bfwg.model.UserRequest;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  List<User> findAll();

  User save(UserRequest user);

  User update(User user, UserAccount userAccount);

  void delete(Long id);
}
