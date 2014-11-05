/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Tobi, Chris
 * @param <K>
 * @param <V>
 */
public class Performances<K, V> {

    public static double aufbau(String s, Dictionary d) {
        long before = System.nanoTime();
        LineNumberReader in = null;
        try {
            in = new LineNumberReader(new FileReader(s));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                if (sf.length == 2) {
                    d.insert(sf[0], sf[1]);
                }
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }
        long after = System.nanoTime();
        double runningTimeMs = (after - before) / (double) 1000000;
        return runningTimeMs;
    }

    public static double erfAuf(String s, Dictionary d) {

        Set<String> liste = new TreeSet() {
        };
        LineNumberReader in = null;

        try {
            in = new LineNumberReader(new FileReader(s));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                liste.add(sf[0]);
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }

        long before = System.nanoTime();
        for (String str : liste) {
            d.search(str);

        }
        long after = System.nanoTime();
        double runningTimeMs = (after - before) / (double) 1000000;
        return runningTimeMs;
    }

    public static double schAuf(String s, Dictionary d) {

        Set<String> liste = new TreeSet() {
        };
        LineNumberReader in = null;

        try {
            in = new LineNumberReader(new FileReader(s));
            String line;
            while ((line = in.readLine()) != null) {
                String[] sf = line.split(" ");
                liste.add(sf[1]);
            }
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
        }

        long before = System.nanoTime();
        for (String str : liste) {
            d.search(str);

        }
        long after = System.nanoTime();
        double runningTimeMs = (after - before) / (double) 1000000;
        return runningTimeMs;
    }

}
