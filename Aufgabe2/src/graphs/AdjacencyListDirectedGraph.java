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
 * @author Tobi und schrissi
 * @param <V>
 */
public class AdjacencyListDirectedGraph<V> implements DirectedGraph<V> {

    HashMap<V, HashMap<V, Double>> adjacencyIn;
    HashMap<V, HashMap<V, Double>> adjacencyOut;

    public AdjacencyListDirectedGraph() {
        this.adjacencyIn = new HashMap<>();
        this.adjacencyOut = new HashMap<>();
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
        return number;
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
        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        for (V v : adjacencyOut.keySet()) {
            if (adjacencyOut.get(v) != null) {
                HashMap<V, Double> map = adjacencyOut.get(v);
                for (V w : map.keySet()) {
                    Edge temp1 = new Edge(v, w, map.get(w));
                    temp.add(temp1);

                }
            }
        }
        return temp;
    }

    @Override
    public List<V> getAdjacentVertexList(V v) {
        List<V> temp = new LinkedList<V>();
        try {
            if (adjacencyOut.containsKey(v)) {
                for (V w : adjacencyOut.get(v).keySet()) {
                    temp.add(w);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;

    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {

        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        try {
            if (adjacencyOut.containsKey(v)) {
                for (V w : adjacencyOut.get(v).keySet()) {
                    Edge temp1 = new Edge(v, w, adjacencyOut.get(v).get(w));
                    temp.add(temp1);
                }
                return temp;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

    @Override
    public int getInDegree(V v) {
        int i = 0;

        try {
            if (adjacencyIn.containsKey(v)) {
                for (V w : adjacencyIn.get(v).keySet()) {
                    i++;
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return i;

    }

    @Override
    public int getOutDegree(V v) {
        int i = 0;

        try {
            if (adjacencyOut.containsKey(v)) {
                for (V w : adjacencyOut.get(v).keySet()) {
                    i++;
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return i;
    }

    @Override
    public List<V> getPredecessorVertexList(V v) {
        List<V> temp = new LinkedList<V>();
        try {
            if (adjacencyIn.containsKey(v)) {
                for (V w : adjacencyIn.get(v).keySet()) {
                    temp.add(w);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

    @Override
    public List<V> getSuccessorVertexList(V v) {
        List<V> temp = new LinkedList<V>();
        try {
            if (adjacencyOut.containsKey(v)) {
                for (V w : adjacencyOut.get(v).keySet()) {
                    temp.add(w);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

    @Override
    public List<Edge<V>> getOutgoingEdgeList(V v) {

        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        try {
            if (adjacencyOut.containsKey(v)) {
                for (V w : adjacencyOut.get(v).keySet()) {
                    Edge temp1 = new Edge(v, w, adjacencyOut.get(v).get(w));
                    temp.add(temp1);
                }
                return temp;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

    @Override
    public List<Edge<V>> getIncomingEdgeList(V v) {
        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        try {
            if (adjacencyIn.containsKey(v)) {
                for (V w : adjacencyIn.get(v).keySet()) {
                    Edge temp1 = new Edge(w, v, adjacencyIn.get(v).get(w));
                    temp.add(temp1);
                }
                return temp;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

}
