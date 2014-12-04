/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import static graphs.GraphTraversion.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tobi
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdjacencyListUndirectedGraph ugr = new AdjacencyListUndirectedGraph();
        AdjacencyListDirectedGraph dgr = new AdjacencyListDirectedGraph();
        AdjacencyListDirectedGraph anziehen = new AdjacencyListDirectedGraph();

        ugr.addVertex("Heimat");
        ugr.addVertex("Caspa");
        ugr.addVertex("Klimpi");
        ugr.addVertex("Shooters");
        ugr.addVertex("BaBaLu");
        ugr.addVertex("Destille");
        ugr.addVertex("Bett");
        ugr.addVertex("Speien");

        dgr.addVertex("Heimat");
        dgr.addVertex("Caspa");
        dgr.addVertex("Klimpi");
        dgr.addVertex("Shooters");
        dgr.addVertex("BaBaLu");
        dgr.addVertex("Destille");
        dgr.addVertex("Bett");
        dgr.addVertex("Speien");
        
        anziehen.addVertex("Strümpfe");
        anziehen.addVertex("Schuhe");
        anziehen.addVertex("Hose");
        anziehen.addVertex("Unterhose");
        anziehen.addVertex("Unterhemd");
        anziehen.addVertex("Hemd");
        anziehen.addVertex("Gürtel");
        anziehen.addVertex("Pullover");
        anziehen.addVertex("Mantel");
        anziehen.addVertex("Schal");
        anziehen.addVertex("Handschuhe");
        anziehen.addVertex("Mütze");

        anziehen.addEdge("Strümpfe","Hose");
        anziehen.addEdge("Unterhose", "Hose");
        anziehen.addEdge("Unterhemd", "Hose");
        anziehen.addEdge("Hose", "Hemd");
        anziehen.addEdge("Hose", "Gürtel");
        anziehen.addEdge("Hemd", "Pullover");
        anziehen.addEdge("Gürtel", "Mantel");
        anziehen.addEdge("Pullover", "Mantel");
        anziehen.addEdge("Mantel","Schal");
        anziehen.addEdge("Mantel", "Handschuhe");
        anziehen.addEdge("Schal","Mütze");
        anziehen.addEdge("Handschuhe", "Mütze");
        
        ugr.addEdge("Heimat", "Caspa", 0.6);
        ugr.addEdge("Caspa", "Klimpi", 0.9);
        ugr.addEdge("Klimpi", "Shooters", 1.2);
        ugr.addEdge("Klimpi", "Bett", 2.0);
        ugr.addEdge("Shooters", "BaBaLu", 1.8);
        ugr.addEdge("Shooters", "Destille", 1.6);
        ugr.addEdge("Bett", "Speien", 0.5);
        ugr.addEdge("Speien", "Destille", 0.4);
        ugr.addEdge("Destille", "Heimat", 1.5);
        

        dgr.addEdge("Heimat", "Caspa", 0.6);
        dgr.addEdge("Caspa", "Klimpi", 0.9);
        dgr.addEdge("Klimpi", "Shooters", 1.2);
        dgr.addEdge("Klimpi", "Bett", 2.0);
        dgr.addEdge("Shooters", "BaBaLu", 1.8);
        dgr.addEdge("Shooters", "Destille", 1.6);
        dgr.addEdge("Bett", "Speien", 0.5);
        dgr.addEdge("Speien", "Destille", 0.4);
        dgr.addEdge("Destille", "Heimat", 1.5);
        
        
        

        List temp1 = new LinkedList();
        temp1 = depthFirstSearch(ugr, "Heimat");
        for (Object s : temp1) {
            System.out.println(s);
        }
        System.out.println("");
        List temp2 = new LinkedList();
        temp2 = breadthFirstSearch(ugr, "Heimat");
        for (Object s : temp2) {
            System.out.println(s);
        }
        System.out.println("");
        List temp3 = new LinkedList();
        temp3 = depthFirstSearch(dgr, "Heimat");
        for (Object s : temp3) {
            System.out.println(s);
        }
        System.out.println("");
        List temp4 = new LinkedList();
        temp4 = breadthFirstSearch(dgr, "Heimat");
        for (Object s : temp4) {
            System.out.println(s);
        }
        
        System.out.println("");
        List temp5 = new LinkedList();
        temp5 = topologicalSort(anziehen);
        for (Object s : temp5) {
            System.out.println(s);
        }

    }

}
