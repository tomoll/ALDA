/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Tobi und schrissi
 * @param <V>
 */
public class AdjacencyListUndirectedGraph<V> implements UndirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> adjacencyList;

    public AdjacencyListUndirectedGraph() {

        adjacencyList = new HashMap<V, HashMap<V, Double>>();

    }

    @Override
    public int getDegree(V v) {
        
    }

    @Override
    public boolean addVertex(V v) {
        if (!containsVertex(v)) {
            adjacencyList.put(v, new HashMap<V, Double>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(V v, V w) {
        try {
            if (containsVertex(v) && containsVertex(w) && (v != w)) {
                if (!containsEdge(v, w)) {
                    adjacencyList.get(v).put(w, 1.0);
                    
                    return true;
                }
                return false;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean addEdge(V v, V w, double weight) {
        if (!containsEdge(v, w)) {
            adjacencyList.get(v).put(w, weight);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        return adjacencyList.get(v).containsKey(w);
    }

    @Override
    public double getWeight(V v, V w) {
        try {
            if (containsVertex(v) && containsVertex(w)) {
                if (!containsEdge(v, w)) {
                    return 0;
                }
                return adjacencyList.get(v).get(w);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return -1;
        }
    }

    @Override
    public int getNumberOfVertexes() {
        return adjacencyList.size();
    }

    @Override
    public int getNumberOfEdges() {
        int number = 0;
        
        for (V v : adjacencyList.keySet()) { 
            number += adjacencyList.get(v).size();
        }
        return number/2;
    }

    @Override
    public List<V> getVertexList() {
        List<V> temp = new LinkedList<V>();
        for(V v : adjacencyList.keySet()){
            temp.add(v);
        }
        return temp;
    }

    @Override
    public List<Object> getEdgeList() {
        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        
        for(V v : adjacencyList.keySet()){
            if(adjacencyList.get(v).isEmpty()){                  //vielleicht auch mit isEmpty
                new Edge(adjacencyList.get(v), adjacencyList.)
                
            
            
            temp.add(v);
        }
        return temp;
    }

    @Override
    public List<V> getAdjacentVertexList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getIncidentEdgeList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
