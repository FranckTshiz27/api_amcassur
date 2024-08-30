package com.amc.repository.myamc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.amc.model.myamc.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
       
    @Query(value = "SELECT * FROM roles_user_view  where realm_id = :realm_id and client_name = :client and id_user = :user_id", nativeQuery = true)
    List<UserRole> findUserRolesByRealmAndClientAndUser(@Param("realm_id") String realm_id,@Param("client") String client,@Param("user_id") String user_id);

}
