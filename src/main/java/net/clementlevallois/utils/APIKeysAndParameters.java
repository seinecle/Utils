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

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException{
        System.out.println(APIKeysAndParameters.getMendeleyAPIkey());
    }
    
    /**
     *
     * @return
     * @throws IOException
     */
    public static String getMendeleyAPIkey() throws IOException {
        return getKey("mendeley");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getElasticMailAPIkey() throws IOException {
        return getKey("elasticMail");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getScopusRingAPIkey() throws IOException {
        return getKey("scopus");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getTwitterConsumerKey() throws IOException {
        return getKey("twitterConsumer");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getTwitterSecretKey() throws IOException {
        return getKey("twitterSecret");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getTwitterToken() throws IOException {
        return getKey("twitterToken");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getTwitterTokenSecret() throws IOException {
        return getKey("twitterTokenSecret");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getStopwordsLocalPath() throws IOException {
        return getKey("stopWordsLocalPath");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getStopwordsRemotePath() throws IOException {
        return getKey("stopWordsRemotePath");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getStopwordsScientificLocalPath() throws IOException {
        return getKey("stopWordsScientificLocalPath");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getStopwordsScientificRemotePath() throws IOException {
        return getKey("stopWordsScientificRemotePath");
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String getStopwordsTwitterLocalPath() throws IOException {
        return getKey("stopWordsTwitterLocalPath");
    }

    /**
     *
     * @return
     * @throws IOException
     */
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
