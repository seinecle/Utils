/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

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
    private boolean useScientificStopWords;
    private int minWordLength;

    private String lang;
    private boolean local;

    private final int maxAcceptedGarbage = 3;
    private int nbStopWords = 5000;
    private int nbStopWordsShort = 200;

    Set<String> setStopWordsScientificOrShort;
    Set<String> setStopWordsShort;
    Set<String> setStopwordsScientific;
    Set<String> setStopWords;
    Set<String> setKeepWords;
    List<String> listGeneralStopwordsLarge;
    List<String> listGeneralStopwordsShort;
    List<String> stopwordsForOneLanguage;

    public StopWordsRemover(boolean useScientificStopWords, int minWordLength, String lang, boolean local) {
        this.useScientificStopWords = useScientificStopWords;
        this.local = local;
        this.lang = lang;
        this.minWordLength = minWordLength;
        this.stopwordsForOneLanguage = new ArrayList(Stopwords.getStopWords(lang, local));
        nbStopWordsShort = Math.min(200, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
        nbStopWords = Math.min(5000, Math.max(0, (stopwordsForOneLanguage.size() - 1)));
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
        setStopWords = new HashSet();
        setStopWordsScientificOrShort = new HashSet();
        setStopWordsShort = new HashSet();

        listGeneralStopwordsLarge = stopwordsForOneLanguage.subList(0, nbStopWords);
        listGeneralStopwordsShort = stopwordsForOneLanguage.subList(0, nbStopWordsShort);

        setStopWords.addAll(listGeneralStopwordsLarge);
        setStopWordsScientificOrShort.addAll(setStopWordsShort);
        setStopWordsShort.addAll(listGeneralStopwordsShort);

        if (useScientificStopWords) {
            setStopwordsScientific = Stopwords.getScientificStopwords(local, lang);
            setStopWords.addAll(setStopwordsScientific);
            setStopWordsScientificOrShort.addAll(setStopwordsScientific);
        }

    }

    public boolean shouldItBeRemoved(String term) {

        boolean write = true;
        entryWord = term;

        if (useScientificStopWords) {
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
                        && ((setStopWordsScientificOrShort.contains(wordsNGrams[0].toLowerCase().trim())
                        || setStopWordsScientificOrShort.contains(wordsNGrams[1].toLowerCase().trim())))) {
                    write = false;

                }

                if (wordsNGramsLength > 2) {
                    int scoreGarbage = 0;

                    for (int i = 0; i < wordsNGramsLength; i++) {

                        String currentTerm = wordsNGrams[i].toLowerCase().trim();

                        if ((i == 0 | i == (wordsNGramsLength - 1)) && setStopWordsScientificOrShort.contains(currentTerm)) {
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

                        if (setStopwordsScientific.contains(currentTerm)) {
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
