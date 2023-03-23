/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


// from https://www.techiedelight.com/implement-multimap-java/

/**
 *
 * @author LEVALLOIS
 * @param <K>
 * @param <V>
 */
public class TreeMultiMap<K, V> {

    private Map<K, Collection<V>> map;

    /**
     *
     */
    public TreeMultiMap() {
        map = new TreeMap();
    }

    /**
     *
     * @param order
     */
    public TreeMultiMap(Comparator<K> order) {
        map = new TreeMap<>(order);
    }

    /**
     * Add the specified value with the specified key in this multimap.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (map.get(key) == null) {
            map.put(key, new HashSet());
        }

        map.get(key).add(value);
    }

    /**
     * Associate the specified key with the given value if not already
     * associated with a value
     *
     * @param key
     * @param value
     */
    public void putIfAbsent(K key, V value) {
        if (map.get(key) == null) {
            map.put(key, new HashSet<>());
        }

        // if value is absent, insert it
        if (!map.get(key).contains(value)) {
            map.get(key).add(value);
        }
    }

    /**
     * Returns the Collection of values to which the specified key is mapped, or
     * null if this multimap contains no mapping for the key.
     *
     * @param key
     * @return
     */
    public Collection<V> get(K key) {
        return map.get(key);
    }

    /**
     * Returns a Set view of the keys contained in this multimap.
     * @return 
     */
    public Set<K> keySet() {
        return map.keySet();
    }

    /**
     * Returns a Set view of the mappings contained in this multimap.
     * @return 
     */
    public Set<Map.Entry<K, Collection<V>>> entrySet() {
        return map.entrySet();
    }

    /**
     * Returns a Collection view of Collection of the values present in this
     * multimap.
     *
     * @return
     */
    public Collection<Collection<V>> values() {
        return map.values();
    }

    /**
     * Returns true if this multimap contains a mapping for the specified key.
     *
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    /**
     * Removes the mapping for the specified key from this multimap if present
     * and returns the Collection of previous values associated with key, or
     * null if there was no mapping for key.
     * @param key
     * @return 
     */
    public Collection<V> remove(K key) {
        return map.remove(key);
    }

    /**
     * Returns the number of key-value mappings in this multimap.
     * @return 
     */
    public int size() {
        int size = 0;
        for (Collection<V> value : map.values()) {
            size += value.size();
        }
        return size;
    }

    /**
     * Returns true if this multimap contains no key-value mappings.
     * @return 
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Removes all of the mappings from this multimap.
     */
    public void clear() {
        map.clear();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value and return true if removed
     * @param key
     * @param value
     * @return 
     */
    public boolean remove(K key, V value) {
        if (map.get(key) != null) // key exists
        {
            return map.get(key).remove(value);
        }

        return false;
    }

    /**
     * Replaces the entry for the specified key only if currently mapped to the
     * specified value and return true if replaced
     *
     * @param key
     * @param oldValue
     * @param newValue
     * @return
     */
    public boolean replace(K key, V oldValue, V newValue) {

        if (map.get(key) != null) {
            if (map.get(key).remove(oldValue)) {
                return map.get(key).add(newValue);
            }
        }
        return false;
    }
}
