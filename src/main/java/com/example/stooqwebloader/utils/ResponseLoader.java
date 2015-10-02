package com.example.stooqwebloader.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Michał
 */
@Stateless
public class ResponseLoader {

    public String loadResponse(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet getRequest = new HttpGet(url);
        HttpResponse response;
        String content = null;
        try {
            response = httpClient.execute(getRequest);
            HttpEntity entity = response.getEntity();
            content = (EntityUtils.toString(entity));
        } catch (IOException | ParseException ex) {
            Logger.getLogger(ResponseLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return content;

    }

}
