// Layout used by the JPanel

import java.awt.BorderLayout;

// Define color of shapes
import java.awt.Color;

// Allows me to draw and render shapes on components
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.Rectangle;
// Will hold all of my Rock objects

import java.util.ArrayList;

// Runs commands after a given delay
import java.util.concurrent.ScheduledThreadPoolExecutor;

// Defines time units. In this case TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

// NEW Needed for sound to play

// Stores a predefined audio clip

import javax.sound.sampled.Clip;

// Can convert audio data to different playable formats

import javax.sound.sampled.AudioSystem;

// Works with streams of a definite length

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.*;

import javax.swing.*;

public class GameBoard extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	// Height and width of the game board
	
	public static int boardWidth = 1000;
	public static int boardHeight = 800;

	public static int numcars=5;
	
	// Used to check if a key is being held down
	
	public static boolean keyHeld = false;

	public static boolean moveRightKey = false;
	public static boolean moveLeftKey = false;
	public static boolean enterkeyPressed=false;
	
	// Gets the keycode for the key being held down
	
	public static int keyHeldCode;
	

	public static String thrustFile = "file:./src/thrust.au";
	public static String laserFile = "file:./src/laser.aiff";

	
	public static void main(String [] args)
    {
            new GameBoard();
            
    }
	
	public GameBoard()
    {
    	// Define the defaults for the JFrame
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("The Road");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Handles executing code based on keys being pressed
        
        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==87)
			    	{
					System.out.println("Forward");					
					keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
				    } 
			
				
					else if (e.getKeyCode()==83)
					{
			    	System.out.println("Backward");
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			  	    } 
			        else if (e.getKeyCode()==68)
			        {
			    	System.out.println("Move towards Right");			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	moveRightKey=true;			    	
			    	} 

			    	else if (e.getKeyCode()==65)
			    	{
			    	System.out.println("Move Left");			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	moveLeftKey=true;
			    	}
			    	else if (e.getKeyCode()==KeyEvent.VK_ENTER)
			    	{
			    		enterkeyPressed=true;
			    	}
				
							
												}


			
			public void keyReleased(KeyEvent e) 
					{
					keyHeld = false;
					if (e.getKeyCode()==68) moveRightKey=false;
					else if (e.getKeyCode()==65) moveLeftKey=false;
					else if (e.getKeyCode()==KeyEvent.VK_ENTER)
			    		{
			    		enterkeyPressed=false;
			    		}
					}
        	
        });
        
        
        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();
        this.add(gamePanel, BorderLayout.CENTER);       
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);        
		executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);       
        this.setVisible(true);
    }
	
	
	
	public static void playSoundEffect(String soundToPlay){
    	
    	// Pointer towards the resource to play
		URL soundLocation;
		try {
			soundLocation = new URL(soundToPlay);
		 
        	    // Stores a predefined audio clip
        	    Clip clip = null;
				
				// Convert audio data to different playable formats
				clip = AudioSystem.getClip();
				
				// Holds a stream of a definite length
        	    AudioInputStream inputStream;

				inputStream = AudioSystem.getAudioInputStream(soundLocation);

				// Make audio clip available for play
				clip.open( inputStream );
					
				// Define how many times to loop
				clip.loop(0);
				
				// Play the clip
	        	clip.start();
				} 
				
				catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    
        	    catch (UnsupportedAudioFileException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	    
        	    catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	
    }
		
	
}


 

class RepaintTheBoard2 implements Runnable
		{
 		GameBoard theBoard;
		public RepaintTheBoard2(GameBoard theBoard)
			{
			this.theBoard = theBoard;
			}
		@Override
		public void run() 
			{
			theBoard.repaint();
			}
		}

@SuppressWarnings("serial")



