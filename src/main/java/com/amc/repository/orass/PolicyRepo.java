package com.amc.repository.orass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.amc.model.orass.Policy;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;

public interface PolicyRepo extends PagingAndSortingRepository<Policy, String> {

        final String FIND_POLICY_BY_POLICY_NUMBER_AND_CODE_INT_AND_NUM_AVE = "select * from V_MOBILE_POLICY where NUMEPOLI = :numpoli and CODEINTE = :codeint and NUMEAVEN =:numeave ORDER BY DATEEMI DESC";

        @Query(value = "SELECT /*+ RULE */  * FROM V_MOBILE_POLICY WHERE cle = :key ", nativeQuery = true)
        @Transactional(readOnly = true)
        Policy findByCleContainingIgnoreCase(String key);

        @Transactional(readOnly = true)
        @Query(value = "SELECT * FROM V_MOBILE_POLICY  WHERE NUMEARCA = :polNumber ", nativeQuery = true)
        Optional<Policy> findByPolNumber(String polNumber);

        @Query(value = "SELECT * FROM V_MOBILE_POLICY", nativeQuery = true)
        @QueryHints(value = {
                        @QueryHint(name = HINT_CACHEABLE, value = "true"),
                        @QueryHint(name = READ_ONLY, value = "true")
        })
        @Transactional(readOnly = true)
        List<Policy> findAll();

        @Query(value = "SELECT * FROM V_MOBILE_POLICY WHERE PHONE LIKE %:phone%", nativeQuery = true)
        List<Policy> findAllPhone(String phone);

        @Query(value = "SELECT * FROM V_MOBILE_POLICY WHERE PHONE LIKE %:phone%", nativeQuery = true)
        List<Policy> findOnePhone(String phone);

        @Query(value = "SELECT * FROM V_MOBILE_POLICY", nativeQuery = true)
        List<Policy> getAll();

}
