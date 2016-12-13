/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author C. Levallois adapted from
 * http://stackoverflow.com/questions/3656762/n-gram-generation-from-a-sentence
 */
public class NGramFinder {

    private Multiset<String> freqSetN = HashMultiset.create();
    private String[] words;
    private Set<String> setUniqueNGramsPerLine;
    private Multiset<String> setAllNGramsPerLine;
    private Multiset<String> multisetToReturn;
    private List<String> listStrings;
    private String stringOriginal;

    public static void main(String[] args) {
        new NGramFinder("  but    although the law is ").runIt(4, false);
    }

    public NGramFinder(List<String> listStrings) {
        this.listStrings = listStrings;
    }

    public NGramFinder(String string) {
        this.stringOriginal = string;
    }

    public Multiset<String> runIt(int maxgram, boolean binary) {
//        Clock extractingNGramsPerLine = new Clock("extracting ngrams");
        multisetToReturn = HashMultiset.create();
        if (stringOriginal != null) {
            listStrings = new ArrayList();
            listStrings.add(stringOriginal);
        }

        for (String string : listStrings) {

            setAllNGramsPerLine = HashMultiset.create();
            setAllNGramsPerLine.addAll(run(string, maxgram));

            //takes care of the binary counting.
            if (binary) {
                setUniqueNGramsPerLine = new HashSet();
                setUniqueNGramsPerLine.addAll(setAllNGramsPerLine);
                multisetToReturn.addAll(setUniqueNGramsPerLine);
            } else {
                multisetToReturn.addAll(setAllNGramsPerLine);
            }

        }
//        extractingNGramsPerLine.addText("number of unique terms after nGram detection: " + multisetToReturn.elementSet().size());
//        extractingNGramsPerLine.closeAndPrintClock();
        return multisetToReturn;

    }

    private Multiset<String> ngrams(int n, String str) {

        Multiset<String> setToReturn = HashMultiset.create();
        words = StringUtils.split(str);
        String concat;
        if (n == 1) {
            setToReturn.addAll(Arrays.asList(words));
        } else {
            for (int i = 0; i < words.length - n + 1; i++) {
                concat = concat(words, i, i + n);
                if (!concat.isEmpty()) {
                    setToReturn.add(concat);
                }
            }
        }

        return setToReturn;

    }

    public static Multiset<String> ngramsFinderJustAGivenLength(int n, String str) {
        String[] words;
        Multiset<String> setToReturn = HashMultiset.create();
        words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++) {
            setToReturn.add(concatStatic(words, i, i + n));
        }

        return setToReturn;

    }

    private static String concatStatic(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(i > start ? " " : "").append(words[i].trim());
        }
        return sb.toString().trim();
    }

    private String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(i > start ? " " : "").append(words[i].trim());
        }
        return sb.toString().trim();
    }

    private Multiset<String> run(String toBeParsed, int nGram) {
        freqSetN = HashMultiset.create();

        for (int n = 1; n <= nGram; n++) {
            freqSetN.addAll(ngrams(n, toBeParsed));
        }
        //System.out.println(freqList.get(i));
        return freqSetN;
    }
}
