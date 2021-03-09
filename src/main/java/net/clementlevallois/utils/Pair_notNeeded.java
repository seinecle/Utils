///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package net.clementlevallois.utils;
//
///**
// *
// * @author C. Levallois
// * @param <T>
// */
//public class Pair<T> {
//
//    private T left;
//    private T right;
//
//    public Pair(T left, T right) {
//        this.left = left;
//        this.right = right;
//    }
//
//    public Pair() {
//    }
//
//    public void setLeft(T left) {
//        this.left = left;
//    }
//
//    public void setRight(T right) {
//        this.right = right;
//    }
//    
//    
//
//    public T getLeft() {
//        return left;
//    }
//
//    public T getRight() {
//        return right;
//    }
//
//    @Override
//    public int hashCode() {
//        return left.hashCode() ^ right.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Pair<T> other = (Pair<T>) obj;
//        if (this.left != other.left && (this.left == null || !this.left.equals(other.left))) {
//            return false;
//        }
//        if (this.right != other.right && (this.right == null || !this.right.equals(other.right))) {
//            return false;
//        }
//        return true;
//    }
//}
