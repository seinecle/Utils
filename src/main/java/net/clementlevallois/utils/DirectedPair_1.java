/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;


/**
 *
 * @author C. Levallois retrieved from
 * http://stackoverflow.com/questions/521171/a-java-collection-of-value-pairs-tuples
 * @param <L>
 * @param <R>
 */
public class DirectedPair_1<L extends Comparable<? super L>, R extends Comparable<? super R>> implements Comparable<DirectedPair_1<L,R>> {

    private final L left;
    private final R right;

    /**
     *
     * @param left
     * @param right
     */
    public DirectedPair_1(L left, R right) {
        this.left = left;
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
    public R getRight() {
        return right;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof DirectedPair_1)) {
            return false;
        }
        DirectedPair_1 pairo = (DirectedPair_1) o;
        return this.left.equals(pairo.getLeft())
                && this.right.equals(pairo.getRight());
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(DirectedPair_1<L,R> other) {
        int cmp = this.left.compareTo(other.left);
        if(cmp==0) {
            cmp = this.right.compareTo(other.right);
        }
        return cmp;
    }
}