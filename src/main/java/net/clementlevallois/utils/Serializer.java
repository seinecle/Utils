/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.utils;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LEVALLOIS
 */
public class Serializer {

    /**
     *
     * @param argd
     */
    public static void main(String argd[]) {
        Multiset ms = new Multiset();
        ms.addOne("test");
        Serializer.byteArraySerializerForAnyObject(ms);
    }

    /**
     *
     * @param o
     * @return
     */
    public static byte[] byteArraySerializerForAnyObject(Object o) {
        ObjectOutputStream oos = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            oos.flush();
            byte[] data = bos.toByteArray();
            return data;
        } catch (IOException ex) {
            System.out.println("ex: " + ex);
            return null;
        } finally {
            try {
                oos.close();
            } catch (IOException ex) {
                Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
