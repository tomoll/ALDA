/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Tobi, Schrissi
 */
public class GraphTraversion {

    static <V> List<V> depthFirstSearch(Graph<V> g, V s) {
        List<V> list = new LinkedList<>();

        list = depthFirstSearch(g, s, list);

        return list;
    }

    static <V> List<V> depthFirstSearch(Graph<V> g, V s, List<V> list) {
        list.add(s);
        List<V> adjacent = g.getAdjacentVertexList(s);
        for (V w : adjacent) {
            if (!list.contains(w)) {
                depthFirstSearch(g, w, list);
            }
        }
        return list;
    }

    static <V> List<V> breadthFirstSearch(Graph<V> g, V s) {
        List<V> list = new LinkedList<>();

        list = breadthFirstSearch(g, s, list);

        return list;
    }

    static <V> List<V> breadthFirstSearch(Graph<V> g, V s, List<V> list) {
        Queue<V> q = new LinkedList<>();
        q.add(s);
        while (q.size() != 0) {
            s = q.poll();
            if (list.contains(s)) {
                continue;
            }
            list.add(s);
            List<V> adjacent = g.getAdjacentVertexList(s);
            for (V w : adjacent) {
                if (!list.contains(w)) {
                    q.add(w);
                }
            }

        }
        return list;
    }

    static <V> List<V> topologicalSort(DirectedGraph<V> g) {
        List<V> ts = new LinkedList<>();
        int[] inDegree = new int[g.getNumberOfVertexes()];
        Queue<V> q = new LinkedList<>();
        List<V> vl = g.getVertexList();
        int i = 0;
        int j = 0;
        List<V> temp = new LinkedList<>();

        for (V v : g.getVertexList()) {
            inDegree[i] = g.getInDegree(v);
            if (inDegree[i] == 0) {
                q.add(v);
            }
            i++;
        }

        while (q.size() != 0) {
            V v = q.poll();
            ts.add(v);
            temp = g.getSuccessorVertexList(v);

            for (V w : temp) {
                j = 0;
                for (V t : vl) {
                    if (t == w) {
                        break;
                    }
                    j++;

                }
                if (--inDegree[j] == 0) {
                    q.add(w);
                }

            }

        }

        if (ts.size() != g.getNumberOfVertexes()) {
            throw new IllegalStateException("Zyklus gefunden!");
        } else {
            return ts;

        }

    }

}
