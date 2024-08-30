package com.amc.dto.myrawsur;

import java.io.BufferedOutputStream;
import java.io.*;

import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.amc.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileTools {

  public String saveFile(String base64String, String name) {
    log.info("Début SAVE FILE");
    // String path = "temp\\" + name;
    String path = "C:\\Servers\\api-myrawsur\\temp\\" + name;
    byte[] data = DatatypeConverter.parseBase64Binary(base64String);

    File file = new File(path);
    log.info("FIchié(s) chargé(s) en mémoire");
    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
      outputStream.write(data);
      log.info("FIchié(s) écrit dans le serveur");
      return path;
    } catch (IOException e) {
      log.error("Fichier introuvable ( path : " + path + ")");
      e.printStackTrace();
      throw new NotFoundException("Fichier introuvable");
    }
  }
}
