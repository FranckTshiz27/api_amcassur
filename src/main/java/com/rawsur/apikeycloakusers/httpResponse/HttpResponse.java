package com.rawsur.apikeycloakusers.httpResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResponse {

    private int code;
    private String status;
    private String message;
    private Object data;

}
