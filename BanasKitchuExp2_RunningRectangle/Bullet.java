import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Bullet extends Polygon
{
	
	private static final long serialVersionUID = 1L;
	
	

	int gBWidth = GameBoard.boardWidth;
	int gBHeight = GameBoard.boardHeight;
		
	private double centerX = 0, centerY = 0;
	private double moveVelocity=6;
		
	public static int[] polyXArray = {-3,3,3,-3,-3};
	public static int[] polyYArray = {-3,-3,3,3,-3};
	
	private int blockWidth = 6, blockHeight = 6;
	
	public boolean onScreen = false;
	

	
	public Bullet(double centerX, double centerY){	
		
		
		super(polyXArray, polyYArray, 5);	
		
		this.centerX = centerX;
		this.centerY = centerY;	
		this.onScreen = true;
		
	}
	
	
	public double getXCenter()  { return centerX; }		
	public double getYCenter()  { return centerY; }
	public void   changeXPos()  {this.centerX-=this.getMoveVelocity();}
	public double getMoveVelocity() {return moveVelocity;}
		
	public void setXCenter(double xCent){ this.centerX = xCent; }		
	public void setYCenter(double yCent){ this.centerY = yCent; }	
	public void setMoveVelocity(double mv) {this.moveVelocity=mv;}
	public void incMoveVelocity(double am){this.moveVelocity+=am;};
	
	public int getWidth(){ return blockWidth; }			
	public int getHeight(){ return blockHeight; }	

	
	public Rectangle getBounds()
		{
		return new Rectangle((int) getXCenter() - 3, (int) getYCenter() - 3, getWidth(), getHeight());
		}			

	public boolean collision(BossArrow a1)
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
	public void move(ArrayList<BossArrow> barrows)
		{		
		if (this.onScreen)
			{			
			this.changeXPos();		
			if(this.getXCenter() < 0)
				{
				this.onScreen=false;
				} 
			for(BossArrow b1:barrows)
					{
						if(!b1.onScreen) continue;
						if(this.collision(b1))
						{
							this.onScreen=false;
							b1.onScreen=false;
						}
					}	
		
			}


		
		}

	
}