class GameDrawingPanel2 extends JComponent 
		{ 
		public static ArrayList<car> cars=new ArrayList<car>();	
		public static ArrayList<missile> missiles=new ArrayList<missile>();
		int width = GameBoard.boardWidth;
		int height = GameBoard.boardHeight;
	


		public GameDrawingPanel2() 
			{ 
			for (int i=0;i<GameBoard.numcars;i++)
			cars.add(new car(cars));		
			} 

		public void makeThatWeirdSound()
			{
				GameBoard.playSoundEffect(GameBoard.thrustFile);
			}
		public void makeThatShootingSound()
			{
				GameBoard.playSoundEffect(GameBoard.laserFile);
			}
	
		public void paint(Graphics g) { 
		
		
		Graphics2D graphicSettings = (Graphics2D)g; 
		AffineTransform identity = new AffineTransform();
		
		
		
		graphicSettings.setColor(Color.BLACK);
		graphicSettings.fillRect(0, 0, getWidth(), getHeight());
		
		// Set rendering rules
		
		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		
		// Set the drawing color to white
		
		graphicSettings.setPaint( Color.WHITE ); 
		

		
		if(GameBoard.moveRightKey){
			
			cars.get(0).moveRight();
			
		} else
			
					
		if(GameBoard.moveLeftKey){
				
			cars.get(0).moveLeft();
				
		} else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){
			

			cars.get(0).accelUp();
			
		}  else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 83){
			

			cars.get(0).accelDown();
			
		}
		if(GameBoard.enterkeyPressed)
		{
			missiles.add(new missile(cars.get(0).getXCenter(),cars.get(0).getYCenter()-10,cars.get(0).getYVelocity()   ));
		}
		for (int i=0;i<GameBoard.numcars;i++)

			{
		if(!(cars.get(i).onScreen)) continue;
		cars.get(i).move();		
		graphicSettings.setTransform(identity);		
		graphicSettings.translate(cars.get(i).getXCenter(),cars.get(i).getYCenter());		
		if(cars.get(i).onScreen)
		graphicSettings.draw(cars.get(i));
		if(i==0) continue;

		Rectangle zerobound=cars.get(0).getBounds();
		Rectangle ourbound=cars.get(i).getBounds();
		if(ourbound.intersects(zerobound))
				{	GameBoard.playSoundEffect(GameBoard.thrustFile);
					cars.get(0).setYVelocity(cars.get(i).getYVelocity());
					if(cars.get(i).getYCenter()<cars.get(0).getYCenter())
					{

						cars.get(0).escapeDown();
						System.out.println("collision happened decreaseing velocity");
					}
					else
					{
						cars.get(0).escapeUp();
						System.out.println("collision happened increaseing velocity");
					}
				}
			}
			//draw missiles if onscreen==true
		boolean destroy=true;
		boolean startcarx=true;
		for(car carx:cars)
			{
				if(startcarx){startcarx=false;continue;}
				if(carx.onScreen) 
				{
					destroy=false;
					break;
				}
			}
		if (destroy) makeThatShootingSound();
		destroy=true;
		for(missile missilex:missiles)
			{
				if(missilex.onScreen) 
				{
					destroy=false;
					break;
				}
			}
		if(destroy)
			missiles.clear();
		else
		{
		for(missile missilex:missiles)
			{
			if(!(missilex.onScreen)) continue;	
			missilex.move();
			graphicSettings.setTransform(identity);		
			graphicSettings.translate(missilex.getXCenter(),missilex.getYCenter());
			graphicSettings.draw(missilex);
			boolean startcar=true;
			for (car carx:cars)
				{	if (startcar)
						{
							startcar=false;
							continue;
						}
					if(!carx.onScreen) continue;
					Rectangle carbounds=carx.getBounds();
					Rectangle missilebounds=missilex.getBounds();
					if (carbounds.intersects(missilebounds))
						{
							carx.onScreen=false;
							missilex.onScreen=false;
							makeThatWeirdSound();
						}

				}
			//collision detection

			}
		}
			


		
		// Draw torpedos
		
		
		
		
	} 
	
	
}