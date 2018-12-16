import java.awt.Polygon;
import java.awt.Rectangle;

public class missile extends Polygon{
	
	private static final long serialVersionUID = 1L;
	

	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
		
		
	private double centerX = 0, centerY = 0;
	private double initX=0,initY=0;
	
	public static int[] polyXArray = {-3,3,3,-3,-3};
	public static int[] polyYArray = {-3,-3,3,3,-3};	

	private int torpedoWidth = 6, torpedoHeight = 6;	
	public boolean onScreen = false;	

	
	private double xVelocity = 0, yVelocity = 10;
	
	public missile(double centerX, double centerY, double yVelocityRec)
		{
		super(polyXArray, polyYArray, 5);
			
		this.centerX = centerX;
		this.centerY = centerY;	
		this.initX   = centerX;
		this.initY	 = centerY;	
		this.onScreen = true;
		
		//this.setXVelocity(0);
		this.setYVelocity(yVelocityRec*2);
		
		}
	
	public double getXCenter(){ return centerX; }
	public double getYCenter(){ return centerY; }
	
	public void setXCenter(double xCent){ this.centerX = xCent; }
	public void setYCenter(double yCent){ this.centerY = yCent; }
		
	public void changeXPos(double incAmt) { this.centerX += incAmt; }
	public void changeYPos(double incAmt) { this.centerY -= incAmt; }
	
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
		
	public void setXVelocity(double xVel){ this.xVelocity = xVel; }
	public void setYVelocity(double yVel){ this.yVelocity = yVel; }
	
	public int getWidth(){ return torpedoWidth; }
	public int getHeight(){ return torpedoHeight; }
	
	public Rectangle getBounds()
		{
		return new Rectangle((int) getXCenter() - 3, (int) getYCenter() - 3, getWidth(), getHeight());
		}

	public void move()
		{
		if(this.onScreen)
			{		
			this.changeYPos(this.getYVelocity());
			if(this.getYCenter() < 0)
				{
				this.onScreen = false;
				} else
			if (this.getYCenter() > gBHeight)
				{
				this.onScreen = false;
				}else
			if (Math.abs(this.getYCenter()-this.initY) > gBHeight/5)
				{
				this.onScreen = false;
				}
		
			} 
		
		}

	
}