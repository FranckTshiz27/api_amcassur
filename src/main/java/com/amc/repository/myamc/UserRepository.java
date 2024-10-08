package com.amc.repository.myamc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amc.model.myamc.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
        public List<User> findByRealmId(String realm);

        public User findByRealmIdAndUsername(String realm, String username);

        @Query(value = "select * from user_entity where username like :username% and realm_id =:realm",nativeQuery = true)
        public List<User> findManyByRealmIdAndUsername(String realm, String username);

}
