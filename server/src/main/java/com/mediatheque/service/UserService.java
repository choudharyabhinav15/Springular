package com.mediatheque.service;

import java.util.List;
import com.mediatheque.model.User;
import com.mediatheque.model.UserRequest;
import com.mediatheque.model.UserUpdate;

/**
 * Created by fan.jin on 2016-10-15.
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  List<User> findAll();

  User update(UserUpdate user);

  User save(UserRequest user);
}
