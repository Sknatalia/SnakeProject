import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JFrame {


    private JButton STARTGAMEButton;
    private JButton preferencesButton;
    private JPanel menupanel;
    static JFrame MenuFrame = new JFrame();

    public Menu() {
        //main menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        MenuFrame.setTitle("Snake");
        MenuFrame.setResizable(false);
        MenuFrame.add(menupanel);
        MenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuFrame.setSize(600, 735);
        MenuFrame.setLocationRelativeTo(null);
        MenuFrame.setVisible(true);

        //scoreboard and game~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        STARTGAMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Scoreboard(MenuFrame);
                menupanel.setVisible(false);

            }
        });
        //preferences~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        preferencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PreferencesPage(MenuFrame);
                menupanel.setVisible(false);
            }
        });
    }
}
