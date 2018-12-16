import java.awt.*;
import java.util.ArrayList;





class Rock extends Polygon{

	int width = 20;
	int height = 20;

	
	int gwidth = GameBoard.boardWidth;
	int gheight = GameBoard.boardHeight;

	public double xvel,yvel,xpos,ypos;
	

	
	public static int[] polyXArray = { 5,10, 7, 10,  0,-10,-7,-10, -5,5};
	public static int[] polyYArray = {10,0, -4, -6,-10, -6,-4,  0, 10,10};
	public static int pointsInPoly=10;
	public static int vary=100;
	public boolean onScreen = true;
	
	
	
	public Rock()
			{	
		super(Rock.polyXArray, Rock.polyYArray, pointsInPoly);
		this.xvel=Math.random()*4+4;
		this.yvel=Math.random()*4+4;
		this.xpos=Math.random()*gwidth;
		this.ypos=Math.random()*gheight;
		GameBoard.w2debug("xpos:"+String.valueOf(this.xpos)+" ypos:"+String.valueOf(this.ypos)+"\n");		

			}	
	
	public Rectangle getBounds() 
			{
		return new Rectangle((int)this.xpos-this.width/2,(int)this.ypos-this.height/2, width, height);
            }
    public boolean collides(Rock r)
    		{

    			if(r==this) return false;
    			Rectangle them=r.getBounds();
    			Rectangle us=this.getBounds();
    			if(us.intersects(them)) return true;
    			return false;
    		}
    public void exchangeSpeeds(Rock r)
    		{
    			double temp;
    			temp=this.xvel;
    			this.xvel=r.xvel;
    			r.xvel=temp;
    			temp=this.yvel;
    			this.yvel=r.yvel;
    			r.yvel=temp;

    		}
    public String wallCollision()
    		{
    			if ((this.xpos+this.width/2)>=gwidth) return "rightwall";
    			if ((this.xpos-this.width/2)<=0) return "leftwall";
    			if ((this.ypos+this.height+5)>=gheight) return "bottomwall";
    			if ((this.ypos-this.height/2)<=0) return "topwall";
    			return "ok";
    		}

    public void checkWallCollsion()
    		{
    			if(this.wallCollision()=="leftwall") this.xvel=Math.abs(this.xvel)*1;
    			if(this.wallCollision()=="rightwall") this.xvel=Math.abs(this.xvel)*-1;
    			if(this.wallCollision()=="bottomwall") this.yvel=Math.abs(this.xvel)*-1;
    			if(this.wallCollision()=="topwall") this.yvel=Math.abs(this.xvel)*1;
    		}
    public void increaseXpos()
    		{
    			this.xpos+=this.xvel;
    		}
    public void increaseYpos()
    		{
    			this.ypos+=this.yvel;
    		}
    public void simplemove(int x)
    		{
    			for(int i=0;i<x;i++)
    			{
    					this.checkWallCollsion();
    					this.increaseXpos();
						this.increaseYpos();	
    			}
    		}
	
	public void move(ArrayList<Rock> rocks)
			{		
			if(this.onScreen) 
					{
						for(Rock rx:rocks)
							{
							if(!rx.onScreen) continue;
							if(this.collides(rx)) 
								{
									this.exchangeSpeeds(rx);
									//rx.simplemove(2);
								}

							}
						this.checkWallCollsion();
						this.increaseXpos();
						this.increaseYpos();
					}
				
		    } 
		
		
	                       }
	
	
	
	
	
	
