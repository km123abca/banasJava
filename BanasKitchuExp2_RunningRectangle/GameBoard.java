// Layout used by the JPanel

import java.awt.BorderLayout;
import java.io.*;
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
	public static int boardHeight = 800;
	
	// Used to check if a key is being held down
	
	public static boolean keyHeld = false;
	public static boolean enterPressed=false;
	public static boolean spacePressed=false;
	
	// Gets the keycode for the key being held down
	
	public static int keyHeldCode;
	public static boolean gamePaused=false;
	public static int shootDecider=0;
	
	// Holds every PhotonTorpedo I create
	
	
	
	// NEW Location of sound files
	
	public static String thrustFile = "file:./src/thrust.au";
	public static String laserFile = "file:./src/laser.aiff";
	public static String kitchudow = "file:./src/doww.mp3";
	public static String kitchushoot = "file:./src/shoott.au";
	
	public static void main(String [] args)
    {
            new GameBoard();
            
    }
	
	public GameBoard()
    {
    	// Define the defaults for the JFrame
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Rat Race");
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
					
					playSoundEffect(laserFile);
					
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
			    	//System.out.println("Rotate Left");
			    	gamePaused=true;
			    	keyHeldCode = e.getKeyCode();
			    	keyHeld = true;
			    }
				
				// NEW Checks if Enter key is pressed ---------------
				
			    else if (e.getKeyCode()==KeyEvent.VK_ENTER){
			    	System.out.println("Jump Now");
			    	enterPressed=true;
			    	// NEW play laser sound when enter is hit
			    	
			    	playSoundEffect(laserFile);
			    	
			    	//have to write shooting code here
			    	
			    	
			    	
			    }

			    else if (e.getKeyCode()==32){
			    	System.out.println("shoot Now");
			    	spacePressed=true;
			    	// NEW play laser sound when enter is hit
			    	
			    	
			    	
			    	//have to write shooting code here
			    	
			    	
			    	
			    }
				
			}

			// When the key is released this informs the code that
			// the key isn't being held down
			
			public void keyReleased(KeyEvent e) {
		
				keyHeld = false;
				enterPressed=false;
				spacePressed=false;
				gamePaused=false;
			}
        	
        });
        
        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();

    
        
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
	


	public static PrintWriter createFile(String fileName)
			{
				try
					{
						File listOfNames =new File(fileName);
						PrintWriter infoToWrite= new PrintWriter(
																new BufferedWriter(new FileWriter(listOfNames,true))
																);
						return infoToWrite;
					}
				catch(IOException e)
					{
						System.out.println("An I/O error occurred");
						System.exit(0);
					}
				return null;
			}
	public static void w2debug(String stx)
			{
				PrintWriter debugOutput=createFile("debug.txt");
				debugOutput.println(stx);
				debugOutput.close();
			}
	// NEW Handles playing all sound effects
	
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
	
	
	//public static ArrayList<SpaceShip> spaceShips = new ArrayList<SpaceShip>();
		
	int width = GameBoard.boardWidth;
	int height = GameBoard.boardHeight;
	public static ArrayList<RoadBrick> bricks = new ArrayList<RoadBrick>();
	public static Runner r1=new Runner();
	public static ArrayList<Arrow> arrows= new ArrayList<Arrow>();
	public static ArrayList<Bullet> bullets=new ArrayList<Bullet>();
	public static ArrayList<BossArrow> barrows=new ArrayList<BossArrow>();


	public GameDrawingPanel2() { 
		int sX=3;
		for(int i = 0; i < 25; i++)
			{
			bricks.add(new RoadBrick(sX+40*i,height/2));
			}
		
	} 



	public boolean clearToSendArrows()
			{

				
				if(arrows.size() ==0) return true;
				Arrow a1=arrows.get(arrows.size()-1);
				
				if(a1.getXCenter()>(GameBoard.boardWidth/2)) 
					{

						return true;
					}
				return false;
			}

    
	public boolean clearToSendTheBigOne()
			{
				for (BossArrow bx:barrows) 
					{
						if(bx.onScreen) return false;
					} 
				boolean deco=false;
				if(arrows.size() ==0) return true;
				Arrow a1=arrows.get(arrows.size()-1);
				
				if(a1.getXCenter()>(GameBoard.boardWidth/4)) 
					{

						if(Math.random()>0.8) return true;
					}
				return false;
			}
	public void clearUsedArrows()
			{
				int itercntrl=1;
				while(!arrows.get(0).onScreen) 
					{
					 if(itercntrl>arrows.size()) break;
					 arrows.remove(0);
					 itercntrl+=1;
					 if(itercntrl>20) break;
					}

			}

	
	public void paint(Graphics g) 
	{ 
		
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
			
			//The D key
			
		} else
			
		
			
		if(GameBoard.keyHeld == true && GameBoard.keyHeldCode == 65){
				
			//a
				
		} else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 87){
			

			
		//the w key
			
		}  else
			
		if (GameBoard.keyHeld == true && GameBoard.keyHeldCode == 83){		

			// the s key
		}	else
			
		if (GameBoard.enterPressed){		

			r1.jump();
		}
		if(GameBoard.spacePressed){

			GameBoard.shootDecider+=1;
			if(GameBoard.shootDecider>50) GameBoard.shootDecider=0;
			if ((GameBoard.shootDecider%4)==0){
				GameBoard.playSoundEffect(GameBoard.laserFile);
				bullets.add(new Bullet(r1.runnerNosex(),r1.runnerNosey()));
			}
		}

		//clearUsedArrows();
		if(clearToSendArrows()) arrows.add(new Arrow());

		if(clearToSendTheBigOne()) barrows.add(new BossArrow());

		for(BossArrow a1:barrows)
			{
				if(!a1.onScreen) continue;
				if(!GameBoard.gamePaused)
				a1.move();
				//if(!a1.activ) continue;
				graphicSettings.setTransform(identity);		
				graphicSettings.translate(a1.getXCenter(),a1.getYCenter());
				graphicSettings.draw(a1);
				if(a1.collision(r1)) 
						GameBoard.gamePaused=true;
			}	

		for(Arrow a1:arrows)
			{
				if(!a1.onScreen) continue;
				if(!GameBoard.gamePaused)
				a1.move();
				if(!a1.activ) continue;
				graphicSettings.setTransform(identity);		
				graphicSettings.translate(a1.getXCenter(),a1.getYCenter());
				graphicSettings.draw(a1);
				if(a1.collision(r1)) 
						GameBoard.gamePaused=true;
			}	

		for (Bullet b1:bullets)
			{
				if(!b1.onScreen) continue;
				if(!GameBoard.gamePaused)
				b1.move(barrows);
			    graphicSettings.setTransform(identity);		
		 		graphicSettings.translate(b1.getXCenter(),b1.getYCenter());
				graphicSettings.draw(b1);
			}
		
		for (RoadBrick basicBrick:bricks)
			{
				if(basicBrick.onScreen)
					{
					 if(!GameBoard.gamePaused)
					 basicBrick.move();
					 graphicSettings.setTransform(identity);		
					 graphicSettings.translate(basicBrick.getXCenter(),basicBrick.getYCenter());
					 //graphicSettings.rotate(Math.toRadians(ssSamson.getRotationAngle()));
					 graphicSettings.draw(basicBrick);
					}
			}
         if(!GameBoard.gamePaused)
		 r1.move();
		 graphicSettings.setTransform(identity);		
		 graphicSettings.translate(r1.getXCenter(),r1.getYCenter());
		 //graphicSettings.rotate(Math.toRadians(ssSamson.getRotationAngle()));
		 graphicSettings.draw(r1);


		
		
	} 
	
	
}