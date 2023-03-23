/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author LEVALLOIS
 * @param <T>
 */
public class Multiset
        <T> 
//        <T extends Comparable<? super T>> 
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<T, Integer> internalMap;
    private Integer maxElements;

    private class ComparatorDescending implements Comparator<Map.Entry<T, Integer>>, Serializable {

        @Override
        public int compare(Map.Entry<T, Integer> firstEntry, Map.Entry<T, Integer> secondEntry) {
            return secondEntry.getValue() - firstEntry.getValue();
        }
    }

    private class ComparatorAscending implements Comparator<Map.Entry<T, Integer>>, Serializable {

        @Override
        public int compare(Map.Entry<T, Integer> firstEntry, Map.Entry<T, Integer> secondEntry) {
            return firstEntry.getValue() - secondEntry.getValue();
        }
    }

    /**
     *
     */
    public Multiset() {
        internalMap = new HashMap();
    }

    /**
     *
     * @param maxElements
     */
    public Multiset(Integer maxElements) {
        internalMap = new HashMap();
        this.maxElements = maxElements;

    }

    /**
     *
     * @return
     */
    public Map<T, Integer> getInternalMap() {
        return internalMap;
    }

    /**
     *
     * @param internalMap
     */
    public void setInternalMap(Map<T, Integer> internalMap) {
        this.internalMap = internalMap;
    }

    /**
     *
     * @param element
     * @param count
     */
    public void setCount(T element, int count) {
        internalMap.put(element, count);
    }

    /**
     *
     * @param element
     */
    public void addOne(T element) {
        Integer preCount = internalMap.get(element);
        if (preCount == null) {
            preCount = 0;
        }
        internalMap.put(element, preCount + 1);
    }

    /**
     *
     * @param element
     */
    public void addOneWithLimitToMaxElements(T element) {
        if (internalMap.size() >= maxElements) {
            return;
        }
        Integer preCount = internalMap.get(element);
        if (preCount == null) {
            preCount = 0;
        }
        internalMap.put(element, preCount + 1);
    }

    /**
     *
     * @param element
     * @param count
     */
    public void addSeveral(T element, Integer count) {
        Integer preCount = internalMap.get(element);
        if (preCount == null) {
            preCount = 0;
        }
        internalMap.put(element, preCount + count);
    }

    /**
     *
     * @param otherMultiset
     */
    public void addAllFromMultiset(Multiset otherMultiset) {
        Set<Entry<T, Integer>> entrySet = otherMultiset.getEntrySet();
        for (Entry<T, Integer> next : entrySet) {
            addSeveral(next.getKey(), next.getValue());
        }
    }

    /**
     *
     * @param map
     */
    public void addAllFromMap(Map<T, Integer> map) {
        Set<Entry<T, Integer>> entrySet = map.entrySet();
        for (Entry<T, Integer> next : entrySet) {
            addSeveral(next.getKey(), next.getValue());
        }
    }

    /**
     *
     * @param list
     */
    public void addAllFromListOrSet(Collection<T> list) {
        for (T o : list) {
            addOne(o);
        }
    }

    /**
     *
     * @param element
     */
    public void removeOne(T element) {
        if (!internalMap.containsKey(element)) {
            return;
        }

        Integer count = internalMap.get(element);
        if (count > 1) {
            internalMap.put(element, count - 1);
        } else {
            internalMap.remove(element);
        }

    }

    /**
     *
     * @param element
     * @param numberToBeRemoved
     */
    public void removeSeveral(T element, Integer numberToBeRemoved) {
        if (!internalMap.containsKey(element)) {
            return;
        }
        Integer count = internalMap.get(element);
        if (count > numberToBeRemoved + 1) {
            internalMap.put(element, count - numberToBeRemoved);
        } else {
            internalMap.remove(element);
        }
    }

    /**
     *
     * @param element
     * @return
     */
    public Integer getCount(T element) {
        if (!internalMap.containsKey(element)) {
            return 0;
        }
        return internalMap.get(element);
    }

    /**
     *
     * @return
     */
    public Integer getSize() {
        return internalMap.size();
    }

    /**
     *
     * @return
     */
    public Set<T> getElementSet() {
        return internalMap.keySet();
    }

    /**
     *
     * @return
     */
    public Set<Map.Entry<T, Integer>> getEntrySet() {
        return internalMap.entrySet();
    }

    /**
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V extends Comparable<? super V>> Map<K, V> sortByFreq() {
        List<Entry<K, V>> list = new ArrayList(internalMap.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        list.forEach((entry) -> {
            result.put(entry.getKey(), entry.getValue());
        });
        return result;
    }

    /**
     *
     * @param multiset
     * @return
     */
    public List<Map.Entry<T, Integer>> sortDesc(Multiset<T> multiset) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList(multiset.getEntrySet());
        Collections.sort(toReturn, new ComparatorDescending());
        return toReturn;
    }

    /**
     *
     * @param multiset
     * @return
     */
    public List<Map.Entry<T, Integer>> sortAsc(Multiset<T> multiset) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList(multiset.getEntrySet());
        Collections.sort(toReturn, new ComparatorAscending());
        return toReturn;
    }

    /**
     *
     * @param multiset
     * @param n
     * @return
     */
    public List<Map.Entry<T, Integer>> sortDesckeepMostfrequent(Multiset<T> multiset, int n) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList();

        List<Map.Entry<T, Integer>> input = sortDesc(multiset);

        Iterator<Map.Entry<T, Integer>> iterator = input.iterator();

        int count = 1;
        Map.Entry<T, Integer> object;
        while (iterator.hasNext()) {
            object = iterator.next();
            toReturn.add(object);

            if (count == n) {
                break;
            }
            count++;

        }
        return toReturn;
    }

    /**
     *
     * @param multiset
     * @param n
     * @return
     */
    public Multiset<T> keepMostfrequent(Multiset<T> multiset, int n) {

        Multiset<T> toReturn = new Multiset();
        List<Map.Entry<T, Integer>> input = sortDesc(multiset);

        Iterator<Map.Entry<T, Integer>> iterator = input.iterator();

        int count = 1;
        Map.Entry<T, Integer> object;
        while (iterator.hasNext()) {
            object = iterator.next();
            toReturn.addSeveral(object.getKey(), object.getValue());
            if (count == n) {
                break;
            }
            count++;

        }
        return toReturn;
    }

    /**
     *
     * @param multiset
     * @param n
     * @return
     */
    public List<Map.Entry<T, Integer>> sortDesckeepAboveMinFreq(Multiset<T> multiset, int n) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList();

        List<Map.Entry<T, Integer>> input = sortDesc(multiset);

        Iterator<Map.Entry<T, Integer>> iterator = input.iterator();

        Map.Entry<T, Integer> object;
        while (iterator.hasNext()) {
            object = iterator.next();
            if (object.getValue() > n) {
                toReturn.add(object);
            }

        }
        return toReturn;
    }

    /**
     *
     * @return
     */
    public List toListOfElements() {
        List list = new ArrayList();
        for (Map.Entry entry : internalMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;

    }

    /**
     *
     * @return
     */
    public List<T> toListOfAllOccurrences() {
        List list = new ArrayList();
        for (Map.Entry entry : internalMap.entrySet()) {
            Integer i = (Integer) entry.getValue();
            for (int a = 0; a < i; a++) {
                list.add(entry.getKey());
            }
        }
        return list;

    }

    /**
     *
     * @param topRank
     */
    public void printTopRankedElements(int topRank) {
        List<Entry<T, Integer>> sortDesckeepMostfrequent = this.sortDesckeepMostfrequent(this, topRank);
        for (Map.Entry<T, Integer> entry : sortDesckeepMostfrequent) {
            System.out.println(entry.toString());
        }
    }

    /**
     *
     * @param topRank
     * @return
     */
    public String topRankedElementsToString(int topRank) {
        StringBuilder sb = new StringBuilder();
        List<Entry<T, Integer>> sortDesckeepMostfrequent = this.sortDesckeepMostfrequent(this, topRank);
        for (Map.Entry<T, Integer> entry : sortDesckeepMostfrequent) {
            sb.append(entry.getKey()).append(" x ").append(entry.getValue()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    /**
     *
     * @param topRank
     * @return
     */
    public String topRankedElementsToStringWithoutCounts(int topRank) {
        StringBuilder sb = new StringBuilder();
        List<Entry<T, Integer>> sortDesckeepMostfrequent = this.sortDesckeepMostfrequent(this, topRank);
        for (Map.Entry<T, Integer> entry : sortDesckeepMostfrequent) {
            sb.append(entry.getKey()).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

}
