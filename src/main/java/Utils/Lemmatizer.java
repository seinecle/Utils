/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LEVALLOIS
 */
public class Lemmatizer {

    private String[] noLemmaEN = new String[]{"accumbens", "addresses", "afterwards", "always", "approaches", "analytics", "analyses", "bayes", "biases", "businesses","charles", "classes", "cowles", "crises","discusses", "ethics", "focuses", "forbes", "fries", "goes", "humanities", "hypotheses", "james", "inches", "keynes", "koopmans", "lies", "losses", "physics", "politics", "processes", "ries", "series", "sometimes", "species", "status", "themselves", "united states", "neural processes", "humanities", "witnesses"};

    private String[] noLemmaFR = new String[]{"ects", "cours", "sens","puis","temps","parcours","près","auprès","outils","travers","pays","concours","êtes","divers","éthos","ethos","alors","corps","ouvrirons","univers"};
    
    // el/elle e/é i/î ie/ique al/ale

    private Set<String> noLemmaSet;
    private String lang;

    public Lemmatizer(String lang, boolean local) {
        switch (lang) {
            case "en":
                noLemmaSet = new HashSet(Arrays.asList(noLemmaEN));
                break;
            case "fr":
                noLemmaSet = new HashSet(Arrays.asList(noLemmaFR));
                break;
            default:
                noLemmaSet = new HashSet();
                break;
        }
        noLemmaSet.addAll(Stopwords.getStopWords(lang, local));
        this.lang = lang;
    }

    public String lemmatize(String term) {

        String currEntry = term.toLowerCase();
        String[] terms = currEntry.split(" ");
        String lastTerm = terms[terms.length - 1];
        if (noLemmaSet.contains(lastTerm)) {
            return currEntry.trim();
        }

        if ((currEntry.endsWith("s") | currEntry.endsWith("s'"))
                && !currEntry.endsWith("us")
                && !currEntry.endsWith("as")
                && !currEntry.endsWith("ss")
                && !currEntry.endsWith("ies")
                && !noLemmaSet.contains(currEntry)
                && !currEntry.endsWith("is")) {
            if (currEntry.endsWith("s")) {
                currEntry = currEntry.substring(0, currEntry.length() - 1);
            }
            if (currEntry.endsWith("s'")) {
                currEntry = currEntry.substring(0, currEntry.length() - 2);
            }

        } else if (currEntry.endsWith("'")) {
            currEntry = currEntry.substring(0, currEntry.length() - 1);
        }

        if (lang.equals("en")) {
            if (currEntry.endsWith("ies")) {
                currEntry = currEntry.substring(0, currEntry.length() - 3) + "y";
            } else if (currEntry.endsWith("'s")) {
                currEntry = currEntry.substring(0, currEntry.length() - 2);

            }
        }
        return currEntry.trim();

    }

    public String sentenceLemmatizer(String sentence) {

        String[] terms = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String term : terms) {
            sb.append(lemmatize(term));
            sb.append(" ");
        }
        return sb.toString().trim();
    }

}
