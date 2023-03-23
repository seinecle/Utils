/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author C. Levallois
 */
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// source: https://stackoverflow.com/a/23846961/798502

/**
 *
 * @author LEVALLOIS
 */
public class MapUtils {

    /**
     *
     * @param <K>
     * @param <V>
     * @param map
     * @param topElementsToKeep
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortAscendingByValue(Map<K, V> map, int topElementsToKeep) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(topElementsToKeep)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
