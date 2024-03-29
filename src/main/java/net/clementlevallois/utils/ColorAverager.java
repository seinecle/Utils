/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.awt.Color;

/**
 *
 * @author C. Levallois
 */
public class ColorAverager {
    int color1;
    int color2;
    
    /**
     *
     * @param hexaValue1
     * @param hexaValue2
     */
    public ColorAverager(String hexaValue1, String hexaValue2){
        color1 = Color.decode(hexaValue1).getRGB();
        color2 = Color.decode(hexaValue2).getRGB();
    }

    /**
     *
     * @param RGB1
     * @param RGB2
     */
    public ColorAverager(int RGB1, int RGB2){
        color1 = RGB1;
        color2 = RGB2;
    }

    /**
     *
     * @return
     */
    public int createAverageColor() {
        int interpolatedColor = ((color1 & 0xFEFEFEFE) >> 1) + ((color2 & 0xFEFEFEFE) >> 1);
        return interpolatedColor;
    }
}
