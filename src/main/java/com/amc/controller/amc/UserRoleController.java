package com.amc.controller.amc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amc.model.myamc.UserRole;
import com.amc.routes.Routes;
import com.amc.services.amc.UserRoleService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = Routes.BASE_URL_KEYCLAOK_ROLE)
public class UserRoleController {

  @Autowired
  private UserRoleService userRoleService;

  @GetMapping(path = Routes.GET_ROLES_BY_REALM_AND_CLIENT_AND_USER)
  public ResponseEntity<List<UserRole>> getUsersByRealm(@PathVariable("realm") String realm,@PathVariable("client") String client,@PathVariable("user") String user) {
    List<UserRole> userRoles = this.userRoleService.findByRealmIdAndClientIdAndUserId(realm, client, user);
    return ResponseEntity.ok().body(userRoles);
  }

}
