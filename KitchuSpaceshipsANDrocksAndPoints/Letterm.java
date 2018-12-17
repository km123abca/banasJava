import java.awt.*;
import java.util.ArrayList;





class Letterm extends Polygon{

	int width = 16;
	int height = 16;

	
	int gwidth = GameBoard.boardWidth;
	int gheight = GameBoard.boardHeight;

	public double xpos,ypos;
	

	
	public static int[] polyXArrayg = { 8, 8,-8,-8,4,4,6,6,8,8,2,2,-6,-6, 8};
	public static int[] polyYArrayg = {-6,-8,-8, 8,8,4,4,8,8,2,2,6, 6,-6,-6};
	public static int pointsInPolyg=15;
	
	public static int[] polyXArraya = { 0, 8,6,0,-6,-8,0};
	public static int[] polyYArraya = { -8,8,8,-6, 8, 8,-8};
	public static int pointsInPolya=7;
	
	public static int[] polyXArraym = { 0, 3,6,8, 3,0,-3,-8,-6,-3,0};
	public static int[] polyYArraym = { 6,-2,8,8,-8,0,-8, 8, 8,-2,6};
	public static int pointsInPolym=11;
	
	public static int[] polyXArraye = { 8,-8,-8,8,8,-5,-5,8, 8,-5,-5, 8, 8};
	public static int[] polyYArraye = {-8,-8, 8,8,5, 5, 2,2,-2,-2,-5,-5,-8};
	public static int pointsInPolye=13;
	
	public static int[] polyXArrayo = { 8,8,-8,-8,-5,-5,5,5,-5,-5,-8,-8,8};
	public static int[] polyYArrayo = {-8,8,8,-5,-5,5,5,-6,-6,-5,-5,-8,-8};
	public static int pointsInPolyo=13;
	
	public static int[] polyXArrayv = { 8,0,-8,-5,0,5,8};
	public static int[] polyYArrayv = {-8,8,-8,-8,5,-8,-8};
	public static int pointsInPolyv=7;
	
	public static int[] polyXArrayr = { 8, 8, 1, 1, 5, 5,-5,-5, 0,8,6,-6,-6,-8,-8, 8};
	public static int[] polyYArrayr = {-8,-3,-3,-4,-4,-6,-6,-3,-3,8,8, 0, 8, 8,-8,-8};
	public static int pointsInPolyr=16;

	public static int[] polyXArray;
	public static int[] polyYArray;
	public static int pointsInPoly=16;
	public boolean onScreen = true;


/*
	if(cho=="g")	
		{polyXArray=polyXArrayg;polyYArray=polyYArrayg;pointsInPoly=pointsInPolyg}
	else 
		if(cho=="a")	
		super(polyXArraya, polyYArraya, pointsInPolya);
	else 
		if(cho=="m")	
		super(polyXArraym, polyYArraym, pointsInPolym);
	else 
		if(cho=="e")	
		super(polyXArraye, polyYArraye, pointsInPolye);
	else 
		if(cho=="o")	
		super(polyXArrayo, polyYArrayo, pointsInPolyo);
	else 
		if(cho=="v")	
		super(polyXArrayv, polyYArrayv, pointsInPolyv);
	else 
		if(cho=="r")	
		super(polyXArrayr, polyYArrayr, pointsInPolyr);
	*/
	
	
	public Letterm(int x)
			{
			
		super(polyXArraym, polyYArraym, pointsInPolym);
		this.xpos=x;
		this.ypos=gheight/2-100;
			}	
	
	public Rectangle getBounds() 
			{
		return new Rectangle((int)this.xpos-this.width/2,(int)this.ypos-this.height/2, width, height);
            }
    


	
	
		
		
	                       }
	
	
	
	
	
	
