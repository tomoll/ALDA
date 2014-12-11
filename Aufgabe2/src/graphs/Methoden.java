/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author Tobi
 */
public class Methoden {

    public static void einlesen(String name, Graph g) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("/Users/Tobi/NetBeansProjects/ALDA/Aufgabe2/src/graphs/ScotlandYard.txt");
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader in = null;
        in = new LineNumberReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            String[] sf = line.split(" ");
            if (sf.length == 3) {
                int vertexOne = Integer.parseInt(sf[0]);
                int vertexTwo = Integer.parseInt(sf[1]);
                if (sf[2].equals(name)) {
                    g.addEdge(vertexOne, vertexTwo);
                }
            }
        }
        in.close();
        br.close();
    }

    public static void allesEinlesen(Graph g) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("/Users/Tobi/NetBeansProjects/ALDA/Aufgabe2/src/graphs/ScotlandYard.txt");
        BufferedReader br = new BufferedReader(fr);
        LineNumberReader in = null;
        in = new LineNumberReader(fr);
        String line;
        while ((line = in.readLine()) != null) {
            String[] sf = line.split(" ");
            if (sf.length == 3) {
                int vertexOne = Integer.parseInt(sf[0]);
                int vertexTwo = Integer.parseInt(sf[1]);
                if (sf[2].equals("Taxi")) {
                    g.addEdge(vertexOne, vertexTwo, 3.0);
                }
                if (sf[2].equals("Bus")) {
                    g.addEdge(vertexOne, vertexTwo, 2.0);
                }
                if (sf[2].equals("U-Bahn")) {
                    g.addEdge(vertexOne, vertexTwo, 5.0);
                }
            }
        }
        in.close();
        br.close();9
    }

}
