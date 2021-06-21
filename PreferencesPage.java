import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreferencesPage extends JPanel{

    private JButton mainMenuButton;
    public JPanel PreferencesPanel;
    public JComboBox pickcolor;
    private JComboBox pickspeed;
    static JFrame PrefFrame = new JFrame();
    public static String setcolor2 = "Jade";
    public static String setspeed = "Medium";

    public PreferencesPage(JFrame frame) {
        //Options~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //Speed options
        pickspeed.addItem("Slow");
        pickspeed.addItem("Medium");
        pickspeed.addItem("Fast");

        //Snake Color options
        pickcolor.addItem("Rust");
        pickcolor.addItem("Bronze");
        pickcolor.addItem("Gold");
        pickcolor.addItem("Olive");
        pickcolor.addItem("Jade");
        pickcolor.addItem("Teal");
        pickcolor.addItem("Cerulean");
        pickcolor.addItem("Indigo");
        pickcolor.addItem("Purple");
        pickcolor.addItem("Violet");
        pickcolor.addItem("Fuchsia");

        frame.add(PreferencesPanel);
        frame.setVisible(true);

        //return to main menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(PreferencesPanel);
                new Menu();
            }
        });
        //choose options~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        pickcolor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setcolor2 = (String) pickcolor.getSelectedItem();
            }
        });
        pickspeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setspeed = (String) pickspeed.getSelectedItem();
            }
        });
    }
}
