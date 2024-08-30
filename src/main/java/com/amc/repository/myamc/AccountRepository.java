package com.amc.repository.myamc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amc.model.myamc.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

        final String findByUserNameQuery = "SELECT * FROM public.user_entity WHERE username=:username";
        final String findByEmailQuery = "SELECT * FROM public.user_entity WHERE email:email";

        @Query(value = findByUserNameQuery, nativeQuery = true)
        public List<Account> findByUserName(String username);

        @Query(value = findByEmailQuery, nativeQuery = true)
        public List<Account> findByEmail(String email);

        public Account findByUsernameOrEmail(String username, String email);
        public Account findByUsername(String username);

        public void deleteByUsername(String username);
}
