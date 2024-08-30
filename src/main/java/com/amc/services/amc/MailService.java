package com.amc.services.amc;

import java.io.File;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amc.dto.myrawsur.FileTools;
import com.amc.model.myamc.Parametres;
import com.amc.repository.myamc.ParametresRepository;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailService {
  @Autowired
  private ParametresRepository parametresRepository;
  @Autowired
  private JavaMailSender emailSender;
  @Autowired
  private JavaMailSenderImpl mailSenderImpl;
  @Autowired
  private FileTools fileTools;
  // @Value("${spring.mail.username}")
  // private String mail;

  @Transactional
  public void sendMail(String[] mailsTo, String subject, String text) {
    log.info("Début SEND MAIL SANS ATTACHES");
    List<Parametres> parametres = this.parametresRepository.findAll();
    Parametres parametre = parametres.get(0);
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(parametre.getMail_sender());
    message.setTo(mailsTo);
    message.setText(text);
    message.setSubject(subject);

    this.mailSenderImpl.setPassword(parametre.getPassword_mail_sender());
    this.emailSender.send(message);

    log.info("FIN SEND MAIL SANS ATTACHES");
  }

  public void sendMailWithAttachment(
      String[] mailsTo, String subject, String text, List<String> imgs) {
    log.info("Début SEND MAIL AVEC ATTACHES");
    MimeMessage message = emailSender.createMimeMessage();

    // Properties props = new Properties();
    // props.put("spring.mail.password", "DigiRawsur2022");

    try {
      List<Parametres> parametres = this.parametresRepository.findAll();
      Parametres parametre = parametres.get(0);
      this.mailSenderImpl.setPassword(parametre.getPassword_mail_sender());
      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(parametre.getMail_sender());
      helper.setTo(mailsTo);
      helper.setSubject(subject);
      helper.setText(text);

      imgs.forEach(img -> {
        String[] strings = img.split(",");
        String name = strings[0];
        String base64String = strings[1];
        String path = this.fileTools.saveFile(base64String, name);
        FileSystemResource file = new FileSystemResource(new File(path));
        try {
          helper.addAttachment(name, file);
        } catch (MessagingException e) {
          log.error("ERREUR CREATION FICHIER: " + e.getMessage());
          e.printStackTrace();
        }
      });
    } catch (MessagingException e) {
      log.error("ERREUR MAIL : " + e.getMessage());
      e.printStackTrace();
    }

    emailSender.send(message);
    log.info("FIN SEND AVEC ATTACHES");
  }
}
