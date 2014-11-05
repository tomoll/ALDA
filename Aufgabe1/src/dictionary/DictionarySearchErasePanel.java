package dictionary;

import static dictionary.DictionaryMenuBar.dic;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author  Tobi, Chris
 */
public class DictionarySearchErasePanel<K, V> extends JPanel implements ActionListener {

    private final DicProxy<String,String> woerterBuch;
    private final JTextField tfdeutsch;
    private final JTextField tfenglisch;
    private final JButton buttonAnwenden;
    private final JComboBox auswBox;
    private final JTextArea ausFeld;

    /**
     *
     * @param tb
     */
    public DictionarySearchErasePanel(DicProxy<String,String> tb) {
        woerterBuch = tb;

        // Hauptpanel erstellen
        JPanel suchenLoeschen = new JPanel();
        suchenLoeschen.setBorder(BorderFactory.createTitledBorder("Suchen/Löschen"));
        suchenLoeschen.setLayout(new BoxLayout(suchenLoeschen, BoxLayout.X_AXIS));

        // JPanel einbauen für JLabel Name, Zusatz
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(new JLabel("Deutsch"));
        panel1.add(new JLabel("Englisch"));

        // JPanel für TextField Name, Zusatz
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        tfdeutsch = new JTextField();
        panel2.add(tfdeutsch);
        tfenglisch = new JTextField();
        panel2.add(tfenglisch);
        panel2.setPreferredSize(new Dimension(200, 40));

        // Input-Dialog mit ComboBox
        String options[] = {"Suchen", "Alle Anzeigen", "Löschen", "Performance"};
        // ComboBox auswBox erstellen
        auswBox = new JComboBox(options);
        // Standardwert auf Prefix-Suche (index 1 von options[])
        auswBox.setSelectedIndex(0);
        auswBox.addActionListener(this);

        // Anwenden Button erstellen
        buttonAnwenden = new JButton("Anwenden");
        buttonAnwenden.addActionListener(this);

        // Ausgabetextfeld erstellen
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.setBorder(BorderFactory.createTitledBorder("Ausgabe"));
        ausFeld = new JTextArea();
        ausFeld.setEditable(false);
        panel3.setPreferredSize(new Dimension(500, 500));
        panel3.add(ausFeld, BorderLayout.CENTER);
        JScrollPane jScrollPane = new JScrollPane(ausFeld);
        panel3.add(jScrollPane);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Panel suchenLoeschen zusammenbauen
        suchenLoeschen.add(panel1);
        suchenLoeschen.add(panel2);
        suchenLoeschen.add(auswBox);
        suchenLoeschen.add(buttonAnwenden);

        // Eigenschaften des Objekts festlegen
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(suchenLoeschen);
        this.add(panel3);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Object resultat;                                                                          

        if (source == buttonAnwenden) {
            ausFeld.setText(null);
            int index = auswBox.getSelectedIndex();
            // index 0 = Suchen
            if (index == 0) {
                resultat = woerterBuch.getDic().search(tfdeutsch.getText());
                if (resultat == null) {
                    // Eintrag nicht gefunden 
                    JOptionPane.showMessageDialog(this, "Eintrag nicht gefunden");
                } else {
                    // Eintrag gefunden, Ausgabe in ausFeld
                    ausFeld.setText(resultat.toString());                   //ausFeld.setText(resultat.toString());
                }
                // index 1 = alles anzeigen    
            } else if (index == 1) {
                ausFeld.setText(woerterBuch.getDic().toString());

                // index 2 = Löschen    
            } else if (index == 2) {

                if (woerterBuch.getDic().remove(tfdeutsch.getText()) != null) {
                    // Löschen  erfolgreich
                    JOptionPane.showMessageDialog(this, "Löschen war erfolgreich");
                } else {
                    // Löschen nicht erfolgreich
                    JOptionPane.showMessageDialog(this, "Löschen war nicht erfolgreich!");
                }
            } else {
                String klDat = "/Users/Tobi/HTWG/3.Semester/Algorithmen und../"
                        + "Aufgaben/Aufgabe1/src/dictionary/dtengl8000.txt";
                String grDat = "/Users/Tobi/HTWG/3.Semester/Algorithmen und.."
                        + "/Aufgaben/Aufgabe1/src/dictionary/dtengl16000.txt";

                StringBuilder sb = new StringBuilder();
                sb.append("SAD \t\tHashDic \t\tTreeDic \t\tHashMap \t\tTreeMap \n");
                
                String str1;
                Dictionary[] messung1 = new Dictionary[5];                  
                messung1[0] = new SortedArrayDictionary<>();
                messung1[1] = new HashDictionary<>();
                messung1[2] = new TreeDictionary<>();                    
                messung1[3] = new MapDictionary<>(new HashMap());          
                messung1[4] = new MapDictionary<>(new TreeMap());           
                
                
                
               
                

                for (Dictionary d : messung1) {
                    sb.append(Performances.aufbau(klDat, d));
                    sb.append("ms").append("\t\t");
                }
                sb.append ("\n");
                
                 for (Dictionary d : messung1) {
                    sb.append(Performances.erfAuf(klDat, d));
                    sb.append("ms").append("\t\t");
                }
                sb.append ("\n");
                
                 for (Dictionary d : messung1) {
                    sb.append(Performances.schAuf(klDat, d));
                    sb.append("ms").append("\t\t");
                }
                sb.append ("\n\n");
               
                
 

                

                StringBuilder sb2 = new StringBuilder();
                sb2.append("SAD \t\tHashDic \t\tTreeDic \t\tHashMap \t\tTreeMap \n");

                Dictionary[] messung2 = new Dictionary[5];                      
                messung2[0] = new SortedArrayDictionary<>();
                messung2[1] = new HashDictionary<>();
                messung2[2] = new TreeDictionary<>();
                messung2[3] = new MapDictionary<>(new HashMap());
                messung2[4] = new MapDictionary<>(new TreeMap());
                
               
                
                
                for (Dictionary d : messung2) {
                    sb2.append(Performances.aufbau(grDat, d));
                    sb2.append("ms").append("\t\t");
                }
                sb2.append ("\n");
                
                 for (Dictionary d : messung2) {
                    sb2.append(Performances.erfAuf(grDat, d));
                    sb2.append("ms").append("\t\t");
                }
                sb2.append ("\n");
                
                 for (Dictionary d : messung2) {
                    sb2.append(Performances.schAuf(grDat, d));
                    sb2.append("ms").append("\t\t");
                }
                sb2.append ("\n");
                sb.append(sb2);
                str1 = sb.toString();

                ausFeld.setText(str1);
                
                
            }
        }
    }
}
