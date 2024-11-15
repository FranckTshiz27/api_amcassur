package com.amc.controller.amc;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amc.dto.CreateSinitreDTO;
import com.amc.dto.PeriodePaginationAndImmatriculationDTO;
import com.amc.dto.PeriodePaginationDTO;
import com.amc.model.myamc.Sinistre;
import com.amc.routes.Routes;
import com.amc.services.amc.SinistreService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(path = Routes.BASE_URL_MY_AMC+Routes.SINISTRE_URI)
@RestController
@Slf4j
public class SinistreController {
  @Autowired
  private SinistreService sinistreService;

  @GetMapping(path = "v1/find-all")
  public ResponseEntity<List<Sinistre>> findAll() {
    List<Sinistre> sinistres = this.sinistreService.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(sinistres);
  }

  @GetMapping(path = "v1/find-all/paged/{pageSize}/{pageNumber}")
  public ResponseEntity<Page<Sinistre>> findAllPaged(@PathVariable("pageSize") int pageSize,
      @PathVariable("pageNumber") int pageNumber) {
    Page<Sinistre> sinistres = this.sinistreService.findByAllPaged(pageSize, pageNumber);
    return ResponseEntity.status(HttpStatus.OK).body(sinistres);
  } 

  @GetMapping(path = "v1/find-by-immatriculation/paged/{pageSize}/{pageNumber}/{immatriculation}")
  public ResponseEntity<Page<Sinistre>> findByImmatriculationPaged(@PathVariable("pageSize") int pageSize,
      @PathVariable("pageNumber") int pageNumber, @PathVariable("immatriculation") String immatriculation) {
    Page<Sinistre> sinistres = this.sinistreService.findAllByImmatriculation(pageSize, pageNumber, immatriculation);
    return ResponseEntity.status(HttpStatus.OK).body(sinistres);
  }

  @PostMapping(path = "v1/find-by-date/paged")
  public ResponseEntity<Page<Sinistre>> findByDatePaged(@RequestBody @Valid PeriodePaginationDTO periodePaginationDTO) {
    Page<Sinistre> sinistres = this.sinistreService.findAllByDate(periodePaginationDTO);
    return ResponseEntity.status(HttpStatus.OK).body(sinistres);
  }

  @PostMapping(path = "v1/find-by-date-and-immatriculation/paged")
  public ResponseEntity<Page<Sinistre>> findByDateAndImmatriculationPaged(
      @RequestBody @Valid PeriodePaginationAndImmatriculationDTO periodePaginationDTO) {
    Page<Sinistre> sinistres = this.sinistreService.findAllByDateAndImmatriculation(periodePaginationDTO);
    return ResponseEntity.status(HttpStatus.OK).body(sinistres);
  }

  @PostMapping(path = "v1/create")
  public ResponseEntity<Boolean> create(@RequestBody @Valid CreateSinitreDTO sinistreDTO) {
    log.info("DTO : ");
    Boolean resulte = sinistreService.create(sinistreDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(resulte);
  }

  @PatchMapping(path = "v1/editStatus/{id}")
  public ResponseEntity<Sinistre> create(@PathVariable("id") String id) {
    Sinistre sinistre = this.sinistreService.updateSinistreStatus(id);
    return ResponseEntity.status(HttpStatus.CREATED).body(sinistre);
  }

  @GetMapping(path = "v1/count")
  public ResponseEntity<Integer> countNotViewedSinistres() {
    int count = this.sinistreService.countNotViewedSinistres();
    return ResponseEntity.status(HttpStatus.OK).body(count);
  }

  @GetMapping(path = "v1/assistance")
  public ResponseEntity<String> getAssistanceNumber() {
    return ResponseEntity.status(HttpStatus.OK).body("+242067387178");
  }

}
