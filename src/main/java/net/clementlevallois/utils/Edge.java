/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

import java.io.Serializable;
import java.util.Objects;


/**
 *
 * @author C. Levallois
 * @param <T>
 *
 */
public class Edge <T extends Comparable<? super T>> implements Comparable, Serializable {

    private String id;
    private String label;
    private String source;
    private String target;
    private boolean directed;
    private float weight;

    /**
     *
     * @param source
     * @param target
     * @param directed
     */
    public Edge(String source, String target, boolean directed) {
        this.source = source;
        this.target = target;
        this.directed = directed;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLabel() {
        return label;
    }

    /**
     *
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     *
     * @return
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     */
    public String getTarget() {
        return target;
    }

    /**
     *
     * @param target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     *
     * @return
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     *
     * @return
     */
    public String isDirectedToString() {
        if (directed) {
            return "directed";
        } else {
            return "undirected";
        }
    }

    /**
     *
     * @param directed
     */
    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    /**
     *
     * @return
     */
    public float getWeight() {
        return weight;
    }

    /**
     *
     * @param weight
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.source);
        hash = 59 * hash + Objects.hashCode(this.target);
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Edge other = (Edge) obj;
        if (directed) {
            if (!Objects.equals(this.source, other.source)) {
                return false;
            }
            if (!Objects.equals(this.target, other.target)) {
                return false;
            }
        } else {
            if (!Objects.equals(this.source, other.source) & !Objects.equals(this.source, other.target)) {
                return false;
            }
            if (!Objects.equals(this.target, other.target) & !Objects.equals(this.target, other.source)) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Edge)){
            return -1;
        }
        Edge eo = (Edge)o;
        return Math.round((this.weight - eo.weight)*1000);
    }
}
