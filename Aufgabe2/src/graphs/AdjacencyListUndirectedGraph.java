/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfVertexes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getNumberOfEdges() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<V> getVertexList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> getEdgeList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
