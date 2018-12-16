// Layout used by the JPanel
import java.awt.BorderLayout;

// Define color of shapes
import java.awt.Color;

// Allows me to draw and render shapes on components
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

// Will hold all of my Rock objects
import java.util.ArrayList;

// Runs commands after a given delay
import java.util.concurrent.ScheduledThreadPoolExecutor;

// Defines time units. In this case TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Lesson50 extends JFrame{
    
    // Height and width of the game board
    
    public static int boardWidth = 1000;
    public static int boardHeight = 800;
    
    public static void main(String [] args)
    {
            new Lesson50();
    }
    
    public Lesson50()
    {
        // Define the defaults for the JFrame
        
        this.setSize(boardWidth, boardHeight);
        this.setTitle("Java Asteroids");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameDrawingPanel gamePanel = new GameDrawingPanel();

     // Make the drawing area take up the rest of the frame
        
        this.add(gamePanel, BorderLayout.CENTER);
        
        // Used to execute code after a given delay
        // The attribute is corePoolSize - the number of threads to keep in 
        // the pool, even if they are idle
        
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        
        // Method to execute, initial delay, subsequent delay, time unit
        
        executor.scheduleAtFixedRate(new RepaintTheBoard(this), 0L, 20L, TimeUnit.MILLISECONDS);
        
        // Show the frame
        
        this.setVisible(true);
    }
    
}

// Class implements the runnable interface
// By creating this thread we can continually redraw the screen
// while other code continues to execute

class RepaintTheBoard implements Runnable{

    Lesson50 theBoard;
    
    public RepaintTheBoard(Lesson50 theBoard){
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

class GameDrawingPanel extends JComponent { 
    
    // Holds every Rock I create
    
    public ArrayList<Rock> rocks = new ArrayList<Rock>();
    
    // Get the original x & y points for the polygon
    
    int[] polyXArray = Rock.sPolyXArray;
    int[] polyYArray = Rock.sPolyYArray;
    
    // Gets the game board height and weight
    
    int width = Lesson50.boardWidth;
    int height = Lesson50.boardHeight;
    
    // Creates 50 Rock objects and stores them in the ArrayList
    
    public GameDrawingPanel() { 
        
        for(int i = 0; i < 50; i++){
            
            // Find a random x & y starting point
            // The -40 part is on there to keep the Rock on the screen
            
            int randomStartXPos = (int) (Math.random() * (Lesson50.boardWidth - 40) + 1);
            int randomStartYPos = (int) (Math.random() * (Lesson50.boardHeight - 40) + 1);
            
            // Add the Rock object to the ArrayList based on the attributes sent
            
            rocks.add(new Rock(Rock.getpolyXArray(randomStartXPos), Rock.getpolyYArray(randomStartYPos), 13, randomStartXPos, randomStartYPos));
            
        }
        
    } 
    
    public void paint(Graphics g) { 
        
        // Allows me to make many settings changes in regards to graphics
        
        Graphics2D graphicSettings = (Graphics2D)g; 
        
        // Draw a black background that is as big as the game board
        
        graphicSettings.setColor(Color.BLACK);
        graphicSettings.fillRect(0, 0, getWidth(), getHeight());
        
        // Set rendering rules
        
        graphicSettings.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
        
        // Set the drawing color to white
        
        graphicSettings.setPaint( Color.WHITE); 
        
        // Cycle through all of the Rock objects
        
        for(Rock rock : rocks){
            
            // Move the Rock polygon 
            
            rock.move(); 
            
            // Stroke the polygon Rock on the screen
            
            graphicSettings.draw(rock); 
            
        } 
        
    } 
    
}