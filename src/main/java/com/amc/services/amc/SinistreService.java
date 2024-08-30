package com.amc.services.amc;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.dto.CreateSinitreDTO;
import com.amc.dto.PeriodePaginationAndImmatriculationDTO;
import com.amc.dto.PeriodePaginationDTO;
import com.amc.enums.DocType;
import com.amc.exceptions.NotFoundException;
import com.amc.model.myamc.Account;
import com.amc.model.myamc.File;
import com.amc.model.myamc.Parametres;
import com.amc.model.myamc.Sinistre;
import com.amc.repository.myamc.AccountRepository;
import com.amc.repository.myamc.FileRepository;
import com.amc.repository.myamc.ParametresRepository;
import com.amc.repository.myamc.SinistreRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class SinistreService {
  @Autowired
  private SinistreRepository sinistreRepository;
 
  @Autowired
  private FileRepository fileRepository;
 
  @Autowired
  private MailService mailService;
  // @Autowired
  // private KeycloakTools kcTool;
  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private ParametresRepository parametresRepository;

  @Transactional
  public Boolean create(CreateSinitreDTO sinistreDTO) {
    log.info("Début CREATION SINISTRE");
    Sinistre sinistre = new Sinistre();
    sinistre.setImmatriculation(sinistreDTO.getImmatriculation());
    sinistre.setLatitude(sinistreDTO.getLatitude());
    sinistre.setLongitude(sinistreDTO.getLongitude());
    sinistre.setPhoneNumber(sinistreDTO.getPhoneNumber());
    sinistre.setDecSinistre(sinistreDTO.getDecSinistre());
    sinistre = this.sinistreRepository.save(sinistre);
    Account user = this.accountRepository.findByUsername(sinistreDTO.getPhoneNumber());
    sinistre.setUser(user);
    // this.createTreatementStatus(sinistre);
    List<File> files = new ArrayList<>();
    List<String> imgs = sinistreDTO.getImg_sinstres();
    for (String img : imgs) {
      File file = new File();
      file.setSinistre(sinistre);
      file.setImg(img);
      file.setType(DocType.IMG);
      file = this.fileRepository.save(file);
      files.add(file);
    }
    Boolean result = (files.size() == imgs.size()) ? true : false;
    // Boolean result = true;
    // Sinistre sinistre = new Sinistre();
    // sinistre.setImmatriculation(sinistreDTO.getImmatriculation());
    // sinistre.setLatitude(sinistreDTO.getLatitude());
    // sinistre.setLongitude(sinistreDTO.getLongitude());
    // sinistre.setPhoneNumber(sinistreDTO.getPhoneNumber());
    // sinistre.setDecSinistre(sinistreDTO.getDecSinistre());
    // sinistre = this.sinistreRepository.save(sinistre);
    // Users user = this.usersRepo.findByKeycloakId(sinistreDTO.getPhoneNumber());
    // sinistre.setUser(user);
    // this.createTreatementStatus(sinistre);
    // List<File> files = new ArrayList<>();
    // List<String> imgs = sinistreDTO.getImg_sinstres();
    // for (String img : imgs) {
    // File file = new File();
    // file.setSinistre(sinistre);
    // file.setImg(img);
    // file.setType(DocType.IMG);
    // file = this.fileRepository.save(file);
    // files.add(file);
    // }
    // Boolean result = (files.size() == imgs.size()) ? true : false;
    // Boolean result = true;
    log.info("RESULT : " + result);
    if (result) {
      this.senMail(sinistreDTO);
    }
    return result;
  }

  public List<Sinistre> findAll() {
    return sinistreRepository.findAll();
  }

  @Transactional
  private void senMail(CreateSinitreDTO sinistreDTO) {
    log.info("Début SEND MAIL");
    try {
      List<String> imgs = sinistreDTO.getImg_sinstres();
      String immatriculation = sinistreDTO.getImmatriculation();
      String num_police = sinistreDTO.getNum_police();
      double latitude = sinistreDTO.getLatitude();
      double longitude = sinistreDTO.getLongitude();

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
      LocalDateTime dateTime = LocalDateTime.now();
      String formattedDateTime = dateTime.format(formatter);
      // String[] mails = { "vkamdem@rawsur.com", "stagiaire-it@rawsur.com",
      // "klukanga@rawsur.com",
      // "amakandelo@rawsur.com", "mben@rawsur.com" };
      List<Parametres> parametres = this.parametresRepository.findAll();
      Parametres parametre = parametres.get(0);
      String[] mails = { parametre.getGroupe_mail() };

      String link_map = "https://www.google.com/maps/search/?api=1&query=" +
          latitude + "%2C" + longitude;
      String subject = "DECLARATION SINISTRE Automobile";
      //
      String degat = imgs.isEmpty() ? "" : "\n\nVous trouvez ci-joint quelques images des dégâts.";
      String descriptionDto = sinistreDTO.getDecSinistre();
      String descriptionMail = "";

      if ((descriptionDto == null || descriptionDto == "") && (latitude == 0)) {
        descriptionMail = "";
      } else if ((descriptionDto == null || descriptionDto == "") && (latitude != 0)) {
        descriptionMail = "Ci-dessous ma localisation :\n\nClicquer ici : " + link_map;
      } else if ((descriptionDto != null || descriptionDto != "") && (latitude == 0)) {
        descriptionMail = "Ci-dessous une brève description de circonstance :\n\n" + descriptionDto;
      } else if ((descriptionDto != null || descriptionDto != "") && (latitude != 0)) {
        descriptionMail = "Ci-dessous une brève description de circonstance ansi que ma localisation :\n\n"
            + descriptionDto + "\n\nCliquez ici " + link_map;
      }

      String body = String.format(
          "Bonjour.\n\nMon véhicule immatriculé %s vient d'être impliqué dans un accident de circulation.\n\nMon numero de téléphone est le %s.%s\n\n%s",
          immatriculation, sinistreDTO.getPhoneNumber(), degat, descriptionMail);
      log.info("Nombre d'image : " + imgs.size());

      if (imgs.isEmpty()) {
        log.info("EMAIL SANS ATTACHE");
        this.mailService.sendMail(mails, subject, body);
      } else {
        log.info("EMAIL AVEC ATTACHE");
        this.mailService.sendMailWithAttachment(mails, subject, body, imgs);
      }
    } catch (Exception e) {
      System.out.println("====================================ERREUR=======================================");
      System.out.println(e.getMessage());
    }
  }

  @Transactional
  public Page<Sinistre> findByAllPaged(int pageSize, int pageNumber) {

    Pageable page = getPage(pageSize, pageNumber);
    return this.sinistreRepository.findAllPaged(page);
  }
 public List<Sinistre> findByAll() {
    return this.sinistreRepository.findAll();
  }
 
  public Sinistre updateSinistreStatus(String id) {
    UUID uuid = UUID.fromString(id);
    Sinistre updatedSinistre = null;
    Sinistre sinistre = this.sinistreRepository.findBySinistreId(uuid);
    if (sinistre == null) {
      throw new NotFoundException(" Ce snistre n'existe pas dans notre base de données");
    } else {
      updatedSinistre = this.sinistreRepository.updateSinistre(uuid);
    }
    return updatedSinistre;
  }

  public Page<Sinistre> findAllByImmatriculation(int pageSize, int pageNumber, String immatriculation) {
    Pageable page = getPage(pageSize, pageNumber);
    return this.sinistreRepository.getSinistresByImmatriculation(page, immatriculation);
  }

  public Page<Sinistre> findAllByDate(PeriodePaginationDTO paginationDTO) {
    int pageSize = paginationDTO.getPageSize();
    int pageNumber = paginationDTO.getPageIndex();
    Pageable page = getPage(pageSize, pageNumber);
    Date dateDebut = paginationDTO.getDateDebut();
    Date dateFin = paginationDTO.getDateFin();
    return this.sinistreRepository.getSinistresByDate(page, dateDebut, dateFin);
  }

  private Pageable getPage(int pageSize, int pageNumber) {
    if (pageNumber > 0) {
      pageNumber = pageNumber - 1;
    }
    return PageRequest.of(pageNumber, pageSize);
  }

  public Page<Sinistre> findAllByDateAndImmatriculation(PeriodePaginationAndImmatriculationDTO paginationDTO) {
    int pageSize = paginationDTO.getPageSize();
    int pageNumber = paginationDTO.getPageIndex();
    String immatriculation = paginationDTO.getImmatriculation();
    Pageable page = getPage(pageSize, pageNumber);
    Date dateDebut = paginationDTO.getDateDebut();
    Date dateFin = paginationDTO.getDateFin();
    return this.sinistreRepository.getSinistresByDateAndImmatriculation(page, dateDebut, dateFin, immatriculation);
  }

  public int countNotViewedSinistres() {
    return this.sinistreRepository.countNotViewedSinistres();
  }
}
