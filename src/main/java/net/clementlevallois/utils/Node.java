/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.utils;

/**
 *
 * @author C. Levallois
 */
public class Node {
    
    private String id;
    private String label;

    /**
     *
     * @param id
     * @param label
     */
    public Node(String id, String label) {
        this.id = id;
        this.label = label;
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
    
    
    
    
}
