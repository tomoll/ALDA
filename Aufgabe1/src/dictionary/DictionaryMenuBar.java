package dictionary;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Tobi, Chris
 */
public class DictionaryMenuBar
        extends JMenuBar implements ActionListener {

    public static DicProxy<String, String> dic;
    private final JMenu datei;
    private final JMenuItem lesen;
    private final JMenuItem beenden;
    private JFileChooser fc;
    private final JMenu implArt;
    private final JMenuItem sortedArray;
    private final JMenuItem hash;
    private final JMenuItem tree;
    private final JMenuItem mapHash;
    private final JMenuItem mapTree;
    private final JMenuItem map;

    /**
     *
     * @param tb
     */
    public DictionaryMenuBar(DicProxy<String, String> tb) {
        dic = tb;
//
//        // MenuBar anlegen
//        this.menuBar = new JMenuBar();

        // Menue anlegen
        datei = new JMenu("Datei");

        // Menue in menuBar einbauen
        // Unterpunkte in Menue anlegen  
        lesen = new JMenuItem("Wörterbuch lesen...");
        lesen.addActionListener(this);

        beenden = new JMenuItem("Wörterbuch beenden");
        beenden.addActionListener(this);

        datei.add(lesen);
        datei.add(new JSeparator());
        datei.add(beenden);
        // Menue hinzufuegen
        this.add(datei);

        implArt = new JMenu("ImplArt");

        sortedArray = new JRadioButtonMenuItem("SortedArray");
        sortedArray.addActionListener(this);
        hash = new JRadioButtonMenuItem("Hash");
        hash.addActionListener(this);
        tree = new JRadioButtonMenuItem("Tree");
        tree.addActionListener(this);
        mapHash = new JRadioButtonMenuItem("MapHash");
        mapHash.addActionListener(this);
        mapTree = new JRadioButtonMenuItem("MapTree");
        mapTree.addActionListener(this);

        sortedArray.setSelected(true);
        ButtonGroup group = new ButtonGroup();
        group.add(sortedArray);
        group.add(hash);
        group.add(tree);
        group.add(mapHash);
        group.add(mapTree);
        map = new JMenu("Map");

        map.add(mapTree);
        map.add(mapHash);
        implArt.add(map);
        implArt.add(sortedArray);
        implArt.add(hash);
        implArt.add(tree);




        this.add(implArt);


    }

    public void actionPerformed(ActionEvent e) {

        // File Chooser anlegen
        this.fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        Object source = e.getSource();
        if (source == lesen) {
            read();
        } else if (source == mapHash) {
            Map<String, String> map = new HashMap<>();
            dic.setDic(new MapDictionary<>(map));
        } else if (source == mapTree) {
            Map<String, String> map = new TreeMap<>();
            dic.setDic(new MapDictionary<>(map));
        } else if (source == sortedArray) {
            dic.setDic(new SortedArrayDictionary<String, String>());
        } else if (source == tree) {
            dic.setDic(new TreeDictionary<String, String>());
        } else if (source == hash) {
            dic.setDic(new HashDictionary<String, String>());
        } else {
            exit();
        }
    }

    private void read() {
        // Dialog zum Datei öffnen anzeigen
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            LineNumberReader in = null;
            try {
                in = new LineNumberReader(new FileReader(file));
                String line;
                while ((line = in.readLine()) != null) {
                    String[] sf = line.split(" ");
                    if (sf.length == 1) {
                        dic.getDic().insert(sf[0], sf[1]); // leerer Zusatz
                    } else if (sf.length == 2) {
                        dic.getDic().insert(sf[0], sf[1]);
                    }
                }
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     */
    public void exit() {
        // OptionPane anzeigen
        int exit = JOptionPane.showConfirmDialog(
                this,
                "Moechten Sie wirklich beenden?",
                "beenden",
                JOptionPane.YES_NO_OPTION);
        // Anwendung verlassen, Wann Ja ausgewählt
        if (exit == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
