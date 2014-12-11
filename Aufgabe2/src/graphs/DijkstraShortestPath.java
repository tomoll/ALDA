package graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Tobi
 */
public class DijkstraShortestPath<V> {

    private final Graph<V> graph;
    private boolean success;
    private List<V> shortestPath;
    private double weight;

    public DijkstraShortestPath(Graph<V> g) {
        this.graph = g;
        shortestPath = new LinkedList<>();
        success = false;
    }

    public boolean searchShortestPath(V s, V g) {

        V x = null;
        Queue<V> kl = new LinkedList<>();
        HashMap<V, V> previous = new HashMap<>();
        HashMap<V, Double> distance = new HashMap<>();

        for (V v : graph.getVertexList()) {
            previous.put(v, null);
            distance.put(v, Double.MAX_VALUE);
        }

        for (V v : graph.getVertexList()) {
            if (v.equals(s)) {
                distance.put(v, 0.0);
            }
        }

        
        /*List<V> adjacent = graph.getAdjacentVertexList(s);
         for(V v : adjacent){
         kl.add(v);
         }*/

        kl.add(s);
        while (!kl.isEmpty()) {
            double min = Double.MAX_VALUE;
            for (V v : kl) {
                if (distance.get(v) < min) {
                    min = distance.get(v);
                    x = v;
                }
            }
            kl.remove(x);

            /*if (shortestPath.contains(x)) {
                continue;
            }
            shortestPath.add(x);*/
            List<V> adjacent = graph.getAdjacentVertexList(x);
            for (V w : adjacent) {
                if (distance.get(w) == Double.MAX_VALUE) {
                    kl.add(w);

                }
                if (distance.get(x) + graph.getWeight(x, w) < distance.get(w)) {
                    //previous.remove(w);
                    previous.put(w, x);
                    distance.put(w, (distance.get(x) + graph.getWeight(x, w)));
                }
            }
            if (x == g) {
                shortestPath.add(x);

                do {
                    weight = weight + distance.get(x);
                    shortestPath.add(previous.get(x));
                    x = previous.get(x);
                } while (x != s);

                success = true;
                return success;
            }
        }

        return success;

    }

    public List<V> getShortestPath() {
        if (!success) {
            return null;
        }

        return shortestPath;
    }

    public double getDistance() {
        if (!success) {
            return Double.MAX_VALUE;
        }
        return weight;
    }

}
