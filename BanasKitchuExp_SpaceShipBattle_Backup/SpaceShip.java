import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SpaceShip extends Polygon
	{

	private double xVelocity = 0, yVelocity = 0;
		
	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;

	private int shipId=0;
	
	private double centerX = Math.random()*gBWidth, centerY = Math.random()*gBHeight;

	public static int[] polyXArray = {-13,14,-13,-5,-13};
	public static int[] polyYArray = {-15,0,15,0,-15};
	
	private int shipWidth = 27, shipHeight = 30;

	private double uLeftXPos = getXCenter() + this.polyXArray[0];
	private double uLeftYPos = getYCenter() + this.polyYArray[0];
	
	private double rotationAngle = 0, movingAngle = 0;
	public boolean onScreen = true;	
	
	public SpaceShip(ArrayList<SpaceShip> spaceShips)
			{			
			super(polyXArray, polyYArray, 5);
			Rectangle ourbounds=this.getBounds();
			boolean collision=true;
			int itercounter=0;
			while(collision)
				{   ourbounds=this.getBounds();
					itercounter+=1;
					if(itercounter>100) break;
					double yshift= (double) (Math.random() * (gBHeight-40));
					this.setYCenter(yshift);
					double xshift= (double) (Math.random() * (gBWidth-40));
					this.setXCenter(xshift);
					collision=false;
					for(SpaceShip ss1:spaceShips)
					{	Rectangle theirBounds=ss1.getBounds();
						if (theirBounds.intersects(ourbounds))
							{
								collision=true;
								break;
							}					
					}
				}
			}
	
	public void setShipId(int ss){this.shipId=ss;}
	public int  getShipId(){return this.shipId;}

	public double getXCenter(){ return centerX; }
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	public void setYCenter(double yCent){ this.centerY = yCent; }
	
	public void increaseXPos(double incAmt) { this.centerX += incAmt; }
	public void increaseYPos(double incAmt) { this.centerY += incAmt; }	
	
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
	
	public void setMovingAngle(double moveAngle){ this.movingAngle = moveAngle; }
	public double getMovingAngle(){ return movingAngle; }
	
	public void increaseMovingAngle(double moveAngle){ this.movingAngle += moveAngle; }
	
	public double shipXMoveAngle(double xMoveAngle)
		{
		return (double) (Math.cos(xMoveAngle * Math.PI / 180));
		} 
	
	public double shipYMoveAngle(double yMoveAngle)
		{		
		return (double) (Math.sin(yMoveAngle * Math.PI / 180));
		}

	public double getRotationAngle(){ return rotationAngle; }
	public void increaseRotationAngle()
		{ 
		if(getRotationAngle() >= 355) { rotationAngle = 0; }
		else { rotationAngle += 1; }
		
		}
	
	public void decreaseRotationAngle()
		{		
		if(getRotationAngle() < 0) { rotationAngle = 355; }
		else { rotationAngle -= 1; }
		}
	

	
	public Rectangle getBounds(){
		
		return new Rectangle((int) getXCenter() - 14, (int) getYCenter() - 14, getShipWidth(), getShipHeight());
		
	}

	public boolean ishoot(double xpos,double ypos)
			{
				double ygap=ypos-this.getYCenter();
				double xgap=xpos-this.getXCenter();
				double relAngle;
				if (xgap==0) 
					{
						if (ygap>0) relAngle=90;
						else relAngle=270;
					}
				else if(ygap==0)
					{
						if (xgap>0) relAngle=0;
						else relAngle=180;
					}
				else
					{
						relAngle=Math.toDegrees(Math.atan2(Math.abs(ygap),Math.abs(xgap)));
						if((ygap>0)&&(xgap<0)) relAngle=180-relAngle;
						else if ((ygap<0)&&(xgap<0)) relAngle=180+relAngle;
						else if ((ygap<0)&&(xgap>0)) relAngle=360-relAngle;
					}
				if (Math.abs(relAngle-this.getRotationAngle())<2) 
					{
						//System.out.println("relangle:"+String.valueOf(relAngle)+" and rot Angle:"+ this.getRotationAngle());
						return true;
					}

				else return false;
			}

	public double threatDistance(PhotonTorpedo torpedox)
			{
				double ygap=this.getYCenter()-torpedox.getYCenter();
				double xgap=this.getXCenter()-torpedox.getXCenter();
				double relAngle;
				if (xgap==0) 
					{
						if (ygap>0) relAngle=90;
						else relAngle=270;
					}
				else if(ygap==0)
					{
						if (xgap>0) relAngle=0;
						else relAngle=180;
					}
				else
					{
						relAngle=Math.toDegrees(Math.atan2(Math.abs(ygap),Math.abs(xgap)));
						if((ygap>0)&&(xgap<0)) relAngle=180-relAngle;
						else if ((ygap<0)&&(xgap<0)) relAngle=180+relAngle;
						else if ((ygap<0)&&(xgap>0)) relAngle=360-relAngle;
					}
				if (Math.abs(relAngle-torpedox.getMovingAngle())<4) return Math.sqrt(xgap*xgap+ygap*ygap);
				else return 9999.99;


			}
	

	
	public double getShipNoseX()
		{		
		return this.getXCenter() + Math.cos(rotationAngle) * 14;
		}
	
	public double getShipNoseY()
		{		
		return this.getYCenter() + Math.sin(rotationAngle) * 14;
		}

	public void shootnow()
		{  System.out.println("Shot fired from Ship No:"+String.valueOf(this.getShipId()));
		   GameBoard.torpedos.add(new PhotonTorpedo(this.getShipNoseX(),
			    									this.getShipNoseY(), 
			    									this.getRotationAngle(),
			    									this.getShipId()
			    								   )
		   						 );
		}

	public void exchangeSpeeds(SpaceShip otherShip)
		{
			double yvelocity2=this.getYVelocity();
			double xVelocity2=this.getXVelocity();
			this.setXVelocity(otherShip.getXVelocity());
			this.setYVelocity(otherShip.getYVelocity());
			otherShip.setXVelocity(yvelocity2);
			otherShip.setYVelocity(xVelocity2);
		}

	public double getEscapeAngle(double theta1)
		{
			double theta2=theta1+90;
			if(theta2>360) theta2-=360;
			return theta2;
		}
	
	public void move(ArrayList<SpaceShip> spaceShips,ArrayList<PhotonTorpedo> torpedoes)
		{	
		
		
		for (SpaceShip otherShip:spaceShips)
		{
			if(otherShip==this) continue;
			if(!otherShip.onScreen) continue;
			Rectangle ourShip=this.getBounds();
			Rectangle hostileShip=otherShip.getBounds();
			if(ourShip.intersects(hostileShip))
			{
				this.exchangeSpeeds(otherShip);
			}
			if(this.getShipId()==1) continue;
			if (this.ishoot(otherShip.getXCenter(),otherShip.getYCenter()))
				{   //System.out.println(String.valueOf(otherShip.getShipId())+" is in range of "+String.valueOf(this.getShipId()));
					this.shootnow(); //km here shooting has been temporarily disabled
					//System.out.println
				}
		}
		if(this.getShipId()!=1){
			this.increaseRotationAngle();//km here
		double distx=8888.88;
		int ti=-1;
		int tid=0;
		for(PhotonTorpedo torpedo1:torpedoes)
				{	ti+=1;
					if(!torpedo1.onScreen) continue;
					if(this.threatDistance(torpedo1)<distx)
					  {distx=this.threatDistance(torpedo1);
					  	tid=ti;
					  }
				}
		if(distx!=8888.88)
			{   System.out.println("ship NO:"+String.valueOf(this.getShipId())+" senses danger moving now");
				PhotonTorpedo torpedoy=torpedoes.get(tid);
				this.setMovingAngle(this.getEscapeAngle(torpedoy.getMovingAngle()));
				this.increaseXVelocity(1*this.shipXMoveAngle(this.getMovingAngle()) );
				this.increaseYVelocity(1*this.shipYMoveAngle(this.getMovingAngle()) );

			}
		else
			{
				this.setXVelocity(0);
				this.setYVelocity(0);
			}
						}
		this.increaseXPos(this.getXVelocity());		
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
			} else
			if (this.getYCenter() > gBHeight)
			{
			this.setYCenter(0);
			}
		
		}
	
	
}