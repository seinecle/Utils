/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

/**
 *
 * @author LEVALLOIS from: https://stackoverflow.com/a/10661387/798502
 */
public class URLExpander {

    public static String expand(String shortenedUrl) {
        if (shortenedUrl == null || shortenedUrl.isEmpty()) {
            return "";
        }
        try {
            URL url = new URL(shortenedUrl);
            // open connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);

            // stop following browser redirect
            httpURLConnection.setInstanceFollowRedirects(false);

            // extract location header containing the actual destination URL
            String expandedURL = httpURLConnection.getHeaderField("Location");
            httpURLConnection.disconnect();
            return expandedURL;
        } catch (MalformedURLException ex) {
            System.out.println("url badly formatted");
            return shortenedUrl;
        } catch (IOException ex) {
            System.out.println("url seems valid but can't be reached");
            return shortenedUrl;
        }
    }
}
