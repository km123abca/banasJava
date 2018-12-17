// Layout used by the JPanel

import java.awt.BorderLayout;
import java.io.*;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import java.util.ArrayList;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.sound.sampled.Clip;

import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.*;
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame{
	
	private static final long serialVersionUID = 1L;	
	
	public static int boardWidth = 1000;
	public static int boardHeight = 800;	
	
	public static boolean keyHeld = false;
	public static boolean enterPressed=false;
	public static boolean spacePressed=false;	

	public static boolean wPressed=false;
	public static boolean sPressed=false;
	public static boolean dPressed=false;
	public static boolean aPressed=false;
	
	public static int keyHeldCode;
	public static boolean gamePaused=false;
	public static int shootDecider=0;	
	
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
    	
    	
        this.setSize(boardWidth, boardHeight);
        this.setTitle("SpaceShip Battle Reloaded");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
											 }

			@Override
			public void keyPressed(KeyEvent e) {
						keyHeld = true;
						if (e.getKeyCode()==87)	{wPressed=true;}
						if (e.getKeyCode()==83)	{sPressed=true;}
						if (e.getKeyCode()==68)	{dPressed=true;}
						if (e.getKeyCode()==65)	{aPressed=true;}	
						if (e.getKeyCode()==KeyEvent.VK_ENTER)	{enterPressed=true;
																	//playSoundEffect(laserFile);
																}
			   			if (e.getKeyCode()==32)
			    								{
			    								playSoundEffect(laserFile);
			    								System.out.println("shoot Now");
			    								spacePressed=true;
			    								}
				
												}			
			public void keyReleased(KeyEvent e) {
						keyHeld = false;
						if (e.getKeyCode()==87)	{wPressed=false;}
						if (e.getKeyCode()==83)	{sPressed=false;}
						if (e.getKeyCode()==68)	{dPressed=false;}
						if (e.getKeyCode()==65)	{aPressed=false;}	
						if (e.getKeyCode()==KeyEvent.VK_ENTER)	{enterPressed=false;}
			   			if (e.getKeyCode()==32)
			    							{
			    							System.out.println("shoot Now");
			    							spacePressed=false;
			    							}
												}
        	
        									}
        			);
        
        GameDrawingPanel2 gamePanel = new GameDrawingPanel2();        
        this.add(gamePanel, BorderLayout.CENTER);       
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);       
		executor.scheduleAtFixedRate(new RepaintTheBoard2(this), 0L, 20L, TimeUnit.MILLISECONDS);
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

class RepaintTheBoard2 implements Runnable
{

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
		
	public static int width = GameBoard.boardWidth/2;
	int height = GameBoard.boardHeight;
	public static ArrayList<Rock> rocks = new ArrayList<Rock>();
	public static Ship ssSamson=new Ship();
	public static ArrayList<Bullet> bullets= new ArrayList<Bullet>();

	public static Letterg lg=new Letterg(width);
	public static Lettera la=new Lettera(width+16);
	public static Letterm lm=new Letterm(width+32);
	public static Lettere le=new Lettere(width+48);
	public static Lettero lo=new Lettero(width+70);
	public static Letterv lv=new Letterv(width+86);
   public static Lettere le2=new Lettere(width+102);
	public static Letterr lr=new Letterr(width+118);




	public GameDrawingPanel2() 
		{ 
		for(int i=0;i<15;i++)
		rocks.add(new Rock());	

		}
			/*					
	public void dr_w(Polygon a1,Graphics2D graphicSettings)
			{
				graphicSettings.setTransform(identity);		
				graphicSettings.translate(a1.getXCenter(),a1.getYCenter());
				graphicSettings.rotate(Math.toRadians(a1.getRotationAngle()));
				graphicSettings.draw(a1);
			}
			*/


	
	public void paint(Graphics g) 
	{ 	
		Graphics2D graphicSettings = (Graphics2D)g; 		
		AffineTransform identity = new AffineTransform();		
		graphicSettings.setColor(Color.BLACK);
		graphicSettings.fillRect(0, 0, getWidth(), getHeight());		
		graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		graphicSettings.setPaint( Color.WHITE ); 		
		
		for(Rock r:rocks)
			{
		if(!r.onScreen) continue;
	    r.move(rocks,ssSamson);
		graphicSettings.setTransform(identity);		
		graphicSettings.translate(r.xpos,r.ypos);
		//graphicSettings.rotate(Math.toRadians(a1.getRotationAngle()));
		graphicSettings.draw(r);
			}
		for(Bullet r:bullets)
			{
		if(!r.onScreen) continue;
	    r.move(rocks);
		graphicSettings.setTransform(identity);		
		graphicSettings.translate(r.xpos,r.ypos);
		//graphicSettings.rotate(Math.toRadians(a1.getRotationAngle()));
		graphicSettings.draw(r);
			}
		ssSamson.move(GameBoard.wPressed,GameBoard.sPressed,GameBoard.aPressed,GameBoard.dPressed,GameBoard.spacePressed);
		graphicSettings.setTransform(identity);		
		graphicSettings.translate(ssSamson.xpos,ssSamson.ypos);
		graphicSettings.rotate(Math.toRadians(ssSamson.rotAngle));
		graphicSettings.draw(ssSamson);

		if(GameBoard.spacePressed)
		{
			bullets.add(new Bullet(ssSamson.getNose()[0],ssSamson.getNose()[1],ssSamson.rotAngle));
		}
		


				graphicSettings.setTransform(identity);		
				graphicSettings.translate(lg.xpos,lg.ypos);
				graphicSettings.draw(lg);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(la.xpos,la.ypos);
				graphicSettings.draw(la);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(lm.xpos,lm.ypos);
				graphicSettings.draw(lm);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(le.xpos,le.ypos);
				graphicSettings.draw(le);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(lo.xpos,lo.ypos);
				graphicSettings.draw(lo);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(lv.xpos,lv.ypos);
				graphicSettings.draw(lv);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(le2.xpos,le2.ypos);
				graphicSettings.draw(le2);

				graphicSettings.setTransform(identity);		
				graphicSettings.translate(lr.xpos,lr.ypos);
				graphicSettings.draw(lr);
		
					
	} 
	
	
}