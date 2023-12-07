import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class AquariumGameApp implements Runnable{
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;
    public Image astroPic;

    public Image background;



    //Declare the objects used in the program
    //These are things that are made up of more than one variable type
    private BubbleTea bobaPic;
    private BubbleTea astro2;
    private BubbleTea astro3;


    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        AquariumGameApp ex = new AquariumGameApp();   //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    public AquariumGameApp() {

        setUpGraphics();

        //variable and objects
        //create (construct) the objects needed for the game and load up
        BubbleTea = Toolkit.getDefaultToolkit().getImage("boba.webp"); //load the picture
        astro = new Astronaut((int)(Math.random()*940),(int)(Math.random()*620));
        background = Toolkit.getDefaultToolkit().getImage("teabkg.jpeg");
        astro2 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*620));
        astro3 = new Astronaut((int)(Math.random()*940), (int)(Math.random()*620));
        astro.isAlive = false;

    }

}
