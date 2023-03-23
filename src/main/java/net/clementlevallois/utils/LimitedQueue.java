/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.util.LinkedList;

/**
 *
 * @author LEVALLOIS
 * @param <E>
 */
public class LimitedQueue<E> extends LinkedList<E> {

    private final int limit;

    /**
     *
     * @param limit
     */
    public LimitedQueue(int limit) {
        this.limit = limit;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean add(E o) {
        boolean added = super.add(o);
        while (added && size() > limit) {
           super.remove();
        }
        return added;
    }
}