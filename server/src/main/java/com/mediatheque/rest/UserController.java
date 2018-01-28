package com.mediatheque.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mediatheque.model.UserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.mediatheque.exception.ResourceConflictException;
import com.mediatheque.model.User;
import com.mediatheque.model.UserRequest;
import com.mediatheque.service.UserService;
/**
 * Created by Ghiles FEGHOUL on 28/12/2017
 */

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UserService userService;

  @PreAuthorize("hasRole('ADMIN')")
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
    existUser = this.userService.findByUsername(userRequest.getEmail());
      if (existUser != null) {
          throw new ResourceConflictException(userRequest.getId(), "Email already exists");
      }
    User user = this.userService.save(userRequest);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/api/user/{userId}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @RequestMapping(method = PUT, value = "/user/my-account")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<?> updateUser(@RequestBody UserUpdate userUpdate) {
    if(userUpdate.getId()!=null) {
      User existUser = this.userService.findById(userUpdate.getId());
      if (existUser == null){
        existUser = this.userService.findByUsername(userUpdate.getUsername());
		if (existUser == null)
			throw new ResourceConflictException(userUpdate.getId(), "User not found !");
	  }
	}
      User user = this.userService.update(userUpdate);
    return new ResponseEntity<User>(user, HttpStatus.OK);
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
