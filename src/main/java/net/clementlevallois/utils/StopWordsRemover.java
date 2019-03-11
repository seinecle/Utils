/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author C. Levallois
 */
public final class StopWordsRemover {

    private String entryWord;
    private boolean multipleWord;
    private boolean useFieldSpecificStopWords;
    private int minWordLength;

    private String lang;
    private boolean local;

    private final int maxAcceptedGarbage = 3;
    private int nbStopWords = 5000;
    private int nbStopWordsShort = 500;

    Set<String> setStopWordsFieldSpecificOrShort = new HashSet();
    Set<String> setStopWordsShort = new HashSet();
    Set<String> setStopwordsFieldSpecific = new HashSet();
    Set<String> setStopWords = new HashSet();
    Set<String> setKeepWords = new HashSet();
    List<String> listGeneralStopwordsLarge = new ArrayList();
    List<String> listGeneralStopwordsShort = new ArrayList();
    List<String> stopwordsForOneLanguage = new ArrayList();
    Pair stopWordsLongAndShort;

    public StopWordsRemover(int minWordLength, String lang, boolean local) {
        stopWordsLongAndShort = Stopwords.getStopWords(lang, local);
        stopwordsForOneLanguage = new ArrayList((Set<String>) stopWordsLongAndShort.getLeft());

        this.useFieldSpecificStopWords = false;
        this.local = local;
        this.lang = lang;
        this.minWordLength = minWordLength;
        nbStopWordsShort = Math.min(nbStopWordsShort, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        nbStopWords = Math.min(5000, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(StopWordsRemover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public StopWordsRemover(int minWordLength, String lang, boolean local, Set<String> fieldSpecificStopwords) {
        stopWordsLongAndShort = Stopwords.getStopWords(lang, local);
        stopwordsForOneLanguage = new ArrayList((Set<String>) stopWordsLongAndShort.getLeft());

        this.useFieldSpecificStopWords = true;
        this.local = local;
        this.lang = lang;
        this.minWordLength = minWordLength;
        nbStopWordsShort = Math.min(nbStopWordsShort, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        nbStopWords = Math.min(5000, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        setStopwordsFieldSpecific = new HashSet(fieldSpecificStopwords);
        setStopWordsFieldSpecificOrShort.addAll(fieldSpecificStopwords);
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(StopWordsRemover.class.getName()).log(Level.SEVERE, null, ex);
        }
        setStopWords.addAll(fieldSpecificStopwords);

    }

    public StopWordsRemover(boolean useFieldSpecificStopWords, int minWordLength, List<String> stopwords) {
        stopWordsLongAndShort = Stopwords.getStopWords(lang, local);
        stopwordsForOneLanguage = new ArrayList((Set<String>) stopWordsLongAndShort.getLeft());

        this.useFieldSpecificStopWords = useFieldSpecificStopWords;
        this.minWordLength = minWordLength;
        nbStopWordsShort = Math.min(nbStopWordsShort, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        nbStopWords = Math.min(5000, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        setStopWords.addAll(setStopwordsFieldSpecific);
        setStopWordsFieldSpecificOrShort.addAll(setStopwordsFieldSpecific);

        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(StopWordsRemover.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addStopWordsToKeep(Set<String> stopWordsToKeep) {
        setKeepWords.addAll(stopWordsToKeep);
    }

    private void init() throws IOException {
        setKeepWords = new HashSet();
        setStopWordsShort = new HashSet();

        listGeneralStopwordsLarge = stopwordsForOneLanguage.subList(0, nbStopWords);
        listGeneralStopwordsShort = stopwordsForOneLanguage.subList(0, nbStopWordsShort);

        setStopWords.addAll(listGeneralStopwordsLarge);
        if (((Set<String>) stopWordsLongAndShort.getRight()).isEmpty()) {
            setStopWordsShort.addAll(listGeneralStopwordsShort);
        }else{
            setStopWordsShort.addAll((Set<String>) stopWordsLongAndShort.getRight());            
        }
        setStopWordsFieldSpecificOrShort.addAll(setStopWordsShort);
    }

    public boolean shouldItBeRemoved(String term) {

        boolean write = true;
        entryWord = term;

        if (useFieldSpecificStopWords) {
            multipleWord = entryWord.contains(" ");

            if (multipleWord) {
                String[] wordsNGrams = entryWord.split(" ");
                int wordsNGramsLength = wordsNGrams.length;

                for (String wordsNGram : wordsNGrams) {
                    if (wordsNGram.length() < minWordLength) {
                        write = false;
                        break;
                    }
                }

                if (wordsNGramsLength == 2
                        && ((setStopWordsFieldSpecificOrShort.contains(wordsNGrams[0].toLowerCase().trim())
                        || setStopWordsFieldSpecificOrShort.contains(wordsNGrams[1].toLowerCase().trim())))) {
                    write = false;

                }

                if (wordsNGramsLength > 2) {
                    int scoreGarbage = 0;

                    for (int i = 0; i < wordsNGramsLength; i++) {

                        String currentTerm = wordsNGrams[i].toLowerCase().trim();

                        if ((i == 0 | i == (wordsNGramsLength - 1)) && setStopWordsFieldSpecificOrShort.contains(currentTerm)) {
                            scoreGarbage = maxAcceptedGarbage + 1;
                            continue;
                        }

                        if ((i == 0 | i == (wordsNGramsLength - 1)) && setStopWordsShort.contains(currentTerm)) {
                            write = false;
                            continue;
                        }

                        if (setStopWordsShort.contains(currentTerm)) {
                            scoreGarbage = scoreGarbage + 3;
                            continue;
                        }

                        if (setStopwordsFieldSpecific.contains(currentTerm)) {
                            scoreGarbage = scoreGarbage + 2;
                            continue;
                        }

                    }

                    if (setStopWords.contains(entryWord)) {
                        scoreGarbage = maxAcceptedGarbage + 1;
                    }

                    if (scoreGarbage > maxAcceptedGarbage) {

                        write = false;
                    }
                }

            } else if (setStopWords.contains(entryWord) & !setKeepWords.contains(entryWord)) {
                write = false;
            }

            if (setKeepWords.contains(entryWord)) {
                write = true;
            }

        } else {
            String[] wordsNGrams = entryWord.split(" ");
            for (String wordsNGram : wordsNGrams) {
                if (setStopWords.contains(wordsNGram)) {
                    write = false;
                }
            }
        } //end of else block       

        if (write) {
            return false;
        } else {
            return true;
        }

    }
}
