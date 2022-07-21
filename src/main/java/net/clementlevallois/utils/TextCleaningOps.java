/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.text.Normalizer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCleaningOps {

    public static String clean(String status) {
        if (status == null) {
            return "";
        }
        status = status.replace("...", " ");
        status = status.replace(",", " ");
        status = status.replace("..", " ");
//            System.out.println(status);
        status = status.replaceAll("http[^ ]*", " ");
        status = status.replaceAll("http.*[\r|\n]*", " ");
        status = status.replaceAll(" +", " ");

        return status;
    }

    public static String removePunctuationSigns(String string) {
        if (string == null) {
            return "";
        }
        String punctuation = "!?.@'’`+<>\"«»:-“”—+,|$;_/~&()[]{}#=*";

        char[] chars = punctuation.toCharArray();
        for (char currChar : chars) {
            string = string.replace(String.valueOf(currChar), " ");
        }
        return string.trim();
    }

    public static String detachCamelCaseWordsAndPutInLowerCase(String string) {
        if (string.contains("LeMonde") || string.contains("PhD")) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        int countChar = 0;
        String currentChar;
        String previousChar;
        for (char c : string.toCharArray()) {
            //if the character is uppercase, and is not preceded by an uppercase, then introduce a space before it
            if (Character.isUpperCase(c) && (countChar > 0 && !Character.isUpperCase(string.charAt(countChar - 1)))) {
                currentChar = String.valueOf(c);
                previousChar = String.valueOf(string.charAt(countChar - 1));
            }
            sb.append(c);
            countChar++;
        }
        return sb.toString();
    }

    public static Multiset<String> removeSmallWords(Multiset<String> terms, int lessOrEqualToNumber) {

        Iterator<Map.Entry<String, Integer>> it = terms.getEntrySet().iterator();
        while (it.hasNext()) {
            String string = it.next().getKey();
            if (string.length() < lessOrEqualToNumber) {
                it.remove();
            }
        }
        return terms;
    }

    public static boolean shouldItBeRemoved(String string, int lessOrEqualToNumber) {

        if (string.trim().length() < lessOrEqualToNumber | string.matches(".*\\d.*")) {
            return true;
        } else {
            return false;
        }

    }

    public static String removeUrls(String status) {
//            System.out.println(status);
        status = status.replaceAll("http[^ ]*", " ");
//        status = status.replaceAll("\".*\"", " ");
        status = status.replaceAll("http.*[\r|\n]*", " ");
        status = status.replaceAll(" +", " ");

        return status;
    }

    public static String removeStartAndFinalApostrophs(String string) {
        string = string.replaceAll("’", "'");
        string = string.endsWith("'s") ? string.substring(0, string.lastIndexOf("'s")) : string;
        string = string.replaceAll("l'", " ");
        string = string.replaceAll("d'", " ");
        string = string.replaceAll("m'", " ");
        string = string.replaceAll("t'", " ");
        string = string.replaceAll("j'", " ");
        string = string.replaceAll("c'", " ");
        string = string.replaceAll("n'", " ");
        string = string.replaceAll("s'", " ");
        return string.trim();
    }

    public static String normalizeApostrophs(String string) {
        string = string.replaceAll("’", "'");
        return string;
    }

    public static String removeTermsBetweenQuotes(String string) {
        string = string.replaceAll("\".*?\"", " ");
        string = string.replaceAll("«.*?»", " ");
        string = string.replaceAll("“.*?”", " ");
        return string.trim();
    }

    public static boolean isItCleaned(String status) {
        if (status.contains("\"")) {
            String s1 = status.replaceFirst("\"", "");
            return !s1.contains("\"");
        } else {
            return true;
        }
    }

    public static Multiset<String> removeSmallWordsOrNumeric(Multiset<String> terms, int maxLetters) {

        Iterator<Map.Entry<String, Integer>> it = terms.getEntrySet().iterator();
        while (it.hasNext()) {
            String string = it.next().getKey();
            if (string.length() < maxLetters | string.matches(".*\\d.*")) {
                it.remove();
            }
        }
        return terms;
    }

    public static String removeNumeric(String string) {
        return string.replaceAll("[\\d]", "");
    }

    public static String removeHashtags(String status) {
        return status.replaceAll("#\\p{L}+", "");
    }

    public static String putInLowerCase(String input) {
        return input.toLowerCase();
    }

    public static String removedXmlEscaped(String input) {
        if (input == null) {
            return input;
        }
        String cleaned = input.replaceAll("&gt;", " ");
        cleaned = cleaned.replaceAll("&lt;", " ");
        cleaned = cleaned.replaceAll("&amp;", " ");
        cleaned = cleaned.replaceAll("&apos;", " ");
        cleaned = cleaned.replaceAll("&quot;", " ");
        return cleaned;
    }

    public static String removeEmojisBetweenSemiColons(String cleaned) {
        Pattern p = Pattern.compile(":(.*?):");
        int count = cleaned.length() - cleaned.replace(":", "").length();
        if (count >= 2) {
            Matcher m = p.matcher(cleaned);
            while (m.find()) {
                cleaned = cleaned.replace(m.group(1), " ");
            }
        }
        return cleaned;
    }

    public static String removeNullChars(String string) {
        char nulChar = Character.MIN_VALUE;
        string = string.replaceAll(String.valueOf(nulChar), " ");
        string = string.replaceAll("\0", "");
        return string.trim();
    }

    // from https://stackoverflow.com/a/15191508/798502
    public static String flattenToAscii(String string) {
        if (string == null || string.isBlank()) {
            return string;
        }
        char[] out = new char[string.length()];
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        int j = 0;
        for (int i = 0, n = string.length(); i < n; ++i) {
            char c = string.charAt(i);
            if (c <= '\u007F') {
                out[j++] = c;
            }
        }
        return new String(out, 0, j);
    }

    public static Map<Integer, String> doAllCleaningOps(Map<Integer, String> mapOfLines) {
        Map<Integer, String> cleanedLines = new HashMap();
        if (mapOfLines == null) {
            return cleanedLines;
        }

        for (Map.Entry<Integer, String> entry : mapOfLines.entrySet()) {
            String status = entry.getValue();
            if (status == null || status.isBlank()) {
                cleanedLines.put(entry.getKey(), "");
                continue;
            }
            status = TextCleaningOps.removeUrls(status);
            status = TextCleaningOps.normalizeApostrophs(status);
            status = TextCleaningOps.removeNullChars(status);
            status = status.replaceAll(" +", " ");
            status = TextCleaningOps.removePunctuationSigns(status);
            status = TextCleaningOps.flattenToAscii(status);
            status = status.replaceAll(" +", " ");
            cleanedLines.put(entry.getKey(), status);
        }
        return cleanedLines;
    }

    public static String doAllCleaningOps(String status) {

        if (status == null) {
            return "";
        }
        status = TextCleaningOps.removeUrls(status);
        status = TextCleaningOps.normalizeApostrophs(status);
        status = TextCleaningOps.removeNullChars(status);
        status = status.replaceAll(" +", " ");
        status = TextCleaningOps.removePunctuationSigns(status);
        status = TextCleaningOps.flattenToAscii(status);
        status = status.replaceAll(" +", " ");

        return status;
    }

    public static Set<String> doAllCleaningOps(Set<String> lines) {

        Set<String> results = new HashSet();
        if (lines == null) {
            return results;
        }
        Map<Integer, String> map = new HashMap();
        Integer count = 0;
        for (String string : lines) {
            map.put(count++, string);
        }
        Map<Integer, String> cleaned = doAllCleaningOps(map);
        for (Map.Entry<Integer, String> entry : cleaned.entrySet()) {
            results.add(entry.getValue());
        }
        return results;
    }

}
