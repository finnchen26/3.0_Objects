//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   
	public BufferStrategy bufferStrategy;
	public Image astroPic;




	public Image astroPic2;
	public Image asteroidPic;
	public Image background;
	public Image background2;
	public Image memePic;

	public Image astroPic3;
	public Image rip;




   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut astro;
	private Astronaut astro2;
	private Astronaut astro3;
	private Asteroid asteroid;
	private Asteroid asteroid2;
	private Asteroid asteroid3;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		astroPic = Toolkit.getDefaultToolkit().getImage("astroPic.png"); //load the picture
		astroPic2 = Toolkit.getDefaultToolkit().getImage("astroPic2.png");
		astroPic3 = Toolkit.getDefaultToolkit().getImage("astroPic3.png");
		asteroidPic = Toolkit.getDefaultToolkit().getImage("asteroid.png");
		asteroid = new Asteroid((int)(Math.random()*940), (int)(Math.random()*620));
		asteroid3 = new Asteroid((int)(Math.random()*940), (int)(Math.random()*620));
		asteroid2 = new Asteroid((int)(Math.random()*940), (int)(Math.random()*620));
		astro = new Astronaut((int)(Math.random()*940),(int)(Math.random()*620));
		background = Toolkit.getDefaultToolkit().getImage("background.jpeg");
		background2 = Toolkit.getDefaultToolkit().getImage("background2.jpeg");
		astro2 = new Astronaut((int)(Math.random()*940),(int)(Math.random()*620));
		astro3 = new Astronaut((int)(Math.random()*940), (int)(Math.random()*620));
		astro3.isAlive = false;
		astro3.isCrashing = false;
		asteroid.isAlive = false;
		asteroid2.isAlive = false;
		asteroid3.isAlive = false;
		memePic = Toolkit.getDefaultToolkit().getImage("meme.jpg");
		rip = Toolkit.getDefaultToolkit().getImage("rip.png");

	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects

		astro.wrap();
		astro2.bounce();

		asteroid.bounce();
		asteroid2.bounce();
		asteroid3.bounce();
		if(astro.rect.intersects(astro2.rect) && !astro.isCrashing && !astro2.isCrashing){ //"!" means "== false"
			System.out.println("Crash!");
			astro.height = astro.height + 50;
			astro.isCrashing = true;
			astro.isAlive = true;
			astro3.isAlive = true;
			astro2.isCrashing = true;
			asteroid.isAlive = true;
			asteroid2.isAlive = true;
			asteroid3.isAlive = true;

			astro3.xpos = astro.xpos;
			astro3.ypos = astro.ypos;
			astro3.rect = new Rectangle(astro3.xpos, astro3.ypos, astro3.width, astro3.height);



		}



		if (astro.rect.intersects(astro2.rect) == false){
			astro.isCrashing = false;
			astro2.isCrashing  = false;
		}

		if (astro3.isAlive ==true && (astro3.rect.intersects(asteroid.rect) == true && asteroid.isCrashing == false)|| astro3.isAlive ==true && (astro3.rect.intersects(asteroid2.rect) == true && !asteroid2.isCrashing)|| astro3.isAlive ==true && (astro3.rect.intersects(asteroid3.rect) == true && !asteroid3.isCrashing)){
			astro3.isCrashing = true;
			asteroid.isCrashing = true;
			asteroid2.isCrashing = true;
			asteroid3.isCrashing = true;
			System.out.println("dog has been hit");

		}

		if (astro3.rect.intersects(asteroid.rect) == false){
			asteroid.isCrashing = false;
		}

		if (astro3.rect.intersects(asteroid2.rect) == false){
			asteroid2.isCrashing = false;
		}

		if (astro3.rect.intersects(asteroid3.rect) == false){
			asteroid3.isCrashing = false;
		}





	}


	
   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
		g.drawImage(astroPic2, astro.xpos, astro.ypos, astro.width, astro.height, null);
		g.drawImage(astroPic, astro2.xpos, astro2.ypos, astro2.width, astro2.height, null);

		if(astro3.isAlive){
			g.drawImage(astroPic3, astro3.xpos, astro3.ypos, astro3.width, astro3.height, null);

		}

		if(asteroid.isAlive){
			g.drawImage(asteroidPic, asteroid.xpos, asteroid.ypos, asteroid.width, asteroid.height, null);

		}
		if(asteroid2.isAlive){
			g.drawImage(asteroidPic, asteroid2.xpos, asteroid2.ypos, asteroid2.width, asteroid2.height, null);

		}
		if(asteroid3.isAlive){
			g.drawImage(asteroidPic, asteroid3.xpos, asteroid3.ypos, asteroid3.width, asteroid3.height, null);

		}

		if ((astro3.rect.intersects(asteroid.rect) || astro3.rect.intersects(asteroid2.rect)  || astro3.rect.intersects(asteroid3.rect)  )) {
			g.drawImage(rip, 230, 100, 500, 100, null);
		}

		if (astro.height >= 300 && astro.height <= 350){
			g.drawImage(memePic, 230, 100, 500, 500, null);
		}

		g.dispose();

		bufferStrategy.show();
	}
}