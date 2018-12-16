import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class sm2 extends JFrame
	{

		JButton button1;
		JLabel lCustName,lPhoneNo,lBDate;
		JTextField tfCustName,tfPhoneNo,tfBDate;
		Date todaysDate;
		JPanel datacont,masterpanel;
		static Object[][] databaseResults;
		public static void main(String[] args)
			{
				new sm2();

			}
		public sm2()
			{   
				this.setLocationRelativeTo(null);				
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Billing Software");
				datacont=new JPanel();
				datacont.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
				lCustName=new JLabel("Customer Name:");
				datacont.add(lCustName);
				tfCustName=new JTextField(15);
				datacont.add(tfCustName);
				lPhoneNo=new JLabel("Phone:");
				datacont.add(lPhoneNo);
				tfPhoneNo=new JTextField(15);
				datacont.add(tfPhoneNo);
				lBDate=new JLabel("Date:");
				datacont.add(lBDate);
				tfBDate=new JTextField(10);
				datacont.add(tfBDate);

				
				button1=new JButton("Create");
				cListener cl=new cListener();
				button1.addActionListener(cl);
				datacont.add(button1);


			    
				Object[] columns={"Product","Price","Quantity","Total Price"};
				DefaultTableModel dTableModel= new DefaultTableModel(databaseResults,columns)
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
				JTable table = new JTable(dTableModel);
				Connection conn=null;
				try
					{
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/samp_db","root","sonja");
						Statement sqlState = conn.createStatement();
						String selectStuff="select b.prod pname,b.price pprice,c.qty pnos,c.qty*b.price ptprice from ";
							   selectStuff+=" productid b,cbill c where b.prodcode=c.prodcode";
						ResultSet rows =  sqlState.executeQuery(selectStuff);
						Object[] tempRow;
						while(rows.next())
							{
								tempRow= new Object[]{rows.getString(1),rows.getInt(2),rows.getInt(3),rows.getInt(4)};
								dTableModel.addRow(tempRow);

							}
					}
				catch(SQLException e)
					{
						e.printStackTrace();
					}
				catch(ClassNotFoundException e)
					{
						e.printStackTrace();
					}

				table.setFont(new Font("Serif",Font.PLAIN,26));
				table.setRowHeight(table.getRowHeight()+16);
				table.setAutoCreateRowSorter(true);
				JScrollPane scrollPane = new JScrollPane(table);


				/*								
				String[][] colvals={
								{"B01","Brown Bread","1","35","35"},
								{"B02","Anavar","2","245","490"}
							   };
			    
				String[] colNames={"Product code","Product Name","Quantity","Price","Total Price"};
				JTable j = new JTable(colvals, colNames); 
				 j.setBounds(30, 40, 200, 300);
				 JScrollPane scrollPane = new JScrollPane(j); 
				*/


				masterpanel=new JPanel();
				masterpanel.setLayout(new GridBagLayout());




				addComp(masterpanel,datacont,0,1,5,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
				//addComp(masterpanel,button1,1,2,0,0,GridBagConstraints.WEST,GridBagConstraints.NONE);
				addComp(masterpanel,scrollPane,0,2,5,2,GridBagConstraints.WEST,GridBagConstraints.NONE);


				this.add(masterpanel);
			    this.pack();

				this.setVisible(true);
				tfPhoneNo.requestFocus();
			}

		private void addComp(JPanel thePanel,JComponent comp,int xPos,int yPos,int compWidth,int compHeight
							,int place,int stretch)
			{
				GridBagConstraints gridConstraints = new GridBagConstraints();
				gridConstraints.gridx = xPos;
				gridConstraints.gridy = yPos;
				gridConstraints.gridwidth = compWidth;
				gridConstraints.gridheight =compHeight;
				gridConstraints.weightx = 100;
				gridConstraints.weighty = 100;
				gridConstraints.insets= new Insets(5,5,5,5);//This is padding
				gridConstraints.anchor= place; //This can be North South West East
				gridConstraints.fill = stretch;//can be none horizontal vertical
				thePanel.add(comp,gridConstraints);
			}

		private class cListener implements ActionListener
				{
				public void actionPerformed(ActionEvent e)
					{//JOptionPane.showMessageDialog(sm2.this,"action","Info",JOptionPane.INFORMATION_MESSAGE);
						if (e.getSource()==button1)
							{
								String loc1CustName,loc1PhoneNo,loc1BDate,loc1billid;
								//java.util.Date dateBirthDate;
								//java.sql.Date sqlBirthDate;
								//JTextField tfCustName,tfPhoneNo,tfBDate;
								loc1CustName=tfCustName.getText();
								loc1PhoneNo=tfPhoneNo.getText();
								loc1BDate=tfBDate.getText();
								
								loc1billid="b01";

								SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
					JOptionPane.showMessageDialog(sm2.this,"Button Pressed","Info",JOptionPane.INFORMATION_MESSAGE);
					/*
						try
							{
							dateBirthDate = dateFormatter.parse(loc1BDate);
							sqlBirthDate  = new java.sql.Date(dateBirthDate.getTime());
						

							}
						catch(ParseException ex)
							{
							ex.printStackTrace();
							}
						*/		
						try
									{
						Class.forName("com.mysql.jdbc.Driver");
						Connection conn=null; 
						conn = DriverManager.getConnection("jdbc:mysql://localhost/samp_db","root","sonja");
						Statement sqlState = conn.createStatement();
						//String selectStuff="insert into billevent values('"+loc1CustName+"','"+loc1PhoneNo+"','"+loc1BDate+"','"+loc1billid+"')";
						
						String insertStuff="insert into billevent values(?,?,?,?)";
						PreparedStatement preparedStmt = conn.prepareStatement(insertStuff);
						//java.util.Date sqlBirthDate2=getADate(loc1BDate);
						preparedStmt.setString (1, loc1CustName);
      					preparedStmt.setString (2, loc1PhoneNo);
      					preparedStmt.setDate (3, getADate(loc1BDate));
      					preparedStmt.setString (4, loc1billid);	

       					preparedStmt.execute();
						
									}

								catch(SQLException e2)
									{
									e2.printStackTrace();
									}
								catch(ClassNotFoundException e2)
									{
									e2.printStackTrace();
									}
							}
					}
				}

		public static java.sql.Date getADate(String sDate){
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateBirthDate;
		java.sql.Date sqlBirthDate;
		try 
			{
			dateBirthDate = dateFormatter.parse(sDate);
			//java.sql.Date sqlBirthDate;
			sqlBirthDate = new java.sql.Date(dateBirthDate.getTime());
			
		} 
		catch (ParseException e1) {
			
			e1.printStackTrace();
		}
		
		return sqlBirthDate;
		
	}
	}