package com.rawsur.apikeycloakusers.controller.keyclaok;

import com.rawsur.apikeycloakusers.model.keyclaok.User;
import com.rawsur.apikeycloakusers.routes.Routes;
import com.rawsur.apikeycloakusers.services.keycloak.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Routes.BASE_URL_MY_AMC)
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = Routes.GET_USER_BY_REALM)
  public ResponseEntity<List<User>> getUsersByRealm(@PathVariable("realm") String realm) {
    List<User> users = this.userService.findByRealmId("rawsur");
    return ResponseEntity.ok().body(users);
  }

  @GetMapping(path = Routes.GET_USER_BY_REALM_AND_USERNAME)
  public ResponseEntity<User> getUserByRealmAndUsername(@PathVariable("realm") String realm,
      @PathVariable("username") String username) {
        System.out.println(" AAAAAAAAAAAAADDDDDDDDDDDDDDDDDDD MMMMMMMMMMMMMMM ".repeat(50));
    User user = this.userService.findByRealmIdAndUsername(realm, username);
    System.out.println(" 0000000000000000000 MMMMMMMMMMMMMMM ".repeat(50));
    return ResponseEntity.ok().body(user);
  }

  @GetMapping(path = Routes.GET_USERS_BY_REALM_AND_USERNAME)
  public ResponseEntity<List<User>> getUserSByRealmAndUsername(
      @PathVariable("realm") String realm,
      @PathVariable("username") String username) {
    List<User> users = this.userService.findManyByRealmIdAndUsername(realm, username);
    return ResponseEntity.ok().body(users);
  }

}
