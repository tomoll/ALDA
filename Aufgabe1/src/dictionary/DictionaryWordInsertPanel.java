package dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *
 * @author  Tobi, Chris
 */
public class DictionaryWordInsertPanel extends JPanel implements ActionListener {

    private final DicProxy<String,String> woerterBuch;
    private final JTextField tfInsertDeutsch;
    private final JTextField tfInsertEnglisch;
    private final JButton buttonEinfuegen;

    /**
     *
     * @param tb
     */
    public DictionaryWordInsertPanel(DicProxy<String,String> tb) {
        woerterBuch = tb;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.add(new JLabel("Deutsch"));
        panel1.add(new JLabel("Englisch"));


        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1));
        tfInsertDeutsch = new JTextField(null, 20);
        panel2.add(tfInsertDeutsch);
        tfInsertEnglisch = new JTextField(null, 20);
        panel2.add(tfInsertEnglisch);

        Border border = BorderFactory.createTitledBorder("Einfügen");
        this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(panel1);
        this.add(panel2);
        buttonEinfuegen = new JButton("Einfügen");
        this.add(buttonEinfuegen);
        buttonEinfuegen.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // ...
        Object source = e.getSource();
        if (source == buttonEinfuegen) {
            woerterBuch.getDic().insert((tfInsertDeutsch.getText()), (tfInsertEnglisch.getText()));
            JOptionPane.showMessageDialog(this, "gespeichert!");
            tfInsertDeutsch.setText(null);
            tfInsertEnglisch.setText(null);
        }
    }
}
