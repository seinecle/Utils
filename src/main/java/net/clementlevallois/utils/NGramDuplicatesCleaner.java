/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author C. Levallois
 */
public class NGramDuplicatesCleaner {

    Set<String> stopWords;
    HashMultiset multisetWords;

    Iterator<Multiset.Entry<String>> itFreqList;
    Set<String> wordsToBeRemoved;
    Multiset.Entry<String> entry;
    String currWord;
    Set<String> setCurrentSubNGrams;
    Iterator<String> setCurrentSubNGramsIterator;
    String string;
    String[] termsInBigram;

    public NGramDuplicatesCleaner(String lang, boolean local) {
        stopWords = (Set<String>)Stopwords.getStopWords(lang, local).getLeft();
    }

    public NGramDuplicatesCleaner(Set<String> stopwordsParameter) {
        stopWords = stopwordsParameter;

    }
    

    public Multiset<String> removeDuplicates(Multiset<String> setNGrams, int maxGrams) {
        multisetWords = HashMultiset.create();
        wordsToBeRemoved = new HashSet();

        for (int i = maxGrams - 1; i > 0; i--) {
            itFreqList = setNGrams.entrySet().iterator();
            while (itFreqList.hasNext()) {
                entry = itFreqList.next();
                currWord = entry.getElement().trim();

                if (StringUtils.countMatches(currWord, " ") == i) {
                    //special condition for i = 1 since this is a very simple case that does not need a heavy duty n-gram detection approach
                    if (i == 1) {

                        termsInBigram = currWord.split(" ");
                        String term1 = termsInBigram[0].trim();
                        String term2 = termsInBigram[1].trim();

                        if (stopWords.contains(term1) & stopWords.contains(term2)) {
                            wordsToBeRemoved.add(term1);
                            wordsToBeRemoved.add(term2);
                            wordsToBeRemoved.add(currWord);
                        }
                        if (!stopWords.contains(term1) & !stopWords.contains(term2)) {
                            if (setNGrams.count(term1) < entry.getCount() * 1.5) {
                                wordsToBeRemoved.add(term1.trim());
                            }
                            if (setNGrams.count(term2) < entry.getCount() * 1.5) {
                                wordsToBeRemoved.add(term2.trim());
                            }
                        }

                    } else {
                        setCurrentSubNGrams = NGramFinder.ngramsFinderJustAGivenLength(i, currWord).elementSet();
                        setCurrentSubNGramsIterator = setCurrentSubNGrams.iterator();
                        while (setCurrentSubNGramsIterator.hasNext()) {
                            string = setCurrentSubNGramsIterator.next().trim();

                            if (!setNGrams.contains(string)) {
                                continue;
                            } else if (setNGrams.count(string) < entry.getCount() * 1.5) {
                                wordsToBeRemoved.add(string);
                            }
                        }
                    }
                }
            }
        }

        itFreqList = setNGrams.entrySet().iterator();
        while (itFreqList.hasNext()) {
            boolean toRemain;
            entry = itFreqList.next();
            currWord = entry.getElement();


            toRemain = wordsToBeRemoved.add(currWord);

            if (toRemain & !stopWords.contains(currWord)) {
                multisetWords.add(entry.getElement().trim(), entry.getCount());
            }

        }

        return multisetWords;

    }
}
