package net.clementlevallois.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

//@Embedded

/**
 *
 * @author LEVALLOIS
 */
public class Quidam implements Comparable<Quidam>, Serializable {

    private String forename;
    private String surname;
    private String fullname;
    private ArrayList<Quidam> ld;
    private String uuid;
    private String fullnameWithComma;

    /**
     *
     */
    public Quidam() {
    }

    /**
     *
     * @param forename
     * @param surname
     */
    public Quidam(String forename, String surname) {
        this.forename = forename.replace(".", "").trim();
        this.surname = surname.trim();
    }

    /**
     *
     * @param forename
     * @param surname
     * @param uuid
     */
    public Quidam(String forename, String surname, UUID uuid) {
        this.forename = forename.replace(".", "").trim();
        this.surname = surname.trim();
        this.uuid = uuid.toString();
    }

    /**
     *
     * @param fullname
     * @param uuid
     */
    public Quidam(String fullname, UUID uuid) {
        this.surname = fullname.trim();
        this.uuid = uuid.toString();
    }

    /**
     *
     * @param fullname
     */
    public Quidam(String fullname) {
        this.fullname = fullname.trim();
    }

    /**
     *
     * @param forename
     */
    public void setForename(String forename) {
        this.forename = forename.replace(".", "").trim();
    }

    /**
     *
     * @return
     */
    public String getForename() {
        return forename;
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
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname.trim();
    }

    /**
     *
     * @return
     */
    public String getFullname() {

        String fullnameToReturn;
        if (fullname == null) {
            fullnameToReturn = this.toString().trim();
        } else {
            fullnameToReturn = this.fullname.trim();
        }
        return fullnameToReturn;
    }

    /**
     *
     * @param fullname
     */
    public void setFullname(String fullname) {
        this.fullname = fullname.trim();
    }

    /**
     *
     * @return
     */
    public String getFullnameWithComma() {

        String fullnameWithCommaToReturn;
        if (fullnameWithComma == null) {
            fullnameWithCommaToReturn = surname + ", " + forename;
        } else {
            fullnameWithCommaToReturn = this.fullnameWithComma.trim();
        }
        if (fullnameWithCommaToReturn == null) {
            fullnameWithCommaToReturn = "no name returned by the fullNameWithComma method on Author";
        }
        return fullnameWithCommaToReturn;
    }

    /**
     *
     * @param newFullnameWithComma
     */
    public void setFullnameWithComma(String newFullnameWithComma) {
        String[] arrayTerms = newFullnameWithComma.split(",");
//        if (arrayTerms.length == 1) {
//            this.fullname = newFullnameWithComma;
//        } 
//        else {
        this.fullnameWithComma = newFullnameWithComma;
        if (!newFullnameWithComma.contains(",")) {
            System.out.println("newFullNameWithComma:" + newFullnameWithComma);
        }
        this.forename = arrayTerms[1].trim();
        this.surname = arrayTerms[0].trim();
        this.fullname = arrayTerms[1].trim() + " " + arrayTerms[0].trim();
//        }
    }

    /**
     *
     * @return
     */
    public String getLd() {
        return ld.toArray().toString();
    }

    /**
     *
     * @param ld
     */
    public void setLd(ArrayList<Quidam> ld) {
        this.ld = ld;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.getFullname() != null ? this.getFullname().hashCode() : 0);
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
        final Quidam other = (Quidam) obj;
        if ((this.getFullname() == null) ? (other.getFullname() != null) : !this.getFullname().equals(other.getFullname())) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public String[] toArray() {
        String[] args = new String[2];
        args[0] = forename;
        args[1] = surname;
        return args;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(Quidam other) {
        int result;
        if (getFullname() == null || other.getFullname() == null) {
            result = (getForename() + getSurname()).compareTo(other.getForename() + other.getSurname());
        } else {
            result = getFullnameWithComma().compareTo(other.getFullnameWithComma());
        }
        return result;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {

        String fullnameToReturn;
        if (fullname == null) {
            fullnameToReturn = forename + " " + surname;
        } else {
            fullnameToReturn = this.fullname.trim();
        }
        return fullnameToReturn;
    }
}
