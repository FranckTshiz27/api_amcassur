package com.amc.services.amc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.model.myamc.User;
import com.amc.repository.myamc.UserRepository;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  private static final Logger logger = Logger.getLogger(UserService.class.getName());

  @Transactional
  public List<User> findByRealmId(String realm) {
    List<User> users = this.userRepository.findByRealmId(realm);
    users = users.stream()
        .filter(user -> user.getFirstname() != null || user.getLastname() != null)
        .collect(Collectors.toList());
    return users;
  }

  @Transactional
  public User findByRealmIdAndUsername(String realm, String username) {
    System.out.println(this.userRepository.findByRealmIdAndUsername(realm, username));
    return this.userRepository.findByRealmIdAndUsername(realm, username);
  }

   @Transactional
  public List<User> findManyByRealmIdAndUsername(String realm, String username) {
    return this.userRepository.findManyByRealmIdAndUsername(realm, username);
  }
}
