import java.sql.*;  
class Test2{  
public static void main(String ar[]){  
 try{  
    
   Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  
   String path="D:\\programming\\Work_12aug2018FromDesktopPC\\banasJava\\javaAccessDatabase\\d1.accdb";
   String url="jdbc:ucanaccess://"+path; 

   Connection c=DriverManager.getConnection(url);  
   Statement st=c.createStatement();  
   ResultSet rs=st.executeQuery("select * from login");  
    
   while(rs.next()){  
    System.out.println(rs.getString(1));  
   }  
  
}catch(Exception ee){System.out.println(ee);}  
  
}}  