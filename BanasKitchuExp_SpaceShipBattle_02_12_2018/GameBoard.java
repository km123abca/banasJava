// Layout used by the JPanel

import java.awt.BorderLayout;

// Define color of shapes
import java.awt.Color;

// Allows me to draw and render shapes on components
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

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
	public static int boardHeight = 600;
	
	// Used to check if a key is being held down
	
	public static boolean keyHeld = false;
	
	// Gets the keycode for the key being held down
	
	public static int keyHeldCode;
	public static int counterr=0;
	public static boolean gamePause=false;
	public static int shipInt=1;
	public static int maxShips=3;
	
	// Holds every PhotonTorpedo I create
	
	public static ArrayList<PhotonTorpedo> torpedos = new ArrayList<PhotonTorpedo>();
	
	// NEW Location of sound files
	
	public static String thrustFile = "file:./src/thrust.au";
	public static String laserFile = "file:./src/laser.aiff";
	public static String explodeFile = "file:./src/explode.wav";
	
	public static void main(String [] args)
    {
            new GameBoard();
            
    }
	
	public GameBoard()
    {
    	// Define the defaults for the JFrame
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("SpaceShip Battle");
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
					
					// NEW play thrust noise when w is pressed
					
					playSoundEffect(thrustFile);
					
					keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
					
			    } 
				// Not currently used
				
					else if (e.getKeyCode()==83){
			    	System.out.println("Backward");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
				// Id the d key is pressed set keyHeld as if it
				// was being held down. This will cause the ship to 
				// constantly rotate. keyHeldCode stores the keyCode for d
			    
			    else if (e.getKeyCode()==68){
			    	System.out.println("Rotate Right");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    	
			    } 
			    
			    // Same thing is done here as was done with the last
				// 65 is the keyCode for a
			    
			    else if (e.getKeyCode()==65){
			    	System.out.println("Rotate Left");
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    }
				else if (e.getKeyCode()==32){
			    	
			    	
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    }
				// NEW Checks if Enter key is pressed ---------------
				
			    else if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	System.out.println("Shoot");
			    	
			    	// NEW play laser sound when enter is hit
			    	
			    	playSoundEffect(laserFile);
			    	
			    	// Creates a new torpedo and passes the ships nose position
			    	// so the torpedo can start there. Also passes the ships
			    	// rotation angle so the torpedo goes in the right direction
			    	
			    	torpedos.add(new PhotonTorpedo(GameDrawingPanel2.spaceShips.get(0).getShipNoseX(),
			    			GameDrawingPanel2.spaceShips.get(0).getShipNoseY(), 
			    			GameDrawingPanel2.spaceShips.get(0).getRotationAngle(),
			    			GameDrawingPanel2.spaceShips.get(0).getShipId()
			    			));
			    	
			    	System.out.println("RotationAngle " + GameDrawingPanel2.spaceShips.get(0).getRotationAngle());
			    	
			    }
				
			}

			// When the key is released this informs the code that
			// the key isn't being held down
			
			public void keyReleased(KeyEvent e) {
		
				keyHeld = false;
				if(e.getKeyCode()==32) GameBoard.gamePause=false;
				
			}
        	
        });
        
        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();

     // Make the drawing area take up the rest of the frame
        
        this.add(gamePanel, BorderLayout.CENTER);
        
        // Used to execute code after a given delay
        // The attribute is corePoolSize - the number of threads to keep in 
        // the pool, even if they are idle
        
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
		
        // Method to execute, initial delay, subsequent delay, time unit
        
		executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);
        
        // Show the frame
        
        this.setVisible(true);
    }
	
	// NEW Handles playing all sound effects
		public static void makeThatWeirdSound()
			{
				playSoundEffect(thrustFile);
			}
		public static void makeThatShootingSound()
			{
				playSoundEffect(laserFile);
			} 
		public static void makeThatExplodingSound()
			{
				playSoundEffect(explodeFile);
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


// Class implements the runnable interface
// By creating this thread we can continually redraw the screen
// while other code continues to execute

class RepaintTheBoard2 implements Runnable{

	GameBoard theBoard;
	
	public RepaintTheBoard2(GameBoard theBoard){
		this.theBoard = theBoard;
	}

	@Override
	public void run() {
		
		// Redraws the game board
		
		theBoard.repaint();
		
	}
	
}

@SuppressWarnings("serial")

// GameDrawingPanel is what we are drawing on

class GameDrawingPanel2 extends JComponent { 
	
	
	public static ArrayList<SpaceShip> spaceShips = new ArrayList<SpaceShip>();
		
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;
	


	public GameDrawingPanel2() { 
		
		for(int i = 0; i < GameBoard.maxShips; i++)
			{
			spaceShips.add(new SpaceShip(spaceShips));
			spaceShips.get(i).setShipId(GameBoard.shipInt);
			GameBoard.shipInt+=1;
			}
		
	}

	
	public void paint(Graphics g) { 
		
		// Allows me to make many settings changes in regards to graphics
		
		Graphics2D graphicSettings = (Graphics2D)g; 
		
		AffineTransform identity = new AffineTransform();
		
		// Draw a black background that is as big as the game board
		
		graphicSettings.setColor(Color.BLACK);
		graphicSettings.fillRect(0, 0, getWidth(), getHeight());
		
		// Set rendering rules
		
		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		graphicSettings.setPaint( Color.WHITE ); 
		
		
		
		
		
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 68){
			
			spaceShips.get(0).increaseRotationAngle();
			
		} 
		else			
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 32){
				
			GameBoard.gamePause=true;
			spaceShips.get(0).setXVelocity(0);
			spaceShips.get(0).setYVelocity(0);
				
		}
		else
			
		// Continues to rotate the ship counter clockwise if the A key is held
			
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65){
				
			spaceShips.get(0).decreaseRotationAngle();
				
		} else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){
			
			// Set movement angle to the current rotation angle
			// This is done so that the ship rotation can be set by the A & D keys
			// but when the throttle is hit the ship knows what direction to go
			
			spaceShips.get(0).setMovingAngle(spaceShips.get(0).getRotationAngle());
			
			spaceShips.get(0).increaseXVelocity(spaceShips.get(0).shipXMoveAngle(spaceShips.get(0).getMovingAngle())*0.1);
			spaceShips.get(0).increaseYVelocity(spaceShips.get(0).shipYMoveAngle(spaceShips.get(0).getMovingAngle())*0.1);
			
		}  else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 83){
			
			// Set movement angle to the current rotation angle
			// This is done so that the ship rotation can be set by the A & D keys
			// but when the throttle is hit the ship knows what direction to go
			
			spaceShips.get(0).setMovingAngle(spaceShips.get(0).getRotationAngle());
			
			spaceShips.get(0).decreaseXVelocity(spaceShips.get(0).shipXMoveAngle(spaceShips.get(0).getMovingAngle())*0.1);
			spaceShips.get(0).decreaseYVelocity(spaceShips.get(0).shipYMoveAngle(spaceShips.get(0).getMovingAngle())*0.1);
			
		}

		if(!GameBoard.gamePause)
		{
		for (SpaceShip ssSamson:spaceShips)
			{
				if(ssSamson.onScreen)
					{
					 ssSamson.move(spaceShips,GameBoard.torpedos);
					 graphicSettings.setTransform(identity);		
					 graphicSettings.translate(ssSamson.getXCenter(),ssSamson.getYCenter());
					 graphicSettings.rotate(Math.toRadians(ssSamson.getRotationAngle()));
					 graphicSettings.draw(ssSamson);
					}
			}
		int z=0;
		for(PhotonTorpedo torpedo : GameBoard.torpedos)
			{
				if(!torpedo.onScreen) continue;z+=1;
				torpedo.move();
				graphicSettings.setTransform(identity);
				graphicSettings.translate(torpedo.getXCenter(),torpedo.getYCenter());
				graphicSettings.draw(torpedo);

				for (SpaceShip admiralKen:spaceShips)
					{
						if (!admiralKen.onScreen) continue;
						Rectangle shipBounds=admiralKen.getBounds();
						Rectangle torpedoBounds=torpedo.getBounds();
						if(shipBounds.intersects(torpedoBounds)) 
							{	if (admiralKen.getShipId()==torpedo.getMotherShip()) continue;
								admiralKen.onScreen=false;
								torpedo.onScreen=false;
								GameBoard.counterr+=1;
								System.out.println((String.valueOf(admiralKen.getShipId()))+" ship destroyed");
								System.out.println("torpedo with dist:"+String.valueOf(torpedo.getTotDist())+" responsible");
							}
					}
			}//System.out.println(String.valueOf(z)+" torpedos active");
		

		
		}

		else
		{ 
			int z=0;
			for (SpaceShip ssSamson:spaceShips)
					{
					 if(!ssSamson.onScreen) continue;z+=1;
					 graphicSettings.setTransform(identity);		
					 graphicSettings.translate(ssSamson.getXCenter(),ssSamson.getYCenter());
					 graphicSettings.rotate(Math.toRadians(ssSamson.getRotationAngle()));
					 graphicSettings.draw(ssSamson);
					}
			System.out.println(String.valueOf(z)+" ships active");
					z=0;
			for(PhotonTorpedo torpedo:GameBoard.torpedos)
					{
					if(!torpedo.onScreen) continue;z+=1;
					graphicSettings.setTransform(identity);
					graphicSettings.translate(torpedo.getXCenter(),torpedo.getYCenter());
					graphicSettings.draw(torpedo);
					}
			System.out.println(String.valueOf(z)+" torpedos active");
		}

		
		
		
		
	} 
	
	
}