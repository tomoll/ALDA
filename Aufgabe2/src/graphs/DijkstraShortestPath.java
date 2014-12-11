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
    private LinkedList<V> shortestPath;
    private double weight;
    
    private Queue<V> kl;
    private HashMap<V, V> previous;
    private HashMap<V, Double> distance;

    public DijkstraShortestPath(Graph<V> g) {
        this.graph = g;
        shortestPath = new LinkedList<>();
        success = false;
    }

    public boolean searchShortestPath(V s, V g) {

        V x = null;
        kl = new LinkedList<>();
        previous = new HashMap<>();
        distance = new HashMap<>();

        for (V v : graph.getVertexList()) {
            previous.put(v, null);
            distance.put(v, Double.MAX_VALUE);
        }

        for (V v : graph.getVertexList()) {
            if (v.equals(s)) {
                distance.put(v, 0.0);
                break;
            }
        }

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

            if (x.equals(g)) {          //war davor if(x==g)
                shortestPath.add(x);
                weight = distance.get(x);
                System.out.println("1");

                do {
                    //weight = weight + distance.get(x);
                    shortestPath.add(previous.get(x));
                    x = previous.get(x);
                } while (x != s);
                                System.out.println("2");


                success = true;
                return success;
            }
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
                                    System.out.println("0");

                }
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
