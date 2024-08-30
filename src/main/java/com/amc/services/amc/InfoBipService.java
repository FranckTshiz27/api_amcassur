package com.amc.services.amc;
// package com.rawsur.apikeycloakusers.services.amc;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.rawsur.apikeycloakusers.dto.myrawsur.InfoBipDTO;
// import com.rawsur.apikeycloakusers.enums.HttpStatusResponse;
// import com.rawsur.apikeycloakusers.httpResponse.HttpResponse;
// import com.rawsur.apikeycloakusers.model.myrawsur.CInfoBip;
// import com.rawsur.apikeycloakusers.repository.mongo.CInfoBipRepository;

// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import java.util.logging.Logger;
// import java.nio.file.Paths;
// import java.io.*;

// @Service
// public class InfoBipService {
//   // @Autowired
//   private CInfoBipRepository cInfoBipRepository;
  
//   private static final Logger logger = Logger.getLogger(InfoBipService.class.getName());

//   @Transactional
//   public HttpResponse create(InfoBipDTO  infoBipDTO) {

//     HttpResponse httpResponse = null;

//     try {

//       CInfoBip cInfoBip = this.readJsonFileConfig();


//       if (cInfoBip!=null) {
//         // on fait l'update
//          cInfoBip.setApikey(infoBipDTO.getApikey());
//          cInfoBip.setBaseurl(infoBipDTO.getBaseurl());
//          cInfoBip.setUri(infoBipDTO.getBaseurl());

//         CInfoBip save = this.cInfoBipRepository.save(cInfoBip);

//         if( save.getApikey().isEmpty()){
//           httpResponse = new HttpResponse(); // create instance HttpResponse
//           httpResponse.setCode(500);
//           httpResponse.setMessage("Les nouveaux paramètres info bip n'ont pas été enregistrés");
//           httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//         } else {
//           httpResponse = new HttpResponse(); // create instance HttpResponse
//           httpResponse.setCode(201);
//           httpResponse.setMessage("Les nouveaux paramètres info bip ont été mise à jour");
//           httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
//         }

//       }else {
//         // on crée le nouveau parametre

//         CInfoBip obj = new CInfoBip();
//         obj.setApikey(infoBipDTO.getApikey());
//         obj.setBaseurl(infoBipDTO.getBaseurl());
//         obj.setUri(infoBipDTO.getBaseurl());

//         CInfoBip save = this.cInfoBipRepository.save(obj);

//         if (save.getApikey().isEmpty()) {
//           httpResponse = new HttpResponse(); // create instance HttpResponse
//           httpResponse.setCode(500);
//           httpResponse.setMessage("Les nouveaux paramètres info bip n'ont pas été enregistrés");
//           httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//         } else {
//           httpResponse = new HttpResponse(); // create instance HttpResponse
//           httpResponse.setCode(201);
//           httpResponse.setMessage("Les nouveaux paramètres info bip ont été enregistrés");
//           httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());
//         }

//       }

//     } catch (Exception e) {

//       httpResponse = new HttpResponse();
//       httpResponse.setCode(500);
//       httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
//       httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//       httpResponse.setData(e);
//       logger.info(e.getMessage());
//       return httpResponse;

//     }

//     return httpResponse;
//   }


//   @Transactional
//   public HttpResponse getSetting() {

//     HttpResponse httpResponse = null;

//     try {

//       CInfoBip cInfoBip = this.readJsonFileConfig();

//       if ( cInfoBip!=null) {

//         httpResponse = new HttpResponse(); // create instance HttpResponse
//         httpResponse.setCode(200);
//         httpResponse.setData(cInfoBip);
//         httpResponse.setStatus(HttpStatusResponse.SUCCESS.name());

//       }else {

//         httpResponse = new HttpResponse(); // create instance HttpResponse
//         httpResponse.setCode(200);
//         httpResponse.setStatus(HttpStatusResponse.EMPTY.name());

//       }

//     } catch (Exception e) {

//       httpResponse = new HttpResponse();
//       httpResponse.setCode(500);
//       httpResponse.setMessage("Une erreur s'est produite pendant le traitement de la requête");
//       httpResponse.setStatus(HttpStatusResponse.ERROR.name());
//       httpResponse.setData(e);
//       return httpResponse;

//     }

//     return httpResponse;
//   }

//   public  CInfoBip readJsonFileConfig() {
//     CInfoBip cInfoBip = new CInfoBip();
//     ObjectMapper objectMapper = new ObjectMapper();
//     // File file = Paths.get("C:/config/config.dev.json").toFile();
//     File file = Paths.get("C:/config/cInfoBip.json").toFile();
//     try {
//       cInfoBip = objectMapper.readValue(file, CInfoBip.class);

//       return cInfoBip;
//     } catch (IOException e) {
//       e.printStackTrace();
//     }
//     return cInfoBip;
//   }


// }
