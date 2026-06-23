package pe.edu.upc.tpbackinkametrics.util;

import org.springframework.http.HttpHeaders;

public class RapidApiHeaders {

    public static HttpHeaders build(String key, String host) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", key);
        headers.set("X-RapidAPI-Host", host);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}
