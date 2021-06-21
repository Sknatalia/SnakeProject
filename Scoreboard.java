import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Scoreboard {

    private JPanel scoreboardpanel;
    private JButton mainMenuButton;

    public Scoreboard(JFrame frame) {

        frame.add(scoreboardpanel);
        scoreboardpanel.setOpaque(false);
        frame.setVisible(true);
        Snake snake = new Snake();
        frame.add(snake);
        snake.setOpaque(false);
        snake.setVisible(true);

        //come back to main menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(scoreboardpanel);
                snake.checklength();
                frame.remove(snake);
                new Menu();
            }
        });
        }
    }
