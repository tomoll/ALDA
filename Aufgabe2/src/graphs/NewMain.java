/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import static graphs.GraphTraversion.*;
import java.util.LinkedList;
import java.util.List;
import graphs.Methoden;
import java.awt.Color;
import java.io.IOException;
import sim.SYSimulation;

/**
 *
 * @author Tobi
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Listenobjekte erstellen
        Graph ugr = new AdjacencyListUndirectedGraph();
        Graph dgr = new AdjacencyListDirectedGraph();
        AdjacencyListDirectedGraph anziehen = new AdjacencyListDirectedGraph();
        Graph scotland = new AdjacencyListUndirectedGraph();
        Graph<Integer> scotlandKompl = new AdjacencyListUndirectedGraph();

        //Simulation vorbereiten
        SYSimulation sim;
        try {
            sim = new SYSimulation();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (int i = 1; i < 200; i++) {
            scotland.addVertex(i);
            scotlandKompl.addVertex(i);

        }

        //alle Taxiverbindungen einlesen
        try {
            Methoden.einlesen("Taxi", scotland);
        } catch (IOException e) {
            return;
        }

        //alle Verbindungen mit Gewicht einlesen
        try {
            Methoden.allesEinlesen(scotlandKompl);
        } catch (IOException e) {
            return;
        }

        //den verschiedenen Graphen Punkte und Kanten zuweisen
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

        anziehen.addEdge("Strümpfe", "Hose");
        anziehen.addEdge("Hose", "Hemd");
        anziehen.addEdge("Hose", "Gürtel");
        anziehen.addEdge("Hemd", "Pullover");
        anziehen.addEdge("Gürtel", "Mantel");
        anziehen.addEdge("Pullover", "Mantel");
        anziehen.addEdge("Mantel", "Schal");
        anziehen.addEdge("Mantel", "Handschuhe");
        anziehen.addEdge("Schal", "Mütze");
        anziehen.addEdge("Handschuhe", "Mütze");
        anziehen.addEdge("Mütze", "Schuhe");
        anziehen.addEdge("Unterhemd", "Hose");
        anziehen.addEdge("Unterhose", "Hose");

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

        //Ausgabe ungerichtete tiefensuche
        System.out.println("Ungerichtete Tiefensuche");
        List temp1 = new LinkedList();
        temp1 = depthFirstSearch(ugr, "Heimat");
        for (Object s : temp1) {
            System.out.println(s);
        }
        System.out.println("");

        //Ausgabe ungerichtete breitensuche
        System.out.println("Ungerichtete Breitensuche");
        List temp2 = new LinkedList();
        temp2 = breadthFirstSearch(ugr, "Heimat");
        for (Object s : temp2) {
            System.out.println(s);
        }
        System.out.println("");

        //Ausgabe gerichtete tiefensuche
        System.out.println("Gerichtete Tiefensuche");
        List temp3 = new LinkedList();
        temp3 = depthFirstSearch(dgr, "Heimat");
        for (Object s : temp3) {
            System.out.println(s);
        }
        System.out.println("");

        //Ausgabe gerichtete breitensuche
        System.out.println("Gerichtete Breitensuche");
        List temp4 = new LinkedList();
        temp4 = breadthFirstSearch(dgr, "Heimat");
        for (Object s : temp4) {
            System.out.println(s);
        }

        System.out.println("");

        //Ausgabe gerichtete topologische suche
        System.out.println("Gerichtete Topologiesuche");
        List temp5 = new LinkedList();
        temp5 = topologicalSort(anziehen);
        for (Object s : temp5) {
            System.out.println(s);
        }

        System.out.println("");
        //Ausgabe ungerichtete breitensuche Scotland 
        System.out.println("Ungerichtete Breitensuche ScotlandYard");
        sim.startSequence("Breitensuche");
        List<Integer> breitensuche = new LinkedList<>();
        breitensuche = breadthFirstSearch(scotland, 1);
        List besuchtbr = new LinkedList();
        for (int s : breitensuche) {
            sim.visitStation(s);
        }
        for (int s : breitensuche) {
            besuchtbr.add(s);
            List<Integer> adjacentVertexes = new LinkedList<>();           //sim.drive()
            adjacentVertexes = scotland.getAdjacentVertexList(s);
            for (int v : adjacentVertexes) {
                if (!besuchtbr.contains(v)) {
                    sim.drive(s, v, Color.YELLOW);
                    besuchtbr.add(v);
                }
            }
            System.out.println(s);
        }
        sim.stopSequence();
        System.out.println("");

        //Ausgabe ungerichtete tiefensuche Scotland 
        System.out.println("Ungerichtete Tiefensuche ScotlandYard");
        sim.startSequence("Tiefensuche");
        List<Integer> tiefensuche = new LinkedList<>();
        tiefensuche = depthFirstSearch(scotland, 1);
        List besuchtti = new LinkedList();
        for (int s : tiefensuche) {
            sim.visitStation(s);
        }
        for (int s : tiefensuche) {
            besuchtti.add(s);
            List<Integer> adjacentVertexes = new LinkedList<>();           //sim.drive()
            adjacentVertexes = scotland.getAdjacentVertexList(s);
            for (int v : adjacentVertexes) {
                if (!besuchtti.contains(v)) {
                    sim.drive(s, v, Color.YELLOW);
                    besuchtti.add(v);
                }
            }
            System.out.println(s);
        }

        sim.stopSequence();

        //Ausgabe billigste Fahrt Scotland 
        int start = 1;
        int ziel = 199;
        System.out.println("Billigste Fahrt ScotlandYard");
        sim.startSequence("Dijkstra");
        DijkstraShortestPath dijki = new DijkstraShortestPath(scotlandKompl);
        boolean passt = dijki.searchShortestPath(start, ziel);
        System.out.println(dijki.getDistance());
        List<Integer> dijkstra = new LinkedList<>();
        dijkstra = dijki.getShortestPath();

        if (passt) {
            for (int s : dijkstra) {

                sim.visitStation(s);
                double d = Double.MAX_VALUE;
                for (int b : scotlandKompl.getAdjacentVertexList(s)) {
                    if (scotlandKompl.getWeight(ziel, s) < d) {
                        d = scotlandKompl.getWeight(ziel, s);
                    }

                }

                if (d == 5.0) {
                    sim.drive(ziel, s, Color.red);
                }
                if (d == 3.0) {
                    sim.drive(ziel, s, Color.yellow);
                }
                if (d == 2.0) {
                    sim.drive(ziel, s, Color.green);
                }
                ziel = s;

            }
        } else {
            System.out.println("FEHLER!!!");
        }
        sim.stopSequence();

    }

}
