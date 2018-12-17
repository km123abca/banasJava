import java.awt.*;
import java.util.ArrayList;





class Ship extends Polygon{

	int width = 20;
	int height = 18;

	
	int gwidth = GameBoard.boardWidth;
	int gheight = GameBoard.boardHeight;

	public double vel=5,xpos,ypos,rotAngle=0;
	

	
	public static int[] polyXArray = {-10,8,8,-10,-10};
	public static int[] polyYArray = {-10,-4,4,10,-10};
	public static int pointsInPoly=5;
	public static int vary=100;
	public boolean onScreen = true;
	
	
	
	public Ship()
			{	
			super(Ship.polyXArray, Ship.polyYArray, Ship.pointsInPoly);
			this.xpos=gwidth/2;
			this.ypos=gheight/2;
			}	
	public double[] getNose()
			{
				double[] d1=new double[2];

				d1[0]=this.xpos+this.width/2*Math.cos(Math.PI/180*this.rotAngle);
				d1[1]=this.ypos+this.width/2*Math.sin(Math.PI/180*this.rotAngle);
				return d1;
			}
	public Rectangle getBounds() 
			{
		return new Rectangle((int)this.xpos-this.width/2,(int)this.ypos-this.height/2, width, height);
            }
    public boolean collides(Rock r)
    		{

    			
    			Rectangle them=r.getBounds();
    			Rectangle us=this.getBounds();
    			if(us.intersects(them)) return true;
    			return false;
    		}
    public void increaseRotAngle(int x)
    		{
    			this.rotAngle+=x;
    			if(this.rotAngle>360)
    				this.rotAngle=0;
    		}

    public void decreaseRotAngle(int x)
    		{
    			this.rotAngle-=x;
    			if(this.rotAngle<0)
    				this.rotAngle=360;
    		}

    public String wallCollision()
    		{

    			if ((this.xpos+this.width+5)>=gwidth) return "rightwall";
    			if ((this.xpos-this.width/2)<=0) return "leftwall";
    			if ((this.ypos+this.height+35)>=gheight) return "bottomwall";
    			if ((this.ypos-this.height/2)<=0) return "topwall";
    			return "ok";
    		}


    public void increasePos()
    		{
    			double xvel=this.vel*Math.cos(this.rotAngle*Math.PI/180);
    			double yvel=this.vel*Math.sin(this.rotAngle*Math.PI/180);
    			if (!(((this.wallCollision()=="rightwall")&&(xvel>0))  || ((this.wallCollision()=="leftwall")&&(xvel<0))))
    			this.xpos+=xvel;
    			if(!(((this.wallCollision()=="topwall")&&(yvel<0))  || ((this.wallCollision()=="bottomwall")&&(yvel>0))))
    				{
    					this.ypos+=yvel;
    					//GameBoard.w2debug("Ship says:y movement restricted");
    				}


    		}
    public void decreasePos()
    		{
    			double xvel=this.vel*Math.cos(this.rotAngle*Math.PI/180);
    			double yvel=this.vel*Math.sin(this.rotAngle*Math.PI/180);
    			if (!(((this.wallCollision()=="rightwall")&&(xvel<0))  || ((this.wallCollision()=="leftwall")&&(xvel>0))))
    			this.xpos-=xvel;
    			if(!(((this.wallCollision()=="topwall")&&(yvel>0))  || ((this.wallCollision()=="bottomwall")&&(yvel<0))))
    			this.ypos-=yvel;
    		}

	public void move(boolean wPressed,boolean sPressed,boolean aPressed,boolean dPressed,boolean spacePressed)
			{		
			if(this.onScreen) 
					{
						if(dPressed) this.increaseRotAngle(5);
						if(aPressed) this.decreaseRotAngle(5);
						if(wPressed) this.increasePos();  
						if(sPressed) this.decreasePos();
						//if(spacePressed);// GameBoard.w2debug("xpos:"+String.valueOf(this.xpos)+" ypos:"+String.valueOf(this.ypos));

					}
				
		    } 
		
		
	                       }
	
	
	
	
	
	
