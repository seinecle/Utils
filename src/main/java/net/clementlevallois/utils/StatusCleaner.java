/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import com.google.common.collect.Multiset;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class StatusCleaner {

    private static Pattern p = Pattern.compile(":(.*?):");

    public static String clean(String status) {
        if (status == null) {
            return "";
        }
        status = status.replace("...", " ");
        status = status.replace(",", " ");
        status = status.replace("..", " ");
//            System.out.println(status);
        status = status.replaceAll("http[^ ]*", " ");
//        status = status.replaceAll("\".*\"", " ");
        status = status.replaceAll("http.*[\r|\n]*", " ");
        status = status.replaceAll(" +", " ");

        return status;
    }

    public static String removePunctuationSigns(String string) {
        if (string == null) {
            return "";
        }
        String punctuation = "!?.@'’`+<>\"«»:-+,|$;_/~&()[]{}#=*";

        char[] chars = punctuation.toCharArray();
        for (char currChar : chars) {
            string = string.replace(String.valueOf(currChar), " ");
        }
        return string.trim();
    }

    public static String detachCamelCaseWordsAndPutInLowerCase(String string) {
        StringBuilder sb = new StringBuilder();
        int countChar = 0;
        for (char c : string.toCharArray()) {
            //if the character is uppercase, and is not preceded by an uppercase, then introduce a space before it
            if (Character.isUpperCase(c) && (countChar > 0 && !Character.isUpperCase(string.charAt(countChar - 1)))) {
                sb.append(" ");
            }
            sb.append(c);
            countChar++;
        }
        return sb.toString();
    }

    public static Multiset<String> removeSmallWords(Multiset<String> terms, int lessOrEqualToNumber) {

        Iterator<String> it = terms.iterator();
        while (it.hasNext()) {
            String string = it.next();
            if (string.length() < lessOrEqualToNumber | string.matches(".*\\d.*")) {
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
        string = StringUtils.removeEnd(string, "'s");
        string = StringUtils.removeEnd(string, "’s");
        string = string.replace("'s", " ");
        string = string.replace("’s", " ");
        string = string.replace("l'", " ");
        string = string.replace("l’", " ");

        return string.trim();
    }

    public static String normalizeApostrophs(String string) {
        string = StringUtils.replace(string, "’", "'");
        return string;
    }

    public static String removeTermsBetweenQuotes(String string) {
        string = string.replaceAll("\".*?\"", "");
        return string.trim();
    }

    public static Multiset<String> removeSmallWordsOrNumeric(Multiset<String> terms, int maxLetters) {

        Iterator<String> it = terms.iterator();
        while (it.hasNext()) {
            String string = it.next();
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
        String cleaned = StringUtils.replace(input, "&gt;", " ");
        cleaned = StringUtils.replace(cleaned, "&lt;", " ");
        cleaned = StringUtils.replace(cleaned, "&amp;", " ");
        cleaned = StringUtils.replace(cleaned, "&apos;", " ");
        cleaned = StringUtils.replace(cleaned, "&quot;", " ");
        return cleaned;
    }

    public static String removeEmojisBetweenSemiColons(String cleaned) {
        int count = cleaned.length() - cleaned.replace(":", "").length();
        if (count >= 2) {
            Matcher m = p.matcher(cleaned);
            while (m.find()) {
                cleaned = cleaned.replace(m.group(1), " "); //is your string. do what you want
            }
        }
        return cleaned;
    }

}
