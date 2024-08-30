package com.amc.services.amc;

import com.amc.enums.HttpStatusResponse;
import com.amc.httpResponse.HttpResponse;
import com.amc.model.myamc.CInfoBip;
import com.amc.model.myamc.COtp;
import com.amc.model.myamc.Keyclaokcredential;
import com.amc.repository.myamc.COtpRepository;
import com.amc.utils.DateUtils;
import com.amc.utils.OtpUtils;
import com.amc.utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map;
import java.nio.file.Paths;
// import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class COtpService {

    @Autowired
    private COtpRepository cOtpRepository;
    // private CInfoBipRepository cInfoBipRepository;
    private static final Logger logger = Logger.getLogger(COtpService.class.getName());

    private final String SENDER = "My Amcassur";

    @Transactional
    public HttpResponse sendOtp(String username, String prefix_country) {
        HttpResponse httpResponse = null;
        COtp cOtp = null;
        try {

            httpResponse = new HttpResponse();

            COtp cOtpCheck = this.cOtpRepository.findByUsername(username);

            if (cOtpCheck != null) {

                // supprime l'ancien otp pour créer le nouveau
                this.cOtpRepository.deleteAllByUsername(username);
                // creation d'un OTP
                cOtp = new COtp();

                String code_otp = Utils.getRandomStringNumeric(6);
                cOtp.setCode(code_otp);

                cOtp.setEnabled(true);
                cOtp.setUsername(username);

                long createdTimestamp = DateUtils.getTimeStampMinuit();
                cOtp.setDatecreate(createdTimestamp);

                COtp cOtpCreate = null;
                cOtpCreate = this.cOtpRepository.save(cOtp); // save in MongoDB

                if (cOtpCreate.getCode() != null) {
                    // on envoie l'opt via une requete http
                    CInfoBip infoBip = this.readJsonFileConfig();
                    System.out.println(" INFO BIP CREDENTIALS ");
                    System.out.println(infoBip);
                    if (infoBip == null) {
                        httpResponse = new HttpResponse();
                        httpResponse.setCode(404);
                        httpResponse.setStatus(HttpStatusResponse.EMPTY_SETTING_INFO_BIP.name());
                    } else {
                        boolean responseSendOtp = OtpUtils.sendOtpWithHttp(SENDER, prefix_country + cOtp.getUsername(),
                                "Votre code My Rawsur est : " + cOtp.getCode(), infoBip);

                        // OTP bien envoyé par SMS
                        if (responseSendOtp) {
                            httpResponse.setCode(201);
                            httpResponse.setMessage("L'OTP a été envoyé avec succès");
                            httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
                        } else {
                            httpResponse = new HttpResponse();
                            httpResponse.setCode(204); // si c'est 204, l'application frontend doit demander à
                                                       // l'utilisateur
                            // de retenter la demande de l'OTP
                        }
                    }
                } else {
                    httpResponse = new HttpResponse();
                    httpResponse.setCode(500);
                    httpResponse.setMessage("L'OTP n'a pas été persisté pendant le traitement");
                    httpResponse.setStatus(HttpStatusResponse.ERROR.name());
                }
            } else {
                // On cree un otp (Ici, on ne crase pas )

                cOtp = new COtp();
                String code_otp = Utils.getRandomStringNumeric(6);
                cOtp.setCode(code_otp);

                cOtp.setEnabled(true);
                cOtp.setUsername(username);

                long createdTimestamp = DateUtils.getTimeStampMinuit();
                cOtp.setDatecreate(createdTimestamp);

                COtp cOtpCreate = null;

                cOtpCreate = this.cOtpRepository.save(cOtp); // save in MongoDB
                // System.out.println(" NORMES ----------------- 0000000 ***************
                // 111111111111111".repeat(40));
                if (cOtpCreate.getCode() != null) {

                    CInfoBip infoBip = this.readJsonFileConfig();
                    if (infoBip == null) {
                        httpResponse = new HttpResponse();
                        httpResponse.setCode(404);
                        httpResponse.setStatus(HttpStatusResponse.EMPTY_SETTING_INFO_BIP.name());
                    } else {
                        // on envoie l'opt via une requete http
                        boolean responseSendOtp = OtpUtils.sendOtpWithHttp(SENDER, prefix_country + cOtp.getUsername(),
                                "Votre code My Rawsur est : " + cOtp.getCode(), infoBip);

                        // OTP bien envoyé par SMS
                        if (responseSendOtp) {
                            httpResponse.setCode(201);
                            httpResponse.setMessage("L'OTP a été envoyé avec succès");
                            httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
                        } else {
                            httpResponse = new HttpResponse();
                            httpResponse.setCode(204); // si c'est 204, l'application frontend doit demander à
                                                       // l'utilisateur
                            // de retenter la demande de l'OTP
                        }
                    }
                } else {
                    httpResponse = new HttpResponse();
                    httpResponse.setCode(500);
                    httpResponse.setMessage("L'OTP n'a pas été persisté pendant le traitement");
                    httpResponse.setStatus(HttpStatusResponse.OTP_NOT_SAVE.name());
                }
            }
        } catch (Exception e) {
            httpResponse = new HttpResponse();
            httpResponse.setCode(500);
            httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
            httpResponse.setStatus(HttpStatusResponse.ERROR.name());
            httpResponse.setData(e);
            return httpResponse;
        }
        return httpResponse;
    }

    @Transactional
    public HttpResponse verifiyOtp(String username, String code) {
        HttpResponse httpResponse = null;
        try {
            logger.info("BEGIN VERIFY OTP");
            httpResponse = new HttpResponse();
            COtp cOtpCheck = this.cOtpRepository.findByUsernameAndCode(username, code);
            System.out.println(cOtpCheck);
            if (cOtpCheck != null) {
                // on calcul la difference de temps entre le moment où le code otp a été
                // enregistré et le moment actuel ( = < 5 )
                long createdTimestamp = DateUtils.getTimeStampMinuit();
                long diffMin = DateUtils.diffMinutes(cOtpCheck.getDatecreate(), createdTimestamp);
                logger.info("DIFF DATE : " + diffMin);
                if (diffMin > 5) {
                    // supprime l'otp qui est espiré
                    logger.info("OTP EXPIRE");
                    this.cOtpRepository.deleteAllByUsername(username);
                    httpResponse.setCode(200);
                    httpResponse.setMessage("L'OTP espiré");
                    httpResponse.setStatus(HttpStatusResponse.OTP_ESPIRED.name());
                } else {
                    // supprime otp trouvé et validé
                    logger.info("OTP VALIDE");
                    this.cOtpRepository.deleteAllByUsername(username);

                    httpResponse.setCode(200);
                    httpResponse.setMessage("L'OTP trouvé avec succès");
                    httpResponse.setStatus(HttpStatusResponse.OTP_OK.name()); // Le client va vérifier cette valeur pour
                    // appeler l'API keyclaok pour modifier le
                    // mot de passe
                }
            } else {
                httpResponse.setCode(200);
                httpResponse.setMessage("L'OTP n'existe pas");
                httpResponse.setStatus(HttpStatusResponse.OTP_NOT_EXIST.name());
            }
        } catch (Exception e) {
            httpResponse = new HttpResponse();
            httpResponse.setCode(500);
            httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
            httpResponse.setStatus(HttpStatusResponse.ERROR.name());
            httpResponse.setData(e);
            return httpResponse;
        }
        return httpResponse;
    }

    public CInfoBip readJsonFileConfig() {
        CInfoBip cInfoBip = new CInfoBip();
        ObjectMapper objectMapper = new ObjectMapper();
        // File file = Paths.get("C:/config/config.dev.json").toFile();
        File file = Paths.get("C:/config/cInfoBip.json").toFile();
        try {

            cInfoBip = objectMapper.readValue(file, CInfoBip.class);

            return cInfoBip;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException IOException IOException " + e.getMessage());
        }
        return cInfoBip;
    }

}
