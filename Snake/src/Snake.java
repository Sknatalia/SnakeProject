import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class Snake extends JPanel implements KeyListener, ActionListener {


    //X Y~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static final int WIDTH = 470, HEIGHT = 700;

    private final int[] snakelengthX = new int[210];
    private final int[] snakelengthY = new int[245];


    //directions~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;


    //snake and snack~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private ImageIcon snakeheadup;
    private ImageIcon snakeheaddown;
    private ImageIcon snakeheadright;
    private ImageIcon snakeheadleft;
    private ImageIcon snack;


    //Colors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    Color Rust = new Color(162,0,0);
    Color Bronze = new Color(163,81,0);
    Color Gold = new Color(162, 162, 0);
    Color Olive = new Color(63, 102, 0);
    Color Jade = new Color(8, 132, 70);
    Color Teal = new Color(0, 140, 140);
    Color Cerulean = new Color(0, 63, 131);
    Color Indigo = new Color(0, 27, 204);
    Color Purple = new Color(99, 23, 191);
    Color Violet = new Color(106, 0, 106);
    Color Fuchsia = new Color(154, 0, 76);

    //convert string from combobox to color
    private Color colorconventer(String ChoosenColor) {
        HashMap<String, Color> map = new HashMap<String, Color>();
        map.put("Rust", Rust);
        map.put("Bronze", Bronze);
        map.put("Gold", Gold);
        map.put("Olive", Olive);
        map.put("Jade", Jade);
        map.put("Teal", Teal);
        map.put("Cerulean", Cerulean);
        map.put("Indigo", Indigo);
        map.put("Purple", Purple);
        map.put("Violet", Violet);
        map.put("Fuchsia", Fuchsia);


        Color ChoosenColorConverted = map.get(ChoosenColor);

        return ChoosenColorConverted;
    }

    //converted color for snake
    Color ChoosenColor = colorconventer(PreferencesPage.setcolor2);

    //random snack~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private Random random = new Random();
    private int randomSnackX = random.nextInt(WIDTH / 35);
    private int randomSnackY = random.nextInt(HEIGHT / 35);

    //Game rules~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static int snakelength = 2;
    private final Timer timer;
    private int delay = speed();
    private boolean moves =false;
    private boolean GameOver = false;
    private int score = snakelength-2;

    // for game resets after clicking menu
    public void checklength(){
        snakelength =  2;
    }

    private int speed(){
        String Choosenspeed = PreferencesPage.setspeed;
        if(Choosenspeed == "Slow"){
            delay = 125;
        }
        if(Choosenspeed == "Medium"){
            delay = 100;
        }
        if(Choosenspeed == "Fast"){
            delay = 75;
        }
        return delay;
    }

    //Score~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    static class Score {
        static int getScore(){
            int score = snakelength-2;
            return score;
        }
    }


    //Game over reset ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    void checkGameOver(Graphics g) {
        if(GameOver) {
            up = false;
            down = false;
            left = false;
            right = false;
            DrawBackground(g);
            snakelength = 2;
            StartingPosition(g);
        }
    }

    public Snake(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void StartingPosition(Graphics g){
        //snake starting position~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        snakelengthX[0] = 210;
        snakelengthX[1] = 210;
        snakelengthY[0] = 210;
        snakelengthY[1] = 245;
        snakeheadup = new ImageIcon(GetSnakeColor("up",ChoosenColor));
        snakeheadup.paintIcon(this, g, snakelengthX[0], snakelengthY[0]);
        g.setColor(ChoosenColor);
        g.fillRect(snakelengthX[1], snakelengthY[1],  35, 35);

    }
//Draw Background~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void DrawBackground (Graphics g){
        for(int i = 0; i < WIDTH /35;i++){
            for(int j = 0; j < HEIGHT /35;j++) {
                if ((i + j) % 2 != 0) {
                    g.setColor(new Color(191, 181, 175));
                } else {
                    g.setColor(new Color(236, 226, 208));
                }
                g.fillRect((i * 35), (j * 35),  35, 35);
            }
        }
    }


    //snake color~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private static BufferedImage colorImage(BufferedImage img, Color old, Color new1) {

        final int oldRGB = old.getRGB();
        final int newRGB = new1.getRGB();
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                if (img.getRGB(x, y) == oldRGB)
                    img.setRGB(x, y, newRGB);
            }
        }
        return img;
    }

    private BufferedImage GetSnakeColor(String part, Color newcolor){
        BufferedImage img = null;
        if(part == "up") {
            try {
                img = colorImage(ImageIO.read(new File("img/snakeheadup.png")), Jade, newcolor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(part == "down") {
            try {
                img = colorImage(ImageIO.read(new File("img/snakeheaddown.png")), Jade, newcolor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(part == "left") {
            try {
                img = colorImage(ImageIO.read(new File("img/snakeheadleft.png")), Jade, newcolor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(part == "right") {
            try {
                img = colorImage(ImageIO.read(new File("img/snakeheadright.png")), Jade, newcolor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        colorImage(img,Jade, newcolor);
        return img;

    }

    //Graphics ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void paint(Graphics g){

        requestFocus(true);

        DrawBackground(g);

        if (!moves){
            StartingPosition(g);
        }


        // Snake and snacks ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        for(int a = 0; a< snakelength; a++){

            int body = a;
            if(body==0)
                body = a+1;


            //check if lost
            checkGameOver(g);

            //spawn snack ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            snack = new ImageIcon("img/snack.png");
            if (snakelengthX[body] / 35 == randomSnackX && snakelengthY[body] / 35 == randomSnackY) {
                randomSnackX = random.nextInt(WIDTH / 35);
                randomSnackY = random.nextInt(HEIGHT / 35);
                snack = new ImageIcon("img/snack.png");
                snack.paintIcon(this, g, randomSnackX, randomSnackY);
            }
            else
                snack.paintIcon(this, g, randomSnackX * 35, randomSnackY * 35);

            // snake head and body~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if(a!=0){
                g.setColor(ChoosenColor);
                g.fillRect(snakelengthX[a], snakelengthY[a],  35, 35);
            }

            if(a==0 && up){
                snakeheadup = new ImageIcon(GetSnakeColor("up",ChoosenColor));
                snakeheadup.paintIcon(this, g, snakelengthX[a], snakelengthY[a]);
            }


            if(a==0 && down){
                snakeheaddown = new ImageIcon(GetSnakeColor("down",ChoosenColor));
                snakeheaddown.paintIcon(this, g, snakelengthX[a], snakelengthY[a]);
            }

            if(a==0 && left){
                snakeheadleft = new ImageIcon(GetSnakeColor("left",ChoosenColor));
                snakeheadleft.paintIcon(this, g, snakelengthX[a], snakelengthY[a]);
            }

            if(a==0 && right){
                snakeheadright = new ImageIcon(GetSnakeColor("right",ChoosenColor));
                snakeheadright.paintIcon(this, g, snakelengthX[a], snakelengthY[a]);
            }


            //ate a snack ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if(snakelengthX[0]/35== randomSnackX  && snakelengthY[0]/35 == randomSnackY) {
                randomSnackX = random.nextInt(WIDTH / 35);
                randomSnackY = random.nextInt(HEIGHT / 35);
                snakelength = snakelength + 1;
            }

            //game over condition~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            if(snakelengthX[0] == snakelengthX[body] && snakelengthY[0] == snakelengthY[body]) {
                GameOver = true;
            }
        }
    }


    //Movement ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        moves =true;
        if (right) {
            validate ();
            System.arraycopy(snakelengthY, 0, snakelengthY, 1, snakelength - 1 + 1);
            for (int i = snakelength; i >= 0; i--) {
                if (i == 0) {
                    snakelengthX[i] = snakelengthX[i] + 35;
                } else
                    snakelengthX[i] = snakelengthX[i - 1];
                if (snakelengthX[i] > 420)
                    snakelengthX[i] = 0;
            }
            repaint();
        }
        if (left) {
            validate ();
            System.arraycopy(snakelengthY, 0, snakelengthY, 1, snakelength - 1 + 1);
            for (int i = snakelength; i >= 0; i--) {
                if (i == 0) {
                    snakelengthX[i] = snakelengthX[i] - 35;
                } else
                    snakelengthX[i] = snakelengthX[i - 1];
                if (snakelengthX[i] < 0)
                    snakelengthX[i] = 420;
            }
            repaint();
        }

        if (down) {
            validate ();
            System.arraycopy(snakelengthX, 0, snakelengthX, 1, snakelength - 1 + 1);
            for (int i = snakelength; i >= 0; i--) {
                if (i == 0) {
                    snakelengthY[i] = snakelengthY[i] + 35;
                } else
                    snakelengthY[i] = snakelengthY[i - 1];
                if (snakelengthY[i] > 665)
                    snakelengthY[i] = 0;
            }
            repaint();
        }
        if (up) {
            validate ();
            System.arraycopy(snakelengthX, 0, snakelengthX, 1, snakelength - 1 + 1);
            for (int i = snakelength; i >= 0; i--) {
                if (i == 0) {
                    snakelengthY[i] = snakelengthY[i] - 35;
                } else
                    snakelengthY[i] = snakelengthY[i - 1];
                if (snakelengthY[i] < 0)
                    snakelengthY[i] = 665;
            }
            repaint();
        }

    }

    //Key pressed events~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP && !down || key == KeyEvent.VK_W && !down){
            if(GameOver)
                GameOver = false;
            up = true;
            left = false;
            right = false;
        }
        if(key == KeyEvent.VK_DOWN && !up || key == KeyEvent.VK_S && !up){
            if(GameOver)
                GameOver = false;
            down = true;
            left = false;
            right = false;
        }
        if(key == KeyEvent.VK_LEFT && !right || key == KeyEvent.VK_A && !right){
            if(GameOver)
                GameOver = false;
            up = false;
            down = false;
            left = true;
        }

        if(key == KeyEvent.VK_RIGHT && !left || key == KeyEvent.VK_D && !left){
            if(GameOver)
                GameOver = false;
            up = false;
            down = false;
            right = true;
            moves =true;
        }

    }

    // does nothing
    @Override
    public void keyReleased(KeyEvent e) {

    }
    // does nothing
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
