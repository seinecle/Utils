/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author Clement
 * @param <L>
 * @param <M>
 * @param <R>
 */
public class Triple<L, M, R> {
    
    private final L left;
    private final M middle;    
    private final R right;

    /**
     *
     * @param left
     * @param middle
     * @param right
     */
    public Triple(L left, M middle, R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    /**
     *
     * @return
     */
    public L getLeft() {
        return left;
    }

    /**
     *
     * @return
     */
    public M getMiddle() {
        return middle;
    }

    /**
     *
     * @return
     */
    public R getRight() {
        return right;
    }    
    
}
