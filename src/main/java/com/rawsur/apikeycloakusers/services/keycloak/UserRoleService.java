package com.rawsur.apikeycloakusers.services.keycloak;

import com.rawsur.apikeycloakusers.dto.keyclaok.UserDTO;
import com.rawsur.apikeycloakusers.model.keyclaok.User;
import com.rawsur.apikeycloakusers.model.keyclaok.UserRole;
import com.rawsur.apikeycloakusers.repository.keycloak.UserRepository;
import com.rawsur.apikeycloakusers.repository.keycloak.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class UserRoleService {
  @Autowired
  private UserRoleRepository userRoleRepository;

  @Autowired
  UserRepository userRepository;

  @Transactional
  public List<UserRole> findByRealmIdAndClientIdAndUserId(String realm, String clientId, String userId) {
    List<UserRole> userRoles = new ArrayList<>();
    User user = userRepository.findByRealmIdAndUsername(realm,userId);
    
    if (user!=null) {
      userRoles = this.userRoleRepository.findUserRolesByRealmAndClientAndUser(realm, clientId, user.getId().toString());
    }
    

    return userRoles;
  }

}
