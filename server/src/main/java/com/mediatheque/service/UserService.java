package com.mediatheque.service;

import java.util.List;
import com.mediatheque.model.User;
import com.mediatheque.model.UserRequest;
import com.mediatheque.model.UserUpdate;

/**
 * Created by Ghiles FEGHOUL on 28/12/2017.
 */
public interface UserService {
  void resetCredentials();

  User findById(Long id);

  User findByUsername(String username);

  User findByEmail(String email);

  List<User> findAll();

  User update(UserUpdate user);

  User save(UserRequest user);

  User update(User user);
}
