/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author C. Levallois
 */

public class CloseMatch implements Comparable<CloseMatch>, Serializable {


    private String author1;
    private String author2;
    private String author1Displayed;
    private String author2Displayed;
    private String author3;
    private boolean author1Selected;
    private boolean author2Selected;
    private String uuid;

    /**
     *
     */
    public CloseMatch() {
    }

    /**
     *
     * @param item1
     * @param item2
     */
    public CloseMatch(String item1, String item2) {
        this.author1 = item1;
        this.author2 = item2;
//        LinkedList<diff_match_patch.Diff> diffs = new diff_match_patch().diff_main(item1, item2);
//        this.author1Displayed = new diff_match_patch().diff_text1Custom(diffs);
//        this.author2Displayed = new diff_match_patch().diff_text2Custom(diffs);
//        System.out.println("disp1: "+author1Displayed);
//        System.out.println("disp2: "+author2Displayed);

    }

    /**
     *
     * @return
     */
    public String getAuthor1() {
        return author1;
    }

    /**
     *
     * @param author1
     */
    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    /**
     *
     * @return
     */
    public String getAuthor2() {
        return author2;
    }

    /**
     *
     * @param author2
     */
    public void setAuthor2(String author2) {
        this.author2 = author2;
    }

    /**
     *
     * @return
     */
    public String getAuthor3() {
        return author3;
    }

    /**
     *
     * @param author3
     */
    public void setAuthor3(String author3) {
        this.author3 = author3;
    }

    /**
     *
     * @return
     */
    public String getAuthor1Displayed() {
        return author1Displayed;
    }

    /**
     *
     * @param author1Displayed
     */
    public void setAuthor1Displayed(String author1Displayed) {
        this.author1Displayed = author1Displayed;
    }

    /**
     *
     * @return
     */
    public String getAuthor2Displayed() {
        return author2Displayed;
    }

    /**
     *
     * @param author2Displayed
     */
    public void setAuthor2Displayed(String author2Displayed) {
        this.author2Displayed = author2Displayed;
    }

    /**
     *
     * @return
     */
    public boolean isAuthor1Selected() {
        return author1Selected;
    }

    /**
     *
     * @param author1Selected
     */
    public void setAuthor1Selected(boolean author1Selected) {
        this.author1Selected = author1Selected;
    }

    /**
     *
     * @return
     */
    public boolean isAuthor2Selected() {
        return author2Selected;
    }

    /**
     *
     * @param author2Selected
     */
    public void setAuthor2Selected(boolean author2Selected) {
        this.author2Selected = author2Selected;
    }

    /**
     *
     * @return
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + (this.author1 != null ? this.author1.hashCode() : 0);
        hash = 73 * hash + (this.author2 != null ? this.author2.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CloseMatch other = (CloseMatch) obj;
        if ((this.author1 == null) ? (other.author1 != null) : !this.author1.equals(other.author1)) {
            return false;
        }
        if ((this.author2 == null) ? (other.author2 != null) : !this.author2.equals(other.author2)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(CloseMatch o) {
        return this.author1.compareTo(o.author1);
    }
}
