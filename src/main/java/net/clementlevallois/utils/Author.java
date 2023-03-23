/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.UUID;

/**
 *
 * @author LEVALLOIS
 */
public class Author extends Quidam {

    private int yearFirstCollab;
    private int yearLastCollab;
    private int timesMentioned;

    /**
     *
     */
    public Author() {
    }

    /**
     *
     * @param forename
     * @param surname
     */
    public Author(String forename, String surname) {
        super(forename.trim(), surname.trim());
    }

    /**
     *
     * @param forename
     * @param surname
     * @param uuid
     */
    public Author(String forename, String surname, UUID uuid) {
        super(forename.trim(), surname.trim(), uuid);
    }

    /**
     *
     * @param fullname
     * @param uuid
     */
    public Author(String fullname, UUID uuid) {
        super(fullname.trim(), uuid);
    }

    /**
     *
     * @param fullname
     */
    public Author(String fullname) {
        super(fullname.trim());
    }

    /**
     *
     * @return
     */
    public int getYearFirstCollab() {
        return yearFirstCollab;
    }

    /**
     *
     * @param yearFirstCollab
     */
    public void setYearFirstCollab(int yearFirstCollab) {
        this.yearFirstCollab = yearFirstCollab;
    }

    /**
     *
     * @return
     */
    public int getYearLastCollab() {
        return yearLastCollab;
    }

    /**
     *
     * @param yearLastCollab
     */
    public void setYearLastCollab(int yearLastCollab) {
        this.yearLastCollab = yearLastCollab;
    }

    /**
     *
     * @return
     */
    public int getTimesMentioned() {
        return timesMentioned;
    }

    /**
     *
     * @param timesMentioned
     */
    public void setTimesMentioned(int timesMentioned) {
        this.timesMentioned = timesMentioned;
    }
    
    
    
}
