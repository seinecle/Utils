/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author C. Levallois
 * @param <L>
 * @param <R>
 */
public class CooccurrencesAnalyzer<L extends Comparable<? super L>, R extends Comparable<R>> implements Serializable {

    private Map<R, Set<L>> inputUndirected;
    private Map<R, UnDirectedPair<L>> inputDirected;

    /**
     *
     */
    public CooccurrencesAnalyzer() {
    }

    /**
     *
     * @param inputUndirected
     */
    public void setInputUndirected(Map<R, Set<L>> inputUndirected) {
        this.inputUndirected = inputUndirected;
    }

    /**
     *
     * @param inputDirected
     */
    public void setInputDirected(Map<R, UnDirectedPair<L>> inputDirected) {
        this.inputDirected = inputDirected;
    }

//    public CooccurrencesAnalyzer(Map<R, DirectedPair<L,L>> input) {
//        this.input = input;
//    }

    /**
     *
     * @return
     */
    public Set<Node> returnSetNodes() {
        Set<Node> setNodes = new HashSet();
        Set<L> set;
        UnDirectedPair pair;
        Iterator<L> setIterator;
        if (inputUndirected == null) {
            Iterator<UnDirectedPair<L>> inputValuesIterator = inputDirected.values().iterator();
            while (inputValuesIterator.hasNext()) {
                pair = inputValuesIterator.next();
                set = new HashSet();
                set.addAll((Set<L>) pair.getLeft());
                set.addAll((Set<L>) pair.getRight());
                setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    L l = setIterator.next();
                    setNodes.add(new Node((String) l, (String) l));
                }
            }

        } else {
            Iterator<Set<L>> inputValuesIterator = inputUndirected.values().iterator();
            while (inputValuesIterator.hasNext()) {
                set = inputValuesIterator.next();
                setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    L l = setIterator.next();
                    setNodes.add(new Node((String) l, (String) l));
                }
            }
        }
        return setNodes;
    }

    /**
     *
     * @return
     */
    public Set<Edge> returnSetEdges() {
        Set<Edge> setEdges;
//        if (inputUndirected == null) {
//            setEdges = returnSetEdgesDirected();
//
//        }
//        else {
            setEdges = returnSetEdgesUndirected();
//
//        }
        return setEdges;

    }

//    public Set<Edge> returnSetEdgesDirected() {
//        Clock getAllDirectedPairsClock = new Clock("getting all directed pairs of mentions");
//        Set<Edge> setEdges = new HashSet();
//        Set<DirectedPair> currSetEdges;
//        Iterator<UnDirectedPair<L>> inputValuesIterator = inputDirected.values().iterator();
//        Iterator<DirectedPair> currSetEdgesIterator;
//        UnDirectedPair<L> pairSetsNodes;
//        DirectedPair<L, L> pair;
//        Set<L> setSources;
//        Set<L> setTargets;
//        while (inputValuesIterator.hasNext()) {
//            pairSetsNodes = inputValuesIterator.next();
//            setSources = (Set<L>) pairSetsNodes.getLeft();
//            setTargets = (Set<L>) pairSetsNodes.getRight();
//            currSetEdges = new FindAllPairs().getAllDirectedPairsFromTwoSets(setSources, setTargets);
//            currSetEdgesIterator = currSetEdges.iterator();
//            while (currSetEdgesIterator.hasNext()) {
//                pair = currSetEdgesIterator.next();
//                Edge edge = new Edge((String) pair.getLeft(), (String) pair.getRight(), true);
//                multisetEdges.addOne();
//            }
//        }
//        Iterator<Edge> multisetEdgesIterator = multisetEdges.getElementSet().iterator();
//        Edge edge;
//        while (multisetEdgesIterator.hasNext()) {
//            edge = multisetEdgesIterator.next();
//            edge.setWeight(multisetEdges.getCount(edge));
//            setEdges.add(edge);
//        }
//
//
//
//        getAllDirectedPairsClock.closeAndPrintClock();
//
//        return setEdges;
//    }

    /**
     *
     * @return
     */

    public Set<Edge> returnSetEdgesUndirected() {
        Set<Edge> setEdges = new HashSet();
        Set<L> set;
        Set<UnDirectedPair> currSetEdges;
        Iterator<Set<L>> inputValuesIterator = inputUndirected.values().iterator();
        Iterator<UnDirectedPair> currSetEdgesIterator;
        while (inputValuesIterator.hasNext()) {
            set = inputValuesIterator.next();
            currSetEdges = new FindAllPairs().getAllUndirectedPairs(set);
            currSetEdgesIterator = currSetEdges.iterator();
            while (currSetEdgesIterator.hasNext()) {
                UnDirectedPair pair = currSetEdgesIterator.next();
                setEdges.add(new Edge((String) pair.getLeft(), (String) pair.getRight(), false));
            }
        }
        return setEdges;
    }
}
