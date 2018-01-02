package com.bfwg.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bfwg.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.bfwg.exception.ResourceConflictException;
import com.bfwg.entities.User;
import com.bfwg.model.UserRequest;
import com.bfwg.service.UserService;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by fan.jin on 2016-10-15.
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UserService userService;


  @RequestMapping(method = GET, value = "/user/{userId}")
  public User loadById(@PathVariable Long userId) {
    return this.userService.findById(userId);
  }

  @RequestMapping(method = GET, value = "/user/all")
  public List<User> loadAll() {
    return this.userService.findAll();
  }

  @RequestMapping(method = GET, value = "/user/reset-credentials")
  public ResponseEntity<Map> resetCredentials() {
    this.userService.resetCredentials();
    Map<String, String> result = new HashMap<>();
    result.put("result", "success");
    return ResponseEntity.accepted().body(result);
  }


  @RequestMapping(method = POST, value = "/signup")
  public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest,
      UriComponentsBuilder ucBuilder) {

    User existUser = this.userService.findByUsername(userRequest.getUsername());
    if (existUser != null) {
      throw new ResourceConflictException(userRequest.getId(), "Username already exists");
    }
    User user = this.userService.save(userRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @RequestMapping(method = PUT, value = "/user/my-account")
  public ResponseEntity<?> updateUser(@RequestBody UserAccount userAccount) {
      User existUser = this.userService.findByUsername(userAccount.getUsername());
      if (existUser == null) {
          throw new ResourceConflictException(userAccount.getId(), "Username doesn't exist in our system, verify that you enter the right information");
      }
    User user = this.userService.update(existUser, userAccount);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }

  @RequestMapping(method = DELETE, value = "/api/user/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable Long userId){
      return new ResponseEntity<String>("User has been successfully removed",HttpStatus.OK);
  }

  /*
   * We are not using userService.findByUsername here(we could), so it is good that we are making
   * sure that the user has role "ROLE_USER" to access this endpoint.
   */
  @RequestMapping("/whoami")
  @PreAuthorize("hasRole('USER')")
  public User user() {
    return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
