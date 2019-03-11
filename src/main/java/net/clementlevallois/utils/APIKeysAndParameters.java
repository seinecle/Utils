/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author C. Levallois
 */
public class APIKeysAndParameters {

    static InputStream inputStream;

    public static void main(String args[]) throws IOException{
        System.out.println(APIKeysAndParameters.getMendeleyAPIkey());
    }
    
    public static String getMendeleyAPIkey() throws IOException {
        return getKey("mendeley");
    }

    public static String getElasticMailAPIkey() throws IOException {
        return getKey("elasticMail");
    }

    public static String getScopusRingAPIkey() throws IOException {
        return getKey("scopus");
    }

    public static String getTwitterConsumerKey() throws IOException {
        return getKey("twitterConsumer");
    }

    public static String getTwitterSecretKey() throws IOException {
        return getKey("twitterSecret");
    }

    public static String getTwitterToken() throws IOException {
        return getKey("twitterToken");
    }

    public static String getTwitterTokenSecret() throws IOException {
        return getKey("twitterTokenSecret");
    }

    public static String getStopwordsLocalPath() throws IOException {
        return getKey("stopWordsLocalPath");
    }

    public static String getStopwordsRemotePath() throws IOException {
        return getKey("stopWordsRemotePath");
    }

    public static String getStopwordsScientificLocalPath() throws IOException {
        return getKey("stopWordsScientificLocalPath");
    }

    public static String getStopwordsScientificRemotePath() throws IOException {
        return getKey("stopWordsScientificRemotePath");
    }

    public static String getStopwordsTwitterLocalPath() throws IOException {
        return getKey("stopWordsTwitterLocalPath");
    }

    public static String getStopwordsTwitterRemotePath() throws IOException {
        return getKey("stopWordsTwitterRemotePath");
    }


    private static String getKey(String keyName) throws IOException {

        Properties prop = new Properties();
        String propFileName = "config.properties";

        inputStream = APIKeysAndParameters.class.getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        // get the property value and print it out
        return prop.getProperty(keyName);

    }
}
