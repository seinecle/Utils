/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author LEVALLOIS
 */
public class OSValidator {

    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     *
     */
    public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);

    /**
     *
     */
    public static boolean IS_MAC = (OS.indexOf("mac") >= 0);

    /**
     *
     */
    public static boolean IS_UNIX = (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

    /**
     *
     */
    public static boolean IS_SOLARIS = (OS.indexOf("sunos") >= 0);

}