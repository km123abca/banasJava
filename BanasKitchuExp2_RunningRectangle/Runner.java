import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Runner extends Polygon
	{

	private double upVelocity = -8;
	private int updist=160;
		
	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
	private boolean onAir=false;

	private double centerX = gBWidth-80, centerY = gBHeight/2-2;

	//public static int[] polyXArray = {10,-10,-10,10,10,10,20,20,-20,-20,-10,-10,10};
	//public static int[] polyYArray = {20, 20, 10,10,20,10,10, 0,  0, 10, 10, 20,20};

	public static int[] polyXArray = {-10,  10,  10,-10,-10,-10,-20,-20,  20,  20, 10,  10,-10};
	public static int[] polyYArray = {-20, -20, -10,-10,-20,-10,-10, -0,  -0, -10, -10, -20,-20};
	
	private int rWidth = 40, rHeight = 40;	
	
	public boolean onScreen = true;	
	
	public Runner()
			{			
			super(polyXArray, polyYArray, 13);
			Rectangle ourbounds=this.getBounds();
			
			}
	
	public double runnerNosex() { return centerX-20;}
	public double runnerNosey() { return centerY-10;}

	public double getXCenter(){ return centerX; }
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	public void setYCenter(double yCent){ this.centerY = yCent; }
	public void updateY(){this.centerY+=this.getUpVelocity();}

	
	public int getRWidth(){ return rWidth; }
	public int getRHeight(){ return rHeight; }
	
	public double getUpVelocity(){ return upVelocity; }
	
	
	public void setUpVelocity(double xVel){ this.upVelocity = xVel; }
	

		
	public Rectangle getBounds()
		{
		return new Rectangle((int) getXCenter() - 20, (int) getYCenter() - 20, getRWidth(), getRHeight());
		}

	public void jump()
		{
			if(!onAir) onAir=true;
			

		}

	public void move()
		{
			if(this.onAir)
				{
					this.updateY();
					if(this.getYCenter()<(gBHeight/2-2-this.updist)) this.setUpVelocity(this.getUpVelocity()*-1);

					if( (this.getUpVelocity()>0) &&(this.getYCenter()>=(gBHeight/2-2)) )
						{	onAir=false;
							this.setUpVelocity(this.getUpVelocity()*-1);
						}
				}
		}
	
}