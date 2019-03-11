/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 Copyright 2008-2013 Clement Levallois
 Authors : Clement Levallois <clementlevallois@gmail.com>
 Website : http://www.clementlevallois.net


 DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.

 Copyright 2013 Clement Levallois. All rights reserved.

 The contents of this file are subject to the terms of either the GNU
 General Public License Version 3 only ("GPL") or the Common
 Development and Distribution License("CDDL") (collectively, the
 "License"). You may not use this file except in compliance with the
 License. You can obtain a copy of the License at
 http://gephi.org/about/legal/license-notice/
 or /cddl-1.0.txt and /gpl-3.0.txt. See the License for the
 specific language governing permissions and limitations under the
 License.  When distributing the software, include this License Header
 Notice in each file and include the License files at
 /cddl-1.0.txt and /gpl-3.0.txt. If applicable, add the following below the
 License Header, with the fields enclosed by brackets [] replaced by
 your own identifying information:
 "Portions Copyrighted [year] [name of copyright owner]"

 If you wish your version of this file to be governed by only the CDDL
 or only the GPL Version 3, indicate your decision by adding
 "[Contributor] elects to include this software in this distribution
 under the [CDDL or GPL Version 3] license." If you do not indicate a
 single choice of license, a recipient has the option to distribute
 your version of this file under either the CDDL, the GPL Version 3 or
 to extend the choice of license to its licensees as provided above.
 However, if you add GPL Version 3 code and therefore, elected the GPL
 Version 3 license, then the option applies only if the new code is
 made subject to such option by the copyright holder.

 Contributor(s): Clement Levallois

 */
public class Stopwords {

    private static final String[] twitterStopWords = {"rt", "w/"};
    private static final String[] commonStopWords = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "25", "30", "40", "50", "100", "1000"};

    private static Map<String, Pair> cache = new HashMap();
    private static Map<String,Set<String>> cacheTwitter = new HashMap();

    public static Pair getStopWords(String lang, boolean local) {

        if (cache.containsKey(lang)) {
            return cache.get(lang);
        }
        Set<String> stopWords = new HashSet();
        Set<String> shortStopWords = new HashSet();
        try {

            BufferedReader br = null;
            String path;
            if (local) {
                path = APIKeysAndParameters.getStopwordsLocalPath();
            } else {
                path = APIKeysAndParameters.getStopwordsRemotePath();
            }
            File file = new File(path);
            System.out.println("path: " + path);

            for (File f : file.listFiles()) {
                if (f.getName().contains(lang + ".txt")) {
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            stopWords.add(line);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (f.getName().contains(lang + "_short.txt")) {
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                        String line;
                        while ((line = br.readLine()) != null) {
                            shortStopWords.add(line);
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            br.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            Pair pair = new Pair(stopWords, shortStopWords);

            cache.put(lang, pair);

            return pair;
        } catch (IOException ex) {
            Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
            return new Pair();
        }
    }

    public static Set<String> getScientificStopwords(boolean local, String lang) throws FileNotFoundException, IOException {
        Set<String> words = new HashSet();
        BufferedReader br;
        String path;
        if (local) {
            path = APIKeysAndParameters.getStopwordsScientificLocalPath() + "\\" + lang + "\\";
        } else {
            path = APIKeysAndParameters.getStopwordsScientificRemotePath() + "/" + lang + "/";
        }

        File file = new File(path + "scientificstopwords.txt");

        br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            words.add(line);
        }
        return words;
    }

    public static Set<String> getTwitterStopwords(boolean local, String lang) throws FileNotFoundException, IOException {
        if (cacheTwitter.containsKey(lang)) {
            return cacheTwitter.get(lang);
        }

        Set<String> words = new HashSet();
        BufferedReader br;
        String path;
        if (local) {
            path = APIKeysAndParameters.getStopwordsTwitterLocalPath();
        } else {
            path = APIKeysAndParameters.getStopwordsTwitterRemotePath();
        }

        File file = new File(path);
        System.out.println("path: " + path);

        for (File f : file.listFiles()) {
            if (f.getName().contains(lang + ".txt") | f.getName().equals("commonxx.txt")) {
                try {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        words.add(line);
                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Stopwords.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        cacheTwitter.put(lang, words);
        return words;
    }

}
