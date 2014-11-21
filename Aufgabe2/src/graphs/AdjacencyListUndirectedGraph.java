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
        int i = 1;
        return i;
        
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
        try {
            if (containsVertex(v) && containsVertex(w) && (v != w)) {
                if (!containsEdge(v, w)) {
                    adjacencyList.get(v).put(w, weight);
                    
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
    public List<Edge<V>> getEdgeList() {
        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        for(V v : adjacencyList.keySet()){
            if(adjacencyList.get(v)!=null){                  //vielleicht auch mit isEmpty
                Edge temp1 = new Edge(v,adjacencyList.get(v));
                Edge temp2 = new Edge(adjacencyList.get(v), v);
                if(!temp.contains(temp1) && !temp.contains(temp2)){
                    temp.add(temp1);
                }
            }
        }
        return temp;
    }

    @Override
    public List<V> getAdjacentVertexList(V v) {
        List<V> temp = new LinkedList<V>();
        try{
            if(adjacencyList.containsKey(v)){
                for(V w : adjacencyList.get(v).keySet()){
                    temp.add(w);
                }                
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e);
        }
        return temp;
    }

    @Override
    public List<Edge<V>> getIncidentEdgeList(V v) {
        List<Edge<V>> temp = new LinkedList<Edge<V>>();
        try{
            if(adjacencyList.containsKey(v)){
                //muss des von v aus nur die Knoten wo mit Kante verbunden ist oder wie???
                
                return temp;
                
            } else {
                throw new IllegalArgumentException();
            }
        } catch(IllegalArgumentException e){
            System.out.println(e);
        }
        return temp;

    }

}
