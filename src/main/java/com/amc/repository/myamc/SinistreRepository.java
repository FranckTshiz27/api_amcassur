package com.amc.repository.myamc;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amc.model.myamc.Sinistre;


public interface SinistreRepository extends JpaRepository<Sinistre, UUID> {

  @Query(value = "select * from sinistre s order by createdat desc", nativeQuery = true)
  public Page<Sinistre> findAllPaged(Pageable page);

  @Query(value = "select * from sinistre s order by createdat desc", nativeQuery = true)
  public List<Sinistre> findAll();

  @Query(value = "select * from sinistre s where id = :sinistreId", nativeQuery = true)
  public Sinistre findBySinistreId(UUID sinistreId);

  @Query(value = "update  sinistre set is_viewed=true where id = :sinistreId", nativeQuery = true)
  public Sinistre updateSinistre(UUID sinistreId);

  @Query(value = "select * from sinistre where immatriculation  like (%:immatriculation%) order by createdat desc ", nativeQuery = true)
  public Page<Sinistre> getSinistresByImmatriculation(Pageable page, String immatriculation);

  @Query(value = "select * from sinistre s where DATE(s.createdat) >= DATE(:dateDebut) and DATE(s.createdat)<=DATE(:dateFin) order by createdat desc", nativeQuery = true)
  public Page<Sinistre> getSinistresByDate(Pageable page, Date dateDebut, Date dateFin);

  @Query(value = "select * from sinistre s where DATE(s.createdat) >= DATE(:dateDebut) and DATE(s.createdat)<=DATE(:dateFin) and immatriculation  like (%:immatriculation%)  order by createdat desc", nativeQuery = true)
  public Page<Sinistre> getSinistresByDateAndImmatriculation(Pageable page, Date dateDebut, Date dateFin,
      String immatriculation);

  @Query(value = "select count(*) from sinistre s where is_viewed=false", nativeQuery = true)
  public int countNotViewedSinistres();
}
