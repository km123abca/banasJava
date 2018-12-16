import javax.swing.*;  
import javax.swing.table.DefaultTableModel;  
public class tabo {    
    JFrame f; 

    tabo(){    
    f=new JFrame(); 
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    String data[][]={ {"101","Amit","670000"},    
                          {"102","Jai","780000"},    
                          {"101","Sachin","700000"}};   

    String addedData[][]={{"203","Kirimov","45554545"}};

    //System.out.println(data.length);

    String[][] temp=new String[data.length+1][3];
    System.arraycopy(data,0,temp,0,data.length);
    System.arraycopy(addedData,0,temp,data.length,1);
    data=temp;


    String column[]={"ID","NAME","SALARY"};
                    DefaultTableModel dTableModel= new DefaultTableModel(data,column)
                                                {
                                                    public Class getColumnClass(int column)
                                                        {
                                                            Class returnValue;
                                                            if((column>=0) && (column<getColumnCount()))
                                                                    {
                                                                        returnValue= getValueAt(0,column).getClass();
                                                                    }
                                                            else
                                                                    {
                                                                        returnValue=Object.class;
                                                                    }
                                                            return returnValue;
                                                        }               


                                                };
                JTable jt = new JTable(dTableModel);


    //JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(300,400);    
    f.setVisible(true);    
}     
public static void main(String[] args) {    
    new tabo();    
}    
}