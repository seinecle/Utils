/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author C. Levallois
 */
public class findAllPairsAuthorsInSet {

    /**
     *
     * @param setAuthorsInHere
     * @return
     */
    static public TreeSet<DirectedPair<Author, Author>> getAllPairs(Set<Author> setAuthorsInHere) {
        Set<Author> setAuthorsProcessed = new TreeSet();
        TreeSet<DirectedPair<Author, Author>> setPairs = new TreeSet();
        Iterator<Author> setAuthorsIteratorA = setAuthorsInHere.iterator();
        Iterator<Author> setAuthorsIteratorB;
        Author currAuthorA;
        Author currAuthorB;
        while (setAuthorsIteratorA.hasNext()) {
            currAuthorA = setAuthorsIteratorA.next();
            setAuthorsIteratorB = setAuthorsInHere.iterator();
            while (setAuthorsIteratorB.hasNext()) {
                currAuthorB = setAuthorsIteratorB.next();
                if (currAuthorB == null) {
                    System.out.println("currAuthorB is null");
                }
                if (currAuthorA == null) {
                    System.out.println("currAuthorA is null");
                }
                if (!setAuthorsProcessed.contains(currAuthorB) && !currAuthorA.equals(currAuthorB)) {
                    setPairs.add(new DirectedPair(currAuthorA, currAuthorB));
                }
            }
            setAuthorsProcessed.add(currAuthorA);
        }
        return setPairs;
    }
}
