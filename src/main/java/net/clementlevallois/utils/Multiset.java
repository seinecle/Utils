/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

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
public class Multiset<T> {

    private Map<T, Integer> internalMap;
    private final Comparator<Map.Entry<T, Integer>> byCountDesc;
    private final Comparator<Map.Entry<T, Integer>> byCountAsc;

    public Multiset() {
        internalMap = new HashMap();
        byCountDesc = (Map.Entry<T, Integer> e1, Map.Entry<T, Integer> e2) -> e2.getValue() - e1.getValue();
        byCountAsc = (Map.Entry<T, Integer> e1, Map.Entry<T, Integer> e2) -> e1.getValue() - e2.getValue();

    }

    public Map<T, Integer> getInternalMap() {
        return internalMap;
    }

    public void setInternalMap(Map<T, Integer> internalMap) {
        this.internalMap = internalMap;
    }

    public void setCount(T element, int count) {
        internalMap.put(element, count);
    }

    public void addOne(T element) {
        Integer preCount = internalMap.get(element);
        if (preCount == null) {
            preCount = 0;
        }
        internalMap.put(element, preCount + 1);
    }

    public void addSeveral(T element, Integer count) {
        Integer preCount = internalMap.get(element);
        if (preCount == null) {
            preCount = 0;
        }
        internalMap.put(element, preCount + count);
    }

    public void addAllFromMultiset(Multiset otherMultiset) {
        Set<Entry<T, Integer>> entrySet = otherMultiset.getEntrySet();
        Iterator<Entry<T, Integer>> it = entrySet.iterator();
        while (it.hasNext()) {
            Entry<T, Integer> next = it.next();
            addSeveral(next.getKey(), next.getValue());
        }
    }

    public void addAllFromListOrSet(Collection<T> list) {
        for (T o : list) {
            addOne(o);
        }
    }

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

    public Integer getCount(T element) {
        if (!internalMap.containsKey(element)) {
            return 0;
        }
        return internalMap.get(element);
    }

    public Integer getSize() {
        return internalMap.size();
    }

    public Set<T> getElementSet() {
        return internalMap.keySet();
    }

    public Set<Map.Entry<T, Integer>> getEntrySet() {
        return internalMap.entrySet();
    }

    public <K, V extends Comparable<? super V>> Map<K, V> sortByFreq() {
        List<Entry<K, V>> list = new ArrayList(internalMap.entrySet());
        list.sort(Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        list.forEach((entry) -> {
            result.put(entry.getKey(), entry.getValue());
        });
        return result;
    }

    public List<Map.Entry<T, Integer>> sortDesc(Multiset<T> multiset) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList(multiset.getEntrySet());
        Collections.sort(toReturn, byCountDesc);
        return toReturn;
    }

    public List<Map.Entry<T, Integer>> sortDesckeepMostfrequent(Multiset<T> multiset, int n) {
        List<Map.Entry<T, Integer>> toReturn = new ArrayList();

        List<Map.Entry<T, Integer>> input = sortDesc(multiset);

        Iterator<Map.Entry<T, Integer>> iterator = input.iterator();

        int count = 0;
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

    public List toListOfElements() {
        List list = new ArrayList();
        for (Map.Entry entry : internalMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;

    }

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

}
