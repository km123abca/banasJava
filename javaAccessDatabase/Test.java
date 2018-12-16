import java.sql.*;  
class Test{  
public static void main(String ar[]){  
 try{  
   String url="jdbc:odbc:mydsnxx";  
   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  
   Connection c=DriverManager.getConnection(url);  
   Statement st=c.createStatement();  
   ResultSet rs=st.executeQuery("select * from login");  
    
   while(rs.next()){  
    System.out.println(rs.getString(1));  
   }  
  
}catch(Exception ee){System.out.println(ee);}  
  
}}  