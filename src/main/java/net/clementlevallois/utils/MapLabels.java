/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.Serializable;

/**
 *
 * @author C. Levallois
 */

public class MapLabels implements Comparable<MapLabels>, Serializable {

//    @Id
//    private ObjectId id;
    private Author author1;
    private Author author2;
    private String author1displayed;
    private String author2displayed;
    private String uuid;
    private boolean editable;
    private boolean deleted;

    /**
     *
     */
    public MapLabels() {
    }

    /**
     *
     * @param author1
     * @param author2
     * @param uuid
     */
    public MapLabels(Author author1, Author author2, String uuid) {
        this.author1 = author1;
        this.author2 = author2;
        this.uuid = uuid;
    }

    /**
     *
     * @param author1
     * @param author2
     */
    public MapLabels(Author author1, Author author2) {
        this.author1 = author1;
        this.author2 = author2;
    }

    /**
     *
     * @param author1displayed
     * @param author2displayed
     */
    public MapLabels(String author1displayed, String author2displayed) {
        this.author1displayed = author1displayed;
        this.author2displayed = author2displayed;
    }

    /**
     *
     * @return
     */
    public Author getAuthor1() {
        return author1;
    }

    /**
     *
     * @param author1
     */
    public void setAuthor1(Author author1) {
        this.author1 = author1;
    }

    /**
     *
     * @return
     */
    public Author getAuthor2() {
        return author2;
    }

    /**
     *
     * @param author2
     */
    public void setAuthor2(Author author2) {
        this.author2 = author2;
    }

    /**
     *
     * @param Uuid
     */
    public void setUuid(String Uuid) {
        this.uuid = Uuid;
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
     * @return
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     *
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    /**
     *
     * @return
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     *
     * @param deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     *
     * @return
     */
    public String getAuthor1displayed() {
        return author1.getFullname();
    }

    /**
     *
     * @param author1displayed
     */
    public void setAuthor1displayed(String author1displayed) {
        this.author1.setFullname(author1displayed);
    }

    /**
     *
     * @return
     */
    public String getAuthor2displayed() {
        return author2.getFullname();
    }

    /**
     *
     * @param author2displayed
     */
    public void setAuthor2displayed(String author2displayed) {
        this.author2.setFullname(author2displayed);
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(MapLabels o) {
//        return this.author2displayed.compareTo(o.getAuthor2displayed());
        return this.author2.getFullname().compareTo(o.getAuthor2().getFullname());
    }
}
