import java.util.*; 
class tester { 
  
    // Driver Code 
    public static void main(String args[]) 
    { 
  
        // X and Y coordinates 
        double x = 90.0; 
        double y = 90.0; 
  
        // theta value from polar coordinate (r, theta) 
        double theta = Math.toDegrees(Math.atan2(x, y)); 
  
        System.out.println(theta); 
    } 
} 