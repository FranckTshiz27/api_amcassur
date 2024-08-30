package com.amc.utils;

// import com.rawsur.apikeycloakusers.repository.mongo.CInfoBipRepository;
import okhttp3.*;
import org.springframework.http.HttpStatus;

import com.amc.model.myamc.CInfoBip;

import java.util.logging.Logger;

// import static javax.xml.transform.OutputKeys.MEDIA_TYPE;

public class OtpUtils {

    private static final Logger logger = Logger.getLogger(OtpUtils.class.getName());



    public static boolean sendOtpWithHttp(String sender, String recipient, String message_text, CInfoBip infoBip) {

        try {

            OkHttpClient client = new OkHttpClient().newBuilder().build();

            String bodyJson = String.format(
                    "{\"messages\":[{\"from\":\"%s\",\"destinations\":[{\"to\":\"%s\"}],\"text\":\"%s\"}]}",
                    sender,
                    recipient,
                    message_text);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(bodyJson, mediaType);

            Request request = prepareHttpRequest(body,infoBip );
            System.out.println(request);
            Response response = client.newCall(request).execute();

            if ( response.code() == HttpStatus.OK.value() ) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.info("Erreur : " + e.getMessage());
            return false;
        }

    }

    private static Request prepareHttpRequest(RequestBody body, CInfoBip infoBip) {

        // url(infoBip.getBaseurl() + "/sms/2/text/advanced")

        Request request = new Request.Builder()
                .url(infoBip.getBaseurl() + infoBip.getUri())
                .addHeader("Authorization", "App " + infoBip.getApikey())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .post(body)
                .build();

        return request;
    }

}
