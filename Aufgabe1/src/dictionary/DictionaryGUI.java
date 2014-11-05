package dictionary;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.*;

/**
 *
 * @author  Tobi, Chris
 */
public class DictionaryGUI extends JFrame {

    private DicProxy<String, String> woerterBuch = new DicProxy<>(new SortedArrayDictionary<String, String>());

    /**
     *
     */
    
//    public void setImpl(){
//        this.woerterBuch = DictionaryMenuBar.dic;
//    }
    public DictionaryGUI() {
        // Dictionary anlegen:
        //woerterBuch = DictionaryMenuBar.dic;

        // Menuleiste einbauen:
        this.setJMenuBar(new DictionaryMenuBar(woerterBuch));

        // mainPanel mit Umrandung versehen und das
        // Einfuegen- und  SuchenLoeschenPanel einbauen:
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.setContentPane(mainPanel);
        mainPanel.add(new DictionaryWordInsertPanel(woerterBuch));
        mainPanel.add(new DictionarySearchErasePanel(woerterBuch));
        // Sonstige Eigenschaften des Hauptfenster setzen:
        this.setTitle("Dictionary");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Operation beim schliessen des Dictionarys
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                // OptionPane anzeigen
                int exit = JOptionPane.showConfirmDialog(
                        frame,
                        "Moechten Sie wirklich beenden?",
                        "beenden",
                        JOptionPane.YES_NO_OPTION);
                // Anwendung verlassen, Wann Ja ausgewählt
                if (exit == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        this.setMinimumSize(new Dimension(900, 400));
        this.pack();
        this.setVisible(true);
    }
    
    

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new DictionaryGUI();

        Dictionary<String, String> wb, zb, ub;
        Map<String, String> map = new TreeMap<>();
        Map<String, String> map2 = new HashMap<>();

        wb = new SortedArrayDictionary<>();

        wb.insert("lesen", "read");

        wb.insert("schreiben", "write");

        System.out.println(wb.toString());


        zb = new MapDictionary<>(map);

        zb.insert("saufen", "drink");

        zb.insert("essen", "eat");

        System.out.println(zb.toString());

        ub = new MapDictionary<>(map2);

        ub.insert("laufen", "run");
        ub.insert("kurt", "eugen");

        System.out.println(ub.toString());
        System.out.println("Hallo Schrissi :) hab den HashTree komplett fertig, Problem war \n"
                + "das wir die Strings mit == verglichen haben und ned equals :) \n"
                + "hab auch des Fenster vergrößert das die performance-Ausgabe passt\n"
                + "und die Performance für hash + Tree soweit vorbereitet\n"
                + "Also bei DictionarySearchErasePanel unten den Kommentar entfernen dann müsste es passen\n"
                + "ach und wenn du die performance testen willst den Pfad kontrollieren/ändern auch bei DictionaryErase...");

    }
}
