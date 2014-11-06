/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.HashMap;

/**
 *
 * @author Tobi
 */
public class AdjacencyListDirectedGraph<V> implements UndirectedGraph<V>  {
    HashMap<V, HashMap<V,Double>> adjacencyInput;
    HashMap<V, HashMap<V,Double>> adjacencyOutput;

    
    public AdjacencyListDirectedGraph() {
        this.adjacencyInput = new HashMap<>();
        this.adjacencyOutput = new HashMap<>();
    }
    
    
}
