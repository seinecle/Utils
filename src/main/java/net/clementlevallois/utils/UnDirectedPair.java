package net.clementlevallois.utils;

public class UnDirectedPair<L extends Comparable<? super L>> implements Comparable<UnDirectedPair<L>> {

    private final L left;
    private final L right;

    public UnDirectedPair(L left, L right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public L getRight() {
        return right;
    }

    public L getOther(L element) {
        if (this.left.equals(element)) {
            return this.right;
        }
        if (this.right.equals(element)) {
            return this.left;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hashFirst = left.hashCode();
        int hashSecond = right.hashCode();
        int maxHash = Math.max(hashFirst, hashSecond);
        int minHash = Math.min(hashFirst, hashSecond);
        return minHash * 31 + maxHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof UnDirectedPair)) {
            return false;
        }
        UnDirectedPair pairo = (UnDirectedPair) o;
        return ((this.left.equals(pairo.getLeft())
                & this.right.equals(pairo.getRight())) || (this.left.equals(pairo.getRight())
                & this.right.equals(pairo.getLeft())));
    }

    @Override
    public int compareTo(UnDirectedPair<L> other) {
        int cmp = this.left.compareTo(other.left);
        if (cmp == 0) {
            cmp = this.right.compareTo(other.right);
        } else {
            cmp = this.left.compareTo(other.right);
            if (cmp == 0) {
                cmp = this.right.compareTo(other.left);
            }
        }
        return cmp;
    }

    @Override
    public String toString() {
        return "{" + "left=" + left + ", right=" + right + '}';
    }

}
