package com.vymorix.plugins.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestUtil {

    public int sendPostRequest(URL url, byte[] payload) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toString(payload.length));
            connection.setDoOutput(true);
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(payload);
            }

            return connection.getResponseCode();
        } finally {
            connection.disconnect();
        }
    }
}
