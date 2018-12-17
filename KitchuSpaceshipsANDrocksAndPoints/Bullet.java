import java.awt.*;
import java.util.ArrayList;





class Bullet extends Polygon{

	int width = 6;
	int height = 6;

	
	int gwidth = GameBoard.boardWidth;
	int gheight = GameBoard.boardHeight;

	public double vel,xpos,ypos,moveAngle;
	

	
	public static int[] polyXArray = {3,3,-3,-3,3};
	public static int[] polyYArray = {-3,3,3,-3,-3};
	public static int pointsInPoly=5;
	public static int vary=100;
	public boolean onScreen = true;
	
	
	
	public Bullet(double spawnXpos,double spawnYpos,double mAngle)
			{	
		super(Bullet.polyXArray, Bullet.polyYArray, Bullet.pointsInPoly);
		this.vel=10;
		this.xpos=spawnXpos;
		this.ypos=spawnYpos;
		this.moveAngle=mAngle;
			}	
	
	public Rectangle getBounds() 
			{
		return new Rectangle((int)this.xpos-this.width/2,(int)this.ypos-this.height/2, width, height);
            }
    public boolean collides(Rock r)
    		{

    			//if(r==this) return false;
    			Rectangle them=r.getBounds();
    			Rectangle us=this.getBounds();
    			if(us.intersects(them)) return true;
    			return false;
    		}
    public boolean ship_collides(Ship r)
    		{

    			//if(r==this) return false;
    			Rectangle them=r.getBounds();
    			Rectangle us=this.getBounds();
    			if(us.intersects(them)) return true;
    			return false;
    		}
   


    public void increasePos()
    		{
    			if(this.onScreen)
    			{
    				this.xpos+=this.vel*Math.cos(Math.PI/180*this.moveAngle);
    				this.ypos+=this.vel*Math.sin(Math.PI/180*this.moveAngle);
    				if(this.xpos+this.width>gwidth) this.onScreen=false;
    				if(this.xpos-this.width<0) this.onScreen=false;
    				if(this.ypos+this.height>gheight) this.onScreen=false;
    				if(this.ypos-this.height<0) this.onScreen=false;

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
									this.onScreen=false;
									rx.onScreen=false;
									//rx.simplemove(2);
								}

							}					
						
						this.increasePos();
						
					}
				
		    } 
		
		
	                       }
	
	
	
	
	
	
