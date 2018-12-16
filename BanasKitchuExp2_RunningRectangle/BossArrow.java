import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BossArrow extends Polygon
	{

	private double aVelocity = 8;
	public boolean onScreen=false;
	//public boolean activ=true;
	private static int a=0;	
	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
	

	private double centerX = 10, centerY = gBHeight/2-2;

	//public static int[] polyXArray = {10,-10,-10,10,10,10,20,20,-20,-20,-10,-10,10};
	//public static int[] polyYArray = {20, 20, 10,10,20,10,10, 0,  0, 10, 10, 20,20};

	public static int[] polyXArray = {-10, 5, 5, 10, 10, 5, 5,-10};
	public static int[] polyYArray = {-9,-9,-18,-18,18,18,9,9};
	
	private int rWidth = 20, rHeight = 36;	
	
	
	
	public BossArrow()
			{			
			super(polyXArray, polyYArray, 8);						
			this.onScreen=true;
			}
	


	public double getXCenter(){ return centerX; }
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	public void setYCenter(double yCent){ this.centerY = yCent; }
	public void updatePos(){this.centerX+=this.getVelocity();}

	
	public int getRWidth(){ return rWidth; }
	public int getRHeight(){ return rHeight; }
	
	public double getVelocity(){ return aVelocity; }
	
	
	public void setUpVelocity(double xVel){ this.aVelocity = xVel; }
	

		
	public Rectangle getBounds()
		{
		return new Rectangle((int) getXCenter() - 10, (int) getYCenter() - 18, getRWidth(), getRHeight());
		}
	public boolean collision(Runner a1)
		{
				Rectangle r1r,a1r;
				r1r=this.getBounds();
				a1r=a1.getBounds();
				if(r1r.intersects(a1r)) 
					{
						//GameBoard.playSoundEffect(GameBoard.kitchushoot);
						return true;
					}
				return false;
		}


	public void move()
		{
			if(this.onScreen)
				{
					this.updatePos();
					if(this.getXCenter()>(gBWidth-20)) this.onScreen=false;
				}
		}
	
}