package com.amc.repository.orass;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.amc.model.orass.Risk;
import java.util.List;
import java.util.Optional;

public interface RiskRepo extends PagingAndSortingRepository<Risk, String> {
    final String FIND_RISK_BY_POLICY_NUMBER_AND_CODE_INT_AND_NUM_AVE = "select * from V_MOBILE_RISK where NUMEPOLI = :numpoli and CODEINTE = :codeint and NUMEAVEN =:numeave ORDER BY DATEEMI DESC";

    @Query(value = "SELECT /*+ RULE */  * FROM V_MOBILE_RISK WHERE cle = :key ", nativeQuery = true)
    List<Risk> findByCle(String key);

    @Query(value = "SELECT /*+ RULE */  * FROM V_MOBILE_RISK WHERE 'Numéro ARCA' = :numearca ", nativeQuery = true)
    List<Risk> findByPolNumber(String numearca);

    @Query(value = "SELECT /*+ RULE */  * FROM V_MOBILE_RISK WHERE 'Numéro Immat' = :immat ", nativeQuery = true)
    Optional<Risk> findByImmatriculation(String immat);

    @Query(value = "SELECT /*+ RULE */ * FROM V_MOBILE_RISK WHERE 'Numéro ARCA' = :pol AND IMMATRICULATION = :immat ", nativeQuery = true)
    Optional<Risk> findByPolAndRisk(String pol, String immat);

    List<Risk> findAll();

    @Query(value = "SELECT * FROM V_MOBILE_RISK WHERE PHONE LIKE %:phone%", nativeQuery = true)
    List<Risk> findAllByPhone(@Param("phone") String phone);

    @Query(value = FIND_RISK_BY_POLICY_NUMBER_AND_CODE_INT_AND_NUM_AVE, nativeQuery = true)
       List<Risk> findByNumPolicyAndCodeIntAndNumeave(@Param("numpoli") String numpoli,@Param("codeint") String codeint,@Param("numeave") int numeave);

    List<Risk> findAllByCle(String cle);

    @Query(value = "SELECT * FROM V_MOBILE_RISK WHERE NUMEPOLI = :numpolice AND CODEINTE = :interm AND NUMEAVEN = :avenant", nativeQuery = true)
    List<Risk> findAllByNumpoliAndCodeintAndNumave(@Param("numpolice") String numpolice, @Param("interm") String interm, @Param("avenant") int avenant);


    @Query(value = "SELECT * FROM V_MOBILE_RISK WHERE NUMIMMAT =:matricule AND PHONE LIKE %:phone%", nativeQuery = true)
    List<Risk> findAllByImmatriculation(String matricule, String phone);
}
