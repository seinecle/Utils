/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author C. Levallois
 */
public class RemoveNonASCII {

    public static String remove(String input) {
        input = RemoveAccents.deAccent(input);
        input = input.replaceAll("'", "");
        input = input.replaceAll("-", " ");
        return input.replaceAll("[^\\x00-\\x7F]", "");
    }
}
