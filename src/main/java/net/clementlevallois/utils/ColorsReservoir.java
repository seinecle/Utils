/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author C. Levallois
 */
public class ColorsReservoir {

    private static ArrayList<String> reservoir;

    /**
     *
     * @return
     */
    public static ArrayList<String> getReservoir() {
        fillReservoir();
        return reservoir;
    }

    /**
     *
     * @return
     */
    public static Color[] getFullColorArray() {
        fillReservoir();
        Color[] colors = new Color[reservoir.size()];
        int i = 0;
        for (String htmlColor : reservoir) {
            colors[i] = Color.decode(htmlColor);
            i++;
        }
        return colors;
    }

    /**
     *
     * @param n
     * @return
     */
    public static Color[] getColorArrayForASize(int n) {
        fillReservoir();
        if (n > reservoir.size()) {
            n = reservoir.size();
        }
        Color[] colors = new Color[reservoir.size()];
        int i = 0;
        for (String htmlColor : reservoir) {
            colors[i] = Color.decode(htmlColor);
            i++;
        }
        Color[] newArray = Arrays.copyOfRange(colors, 0, n);
        return newArray;
    }

    /**
     *
     */
    public static void fillReservoir() {
        reservoir = new ArrayList();
        reservoir.add("#5E9421");
        reservoir.add("#560DE4");
        reservoir.add("#F81D6E");
        reservoir.add("#4FB1F4");
        reservoir.add("#16EF00");
        reservoir.add("#FE9617");
        reservoir.add("#320E41");
        reservoir.add("#CAC713");
        reservoir.add("#F90CD0");
        reservoir.add("#9AC9CA");
        reservoir.add("#762006");
        reservoir.add("#009082");
        reservoir.add("#2B56CE");
        reservoir.add("#F595C8");
        reservoir.add("#47FC8D");
        reservoir.add("#F99F7B");
        reservoir.add("#0E1D19");
        reservoir.add("#8BF6F9");
        reservoir.add("#57AC1E");
        reservoir.add("#780D95");
        reservoir.add("#EF5736");
        reservoir.add("#116281");
        reservoir.add("#AA8D13");
        reservoir.add("#E0D8FC");
        reservoir.add("#990854");
        reservoir.add("#C8EF86");
        reservoir.add("#A798EC");
        reservoir.add("#BB62F3");
        reservoir.add("#048A3F");
        reservoir.add("#41E6BF");
        reservoir.add("#F953B5");
        reservoir.add("#060E68");
        reservoir.add("#43210F");
        reservoir.add("#A65C01");
        reservoir.add("#F6CD70");
        reservoir.add("#C80FF6");
        reservoir.add("#2A4989");
        reservoir.add("#FD7783");
        reservoir.add("#03513F");
        reservoir.add("#570325");
        reservoir.add("#C10435");
        reservoir.add("#FEC317");
        reservoir.add("#710566");
        reservoir.add("#2710AE");
        reservoir.add("#1959FB");
        reservoir.add("#DC87ED");
        reservoir.add("#9BF257");
        reservoir.add("#042E48");
        reservoir.add("#F3B6C3");
        reservoir.add("#736717");
        reservoir.add("#83F4A4");
        reservoir.add("#488EF2");
        reservoir.add("#EFE5DA");
        reservoir.add("#3CC7EC");
        reservoir.add("#96B902");
        reservoir.add("#5F7F0B");
        reservoir.add("#C0F9DC");
        reservoir.add("#63400B");
        reservoir.add("#F46A9C");
        reservoir.add("#A63000");
        reservoir.add("#36B386");
        reservoir.add("#29C2BB");
        reservoir.add("#C6F9B7");
        reservoir.add("#FDAF4F");
        reservoir.add("#193416");
        reservoir.add("#F5B3F8");
        reservoir.add("#C02FC9");
        reservoir.add("#48FB65");
        reservoir.add("#B12C87");
        reservoir.add("#F0CC96");
        reservoir.add("#3F86B7");
        reservoir.add("#9C2741");
        reservoir.add("#D81C80");
        reservoir.add("#330C23");
        reservoir.add("#914DF8");
        reservoir.add("#FC5661");
        reservoir.add("#07501F");
        reservoir.add("#EE863A");
        reservoir.add("#CDF7FA");
        reservoir.add("#04808C");
        reservoir.add("#1E4F54");
        reservoir.add("#FDFB9C");
        reservoir.add("#3FFAEC");
        reservoir.add("#08B934");
        reservoir.add("#176815");
        reservoir.add("#F771E2");
        reservoir.add("#E73AF1");
        reservoir.add("#E61D32");
        reservoir.add("#FBABA2");
        reservoir.add("#122465");
        reservoir.add("#5E000E");
        reservoir.add("#3C3515");
        reservoir.add("#531777");
        reservoir.add("#01D328");
        reservoir.add("#CCBAF7");
        reservoir.add("#36B763");
        reservoir.add("#CEF25E");
        reservoir.add("#716EFE");
        reservoir.add("#9AF8C7");
        reservoir.add("#F9CFB5");
        reservoir.add("#A1A508");
        reservoir.add("#AC83ED");
        reservoir.add("#D71DA6");
        reservoir.add("#45490F");
        reservoir.add("#2A8260");
        reservoir.add("#022653");
        reservoir.add("#FDBC79");
        reservoir.add("#B0FC83");
        reservoir.add("#F678C0");
        reservoir.add("#23171D");
        reservoir.add("#0D8B22");
        reservoir.add("#3A736E");
        reservoir.add("#204EAF");
        reservoir.add("#FE9D61");
        reservoir.add("#885E18");
        reservoir.add("#DF6B0C");
        reservoir.add("#EDC84E");
        reservoir.add("#7D1658");
        reservoir.add("#2D37A7");
        reservoir.add("#95BCF3");
        reservoir.add("#0E1A9A");
        reservoir.add("#9E0910");
        reservoir.add("#8F0888");
        reservoir.add("#FA5DF3");
        reservoir.add("#42050B");
        reservoir.add("#259CC2");
        reservoir.add("#3DD38A");
        reservoir.add("#F7C3E4");
        reservoir.add("#048E59");
        reservoir.add("#8D0FE5");
        reservoir.add("#542102");
        reservoir.add("#FF5623");
        reservoir.add("#4F8F20");
        reservoir.add("#6A15B8");
        reservoir.add("#280955");
        reservoir.add("#D68111");
        reservoir.add("#AE7AFF");
        reservoir.add("#2C2F8B");
        reservoir.add("#A52331");
        reservoir.add("#BA2051");
        reservoir.add("#3C6ED2");
        reservoir.add("#8CDBFE");
        reservoir.add("#30C1D2");
        reservoir.add("#F5E53C");
        reservoir.add("#BDDAF7");
        reservoir.add("#291080");
        reservoir.add("#29180D");
        reservoir.add("#581B65");
        reservoir.add("#AEEB98");
        reservoir.add("#C11A65");
        reservoir.add("#FEBF9C");
        reservoir.add("#BB14D5");
        reservoir.add("#BEA808");
        reservoir.add("#D353F3");
        reservoir.add("#EAFFB5");
        reservoir.add("#A8710E");
        reservoir.add("#0759AC");
        reservoir.add("#55BF17");
        reservoir.add("#800419");
        reservoir.add("#F4E57C");
        reservoir.add("#001E2D");
        reservoir.add("#E16228");
        reservoir.add("#9CFD98");
        reservoir.add("#CDEE1D");
        reservoir.add("#63FA36");
        reservoir.add("#79FA6D");
        reservoir.add("#FB4D48");
        reservoir.add("#024803");
        reservoir.add("#F721AB");
        reservoir.add("#34F1F6");
        reservoir.add("#63153C");
        reservoir.add("#FED240");
        reservoir.add("#F56559");
        reservoir.add("#12373F");
        reservoir.add("#594D00");
        reservoir.add("#E98BAE");
        reservoir.add("#F86586");
        reservoir.add("#EFEAFD");
        reservoir.add("#6E027B");
        reservoir.add("#50B0E2");
        reservoir.add("#196933");
        reservoir.add("#3D84C5");
        reservoir.add("#651457");
        reservoir.add("#013B28");
        reservoir.add("#46211E");
        reservoir.add("#036576");
        reservoir.add("#EF9FA8");
        reservoir.add("#FB3590");
        reservoir.add("#7AE8FC");
        reservoir.add("#302A1B");
        reservoir.add("#17134D");
        reservoir.add("#DE6DFB");
        reservoir.add("#3E6F9F");
        reservoir.add("#F896F1");
        reservoir.add("#EB2CC4");
        reservoir.add("#46E0DA");
        reservoir.add("#486EEB");
        reservoir.add("#FFE0FA");
        reservoir.add("#BCFD57");
        reservoir.add("#F6B2E6");
        reservoir.add("#462804");
        reservoir.add("#2C80A4");
        reservoir.add("#E95CA0");
        reservoir.add("#243C0E");
        reservoir.add("#B45506");
        reservoir.add("#094C6A");
        reservoir.add("#C29122");
        reservoir.add("#22365B");
        reservoir.add("#FB3EE6");
        reservoir.add("#BAFC94");
        reservoir.add("#41123C");
        reservoir.add("#FF9885");
        reservoir.add("#E8F6FC");
        reservoir.add("#DF2369");
        reservoir.add("#E4FA74");
        reservoir.add("#686900");
        reservoir.add("#748100");
        reservoir.add("#F7CBD3");
        reservoir.add("#EADCAA");
        reservoir.add("#33A434");
        reservoir.add("#D1278D");
        reservoir.add("#FD8BD8");
        reservoir.add("#B5F26D");
        reservoir.add("#1BB294");
        reservoir.add("#47A9FA");
        reservoir.add("#BFEFC5");
        reservoir.add("#826304");
        reservoir.add("#8C1A7A");
        reservoir.add("#C5BFEE");
        reservoir.add("#521731");
        reservoir.add("#88B2F7");
        reservoir.add("#D7F6DA");
        reservoir.add("#620C21");
        reservoir.add("#F0E48C");
        reservoir.add("#B011DF");
        reservoir.add("#222F7A");
        reservoir.add("#5685F2");
        reservoir.add("#62A112");
        reservoir.add("#1B316B");
        reservoir.add("#F827EC");
        reservoir.add("#26E576");
        reservoir.add("#CDEBFA");
        reservoir.add("#FCBA4B");
        reservoir.add("#13949D");
        reservoir.add("#F78021");
        reservoir.add("#B61F22");
        reservoir.add("#074629");
        reservoir.add("#38AA1A");
        reservoir.add("#182033");
        reservoir.add("#C5FBEB");
        reservoir.add("#219D3D");
        reservoir.add("#A5F6B9");
        reservoir.add("#2A7152");
        reservoir.add("#A73814");
        reservoir.add("#4B1FDE");
        reservoir.add("#F76DD3");
        reservoir.add("#F2E7EE");
        reservoir.add("#AA044E");
        reservoir.add("#619FFE");
        reservoir.add("#3A0629");
        reservoir.add("#618A06");
        reservoir.add("#46D99A");
        reservoir.add("#19056C");
        reservoir.add("#EBAAFA");
        reservoir.add("#DEE627");
        reservoir.add("#326A13");
        reservoir.add("#1E4AC8");
        reservoir.add("#FEA862");
        reservoir.add("#FDDD7D");
        reservoir.add("#30A47E");
        reservoir.add("#5E360D");
        reservoir.add("#A04F01");
        reservoir.add("#E3137C");
        reservoir.add("#3E13A9");
        reservoir.add("#3FE1D1");
        reservoir.add("#18290C");
        reservoir.add("#FB5F6C");
        reservoir.add("#A6F9F9");
        reservoir.add("#E9B8FE");
        reservoir.add("#08434E");
        reservoir.add("#F9A83E");
        reservoir.add("#DC5717");
        reservoir.add("#1C1823");
        reservoir.add("#112047");
        reservoir.add("#731B0E");
        reservoir.add("#450C06");
        reservoir.add("#28146D");
        reservoir.add("#6D0514");
        reservoir.add("#EEDECD");
        reservoir.add("#667B06");
        reservoir.add("#89018C");
        reservoir.add("#F0B7EE");
        reservoir.add("#77DBFE");
        reservoir.add("#ED2ED8");
        reservoir.add("#96140B");
        reservoir.add("#2B103D");
        reservoir.add("#3BF76A");
        reservoir.add("#32FE8D");
        reservoir.add("#FB4649");
        reservoir.add("#106D1B");
    }
}
