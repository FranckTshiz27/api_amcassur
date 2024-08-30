package com.amc.services.amc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.model.myamc.User;
import com.amc.model.myamc.UserRole;
import com.amc.repository.myamc.UserRepository;
import com.amc.repository.myamc.UserRoleRepository;

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
