import java.awt.Polygon;
import java.awt.Rectangle;

public class bullet extends Polygon
{
	
	private static final long serialVersionUID = 1L;
	
	

	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
		
	private double centerX = 0, centerY = 0;
	private double moveVelocity=0;
		
	public static int[] polyXArray = {-3,3,3,-3,-3};
	public static int[] polyYArray = {-3,-3,3,3,-3};
	
	private int blockWidth = 6, blockHeight = 6;
	
	public boolean onScreen = false;
	

	
	public bullet(double centerX, double centerY){	
		
		
		super(polyXArray, polyYArray, 5);	
		
		this.centerX = centerX;
		this.centerY = centerY;	
		this.onScreen = true;
		
	}
	
	
	public double getXCenter()  { return centerX; }		
	public double getYCenter()  { return centerY; }
	public void   changeXPos()  {this.centerX+=this.getMoveVelocity();}
	public double getMoveVelocity() {return moveVelocity;}
		
	public void setXCenter(double xCent){ this.centerX = xCent; }		
	public void setYCenter(double yCent){ this.centerY = yCent; }	
	public double setMoveVelocity(double mv) {this.moveVelocity=mv;}
	public double incMoveVelocity(double am){this.moveVelocity+=am;};
	
	public int getWidth(){ return blockWidth; }			
	public int getHeight(){ return blockHeight; }	

	
	public Rectangle getBounds()
		{
		return new Rectangle((int) getXCenter() - 6, (int) getYCenter() - 6, getWidth(), getHeight());
		}			

	
	public void move()
		{		
		if(this.onScreen)
			{			
			this.changeXPos();		
			if(this.getXCenter() < 0)
				{
				this.setXCenter(gBWidth);
				} else
			if (this.getXCenter() > gBWidth)
				{
				this.setXCenter(2);
				}	
		
			} 
		
		}

	
}