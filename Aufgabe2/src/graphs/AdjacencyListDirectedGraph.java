/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class AdjacencyListDirectedGraph<V> implements UndirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> adjacencyIn;
    HashMap<V, HashMap<V, Double>> adjacencyOut;

    public AdjacencyListDirectedGraph() {
        this.adjacencyIn = new HashMap<>();
        this.adjacencyOut = new HashMap<>();
    }

    @Override
    public int getDegree(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addVertex(V v) {
        if (!containsVertex(v)) {
            adjacencyIn.put(v, new HashMap<V, Double>());
            adjacencyOut.put(v, new HashMap<V, Double>());
            return true;
        }
        return false;
    }

    @Override
    public boolean addEdge(V v, V w) {
        try {
            if (containsVertex(v) && containsVertex(w) && (v != w)) {
                if (!containsEdge(v, w)) {
                    adjacencyOut.get(v).put(w, 1.0);
                    adjacencyIn.get(w).put(v, 1.0);
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
        try {
            if (containsVertex(v) && containsVertex(w) && (v != w)) {
                if (!containsEdge(v, w)) {
                    adjacencyOut.get(v).put(w, weight);
                    adjacencyIn.get(w).put(v, weight);
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
    public boolean containsVertex(V v) {
        return adjacencyOut.containsKey(v);
    }

    @Override
    public boolean containsEdge(V v, V w) {
        return adjacencyOut.get(v).containsKey(w);
    }

    @Override
    public double getWeight(V v, V w) {
        try {
            if (containsVertex(v) && containsVertex(w)) {
                if (!containsEdge(v, w)) {
                    return 0;
                }
                return adjacencyOut.get(v).get(w);
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
        return adjacencyOut.size();
    }

    @Override
    public int getNumberOfEdges() {
        int number = 0;

        for (V v : adjacencyOut.keySet()) {
            number += adjacencyOut.get(v).size();
        }
        return number;                                  //glaub des passt so ned gibt ja auch welche wo Kanten nur hingehn
    }

    @Override
    public List<V> getVertexList() {
        List<V> temp = new LinkedList<V>();
        for (V v : adjacencyOut.keySet()) {
            temp.add(v);
        }
        return temp;
    }

    @Override
    public List<Edge<V>> getEdgeList() {
        List<Edge<V>> temp = new LinkedList<Edge<V>>();         //blicks nemme hier aufgehört
        for (V v : adjacencyOut.keySet()) {
            if (adjacencyOut.get(v) != null || adjacencyIn.get(v)!=null) {                  //vielleicht auch mit isEmpty
                Edge temp1 = new Edge(v, adjacencyOut.get(v));
                if (!temp.contains(temp1) && !temp.contains(temp2)) {
                    temp.add(temp1);
                }
            }
        }
        return temp;
    }

    @Override
    public List<V> getAdjacentVertexList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
