/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.common.collect.Multiset;
import java.util.Iterator;
import org.apache.commons.lang3.StringUtils;

public class StatusCleaner {

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
        string = string.replace("'s", " ");
        string = string.replace("’s", " ");
        string = string.replace("l'", " ");
        string = string.replace("l’", " ");
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
        return sb.toString().toLowerCase();
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

    public static String removeSpecialCharacters(String string) {
        string = StringUtils.removeEnd(string, "'s");
        string = StringUtils.removeEnd(string, "’s");
        String punctuation = "!?.'’\":-+,$&()#=*";
        char[] chars = punctuation.toCharArray();
        for (char currChar : chars) {
            string = StringUtils.replace(string, String.valueOf(currChar), " ");
        }
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

    public static String removeHashtags(String status) {

        return status.replaceAll("#\\p{L}+", "");

    }

}
