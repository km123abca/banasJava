import java.awt.*;
import java.util.ArrayList;
import java.awt.Rectangle; 

@SuppressWarnings("serial")
public class car extends Polygon
	{
		private double xVelocity = 4, yVelocity = 1;
		int gBWidth = GameBoard.boardWidth;
		int gBHeight = GameBoard.boardHeight;
		private double centerX = gBWidth/2, centerY = gBHeight/2;
		public static int[] polyXArray = {-10,10,10,-10,-10};
		public static int[] polyYArray = {-10,-10,10,10,-10};
		private int shipWidth = 20, shipHeight = 20;
		
		private double uLeftXPos = getXCenter() + this.polyXArray[0];
		private double uLeftYPos = getYCenter() + this.polyYArray[0];
		public boolean onScreen = true;
		
		public car(ArrayList<car> cars)
			{

			super(polyXArray, polyYArray, 5);
			Rectangle ourbounds=this.getBounds();
			boolean collision=true;
			int itercounter=0;
			while(collision)
				{   
					itercounter+=1;
					if(itercounter>100) break;
					int yshift= (int) (Math.random() * gBHeight);
					this.setYCenter(yshift);
					collision=false;
					for(car carsample:cars)
					{	Rectangle theirBounds=carsample.getBounds();
						if (theirBounds.intersects(ourbounds))
							{
								collision=true;
								break;
							}					
					}
				}
			
			}
	
		public double getXCenter(){ return centerX; }
		public double getYCenter(){ return centerY; }
		public void setXCenter(double xCent){ this.centerX = xCent; }
		public void setYCenter(double yCent){ this.centerY = yCent; }
		public void increaseXPos(double incAmt) { this.centerX -= incAmt; }
		public void increaseYPos(double incAmt) { this.centerY -= incAmt; }	
		public double getuLeftXPos(){ return uLeftXPos; }
		public double getuLeftYPos(){ return uLeftYPos; }
		public void setuLeftXPos(double xULPos){ this.uLeftXPos = xULPos; }
		public void setuLeftYPos(double yULPos){ this.uLeftYPos = yULPos; }
		public int getShipWidth(){ return shipWidth; }
		public int getShipHeight(){ return shipHeight; }
		public double getXVelocity(){ return xVelocity; }
		public double getYVelocity(){ return yVelocity; }
		public void setXVelocity(double xVel){ this.xVelocity = xVel; }
		public void setYVelocity(double yVel){ this.yVelocity = yVel; }
		public void increaseXVelocity(double xVelInc){ this.xVelocity += xVelInc; }
		public void increaseYVelocity(double yVelInc){ this.yVelocity += yVelInc; }
		public void decreaseXVelocity(double xVelDec){ this.xVelocity -= xVelDec; }
		public void decreaseYVelocity(double yVelDec){ this.yVelocity -= yVelDec; }

		public void moveRight()
			{
				this.setXCenter(this.getXCenter()+0.5);
			}
		public void moveLeft()
			{
				this.setXCenter(this.getXCenter()-0.5);
			}
		public void escapeUp()
			{
				this.setYVelocity(this.getYVelocity()+0.2);
			}
	    public void escapeDown()
			{
				double velcheck=this.getYVelocity()-0.2 ;
				if (velcheck<0) velcheck=0.2;
				this.setYVelocity(velcheck);
			}
		public void accelUp()
			{
				this.setYVelocity(this.getYVelocity()+0.1);
			}
		public void accelDown()
			{
				this.setYVelocity(this.getYVelocity()-0.1);
			}	
		public Rectangle getBounds()
			{
				return new Rectangle((int) getXCenter() - 10, (int) getYCenter() - 10, getShipWidth(), getShipHeight());
			}
		public void move()
			{
				
				if(this.getXCenter() < 0)
					{
					this.setXCenter(gBWidth);
					} 
				else
				if (this.getXCenter() > gBWidth)
					{
					this.setXCenter(0);
					}
				this.increaseYPos(this.getYVelocity());
				if(this.getYCenter() < 0)
					{
					this.setYCenter(gBHeight);
					} 
				else
				if (this.getYCenter() > gBHeight)
					{
					this.setYCenter(0);
					}
		
			}
	
	
}