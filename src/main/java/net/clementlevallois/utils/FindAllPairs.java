/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * C.Levallois
 * @param <T>
 */
public class FindAllPairs<T extends Comparable<? super T>> {

    private T t;

    public Set<DirectedPair<T, T>> getAllDirectedPairs(Set<T> setObjects) {
        Set<T> setObjectsProcessed = new HashSet();
        Set<DirectedPair<T, T>> setPairs = new HashSet();
        Iterator<T> setObjectsIteratorA = setObjects.iterator();
        Iterator<T> setObjectsIteratorB;
        T currTA;
        T currTB;
        while (setObjectsIteratorA.hasNext()) {
            currTA = setObjectsIteratorA.next();
            setObjectsIteratorB = setObjects.iterator();
            while (setObjectsIteratorB.hasNext()) {
                currTB = setObjectsIteratorB.next();
                if (!setObjectsProcessed.contains(currTB) && !currTA.equals(currTB)) {
                    setPairs.add(new DirectedPair(currTA, currTB));
                }
            }
            setObjectsProcessed.add(currTA);
        }
        return setPairs;

    }

    public Set<DirectedPair<T, T>> getAllDirectedPairsFromTwoSets(Set<T> setSources, Set<T> setTargets) {
        Set<DirectedPair<T, T>> setPairs = new TreeSet();
        Iterator<T> setSourcesIterator = setSources.iterator();
        Iterator<T> setTargetsIterator;
        T source;
        T target;
        while (setSourcesIterator.hasNext()) {
            source = setSourcesIterator.next();
            setTargetsIterator = setTargets.iterator();
            while (setTargetsIterator.hasNext()) {
                target = setTargetsIterator.next();
                if (!source.equals(target)) {
                    setPairs.add(new DirectedPair(source, target));
                }
            }
        }
        return setPairs;

    }

    public Set<UnDirectedPair<T>> getAllUndirectedPairs(Set<T> setObjects) {
        Set<UnDirectedPair<T>> setPairs;
        setPairs = new HashSet();
        if (setObjects.size() < 2) {
            return setPairs;
        }

        Set<T> setObjectsProcessed = new HashSet();

        Iterator<T> setObjectsIteratorA = setObjects.iterator();
        Iterator<T> setObjectsIteratorB;
        T currTA;
        T currTB;

        while (setObjectsIteratorA.hasNext()) {
            currTA = setObjectsIteratorA.next();
            setObjectsIteratorB = setObjects.iterator();
            while (setObjectsIteratorB.hasNext()) {
                currTB = setObjectsIteratorB.next();
                if (!setObjectsProcessed.contains(currTB) && !currTA.equals(currTB)) {
                    setPairs.add(new UnDirectedPair(currTA, currTB));
                }
            }
            setObjectsProcessed.add(currTA);
        }
        return setPairs;
    }

    public Set<UnDirectedPair<T>> getAllUndirectedPairsFromList(List<T> listObjects) {
        Set<UnDirectedPair<T>> setPairs;
        setPairs = new HashSet();
        if (listObjects.size() < 2) {
            return setPairs;
        }

        Set<T> setObjectsProcessed = new HashSet();

        Iterator<T> setObjectsIteratorA = listObjects.iterator();
        Iterator<T> setObjectsIteratorB;
        T currTA;
        T currTB;

        while (setObjectsIteratorA.hasNext()) {
            currTA = setObjectsIteratorA.next();
            setObjectsIteratorB = listObjects.iterator();
            while (setObjectsIteratorB.hasNext()) {
                currTB = setObjectsIteratorB.next();
                if (!setObjectsProcessed.contains(currTB) && !currTA.equals(currTB)) {
                    setPairs.add(new UnDirectedPair(currTA, currTB));
                }
            }
            setObjectsProcessed.add(currTA);
        }
        return setPairs;
    }

    public List<Map<String,T>> getAllUndirectedPairsAsList(Set<T> setObjects) {
//        Clock findingAllPairsClock = new Clock("finding all pairs in a set of "+setObjects.size()+" objects");
        List<T> listObjects = new ArrayList();
        listObjects.addAll(setObjects);

        List<Map<String,T>> listPairs = new ArrayList();
        Iterator<T> listIterator1 = listObjects.listIterator();
        Iterator<T> listIterator2;
        int count = 1;
        T object1;
        while (listIterator1.hasNext()) {
            object1 = listIterator1.next();
            listIterator2 = listObjects.listIterator(count++);
            while (listIterator2.hasNext()) {
                Map<String, T> pair = new HashMap();
                pair.put("left", object1);
                pair.put("right",listIterator2.next());
                listPairs.add(pair);
            }
        }
//        System.out.println("number of pairs: "+listPairs.size());
//        findingAllPairsClock.closeAndPrintClock();
        return listPairs;
    }
}
