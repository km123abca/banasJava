import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
// Needed to track when the user clicks on a table cell

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//The API for accessing and processing data stored in a database

import java.sql.*;
import java.text.ParseException;

// Allows you to convert from string to date or vice versa

import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

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
public class prep extends JFrame
	{

		JButton button1,button2;
		JLabel lCustName,lPhoneNo,lBDate;
		JLabel lprodcode,lprodname,lqty;
		JTextField tfprodcode,tfprodname,tfqty;
		JTextField tfCustName,tfPhoneNo,tfBDate;
		Date todaysDate;
		JPanel datacont,masterpanel,datacont2;
		static Object[][] databaseResults;
		static java.util.Date dateBirthDate;
		static java.sql.Date sqlBirthDate;
		int activBillId;
		public static void main(String[] args)
			{
				new prep();

			}
		public prep()
			{   
				this.setLocationRelativeTo(null);				
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Billing Software");
				datacont=new JPanel();
				datacont2=new JPanel();
				datacont.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
				datacont2.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));

				lprodcode=new JLabel("Product Code:");
				datacont2.add(lprodcode);
				tfprodcode=new JTextField(15);
				datacont2.add(tfprodcode);
				lprodname=new JLabel("Product:");
				datacont2.add(lprodname);
				tfprodname=new JTextField(15);
				datacont2.add(tfprodname);
				lqty=new JLabel("Quantity:");
				datacont2.add(lqty);
				tfqty=new JTextField(15);
				datacont2.add(tfqty);
				button2=new JButton("Add");
				cListener c2=new cListener();
				button2.addActionListener(c2);
				datacont2.add(button2);


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

				masterpanel=new JPanel();
				masterpanel.setLayout(new GridBagLayout());
				addComp(masterpanel,datacont,0,1,5,1,GridBagConstraints.WEST,GridBagConstraints.NONE);
				addComp(masterpanel,datacont2,0,2,5,1,GridBagConstraints.WEST,GridBagConstraints.NONE);

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

		private static PrintWriter createFile(String fileName)
			{
				try
					{
						File listOfNames =new File(fileName);
						PrintWriter infoToWrite= new PrintWriter(
																new BufferedWriter(new FileWriter(listOfNames,true))
																);
						return infoToWrite;
					}
				catch(IOException e)
					{
						System.out.println("An I/O error occurred");
						System.exit(0);
					}
				return null;
			}
		private static void w2debug(String stx)
			{
				PrintWriter debugOutput=createFile("debug.txt");
				debugOutput.println(stx);
				debugOutput.close();
			}

		private class cListener implements ActionListener
				{
				public void actionPerformed(ActionEvent e)
					{//JOptionPane.showMessageDialog(sm2.this,"action","Info",JOptionPane.INFORMATION_MESSAGE);
						if (e.getSource()==button1)
							{
								String loc1CustName,loc1PhoneNo,loc1BDate;
								int loc1billid;
			
								loc1CustName=tfCustName.getText();
								loc1PhoneNo=tfPhoneNo.getText();
								loc1BDate=tfBDate.getText();								
								loc1billid=5;
								sqlBirthDate = getADate(loc1BDate);
								
					JOptionPane.showMessageDialog(prep.this,"Button Pressed","Info",JOptionPane.INFORMATION_MESSAGE);

					try
						{
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/samp_db","root","sonja");
							String insertStuff="insert into billevent values(?,?,?,?)";
							String selMax="select max(billid) bd from billevent";							
							PreparedStatement p1=conn.prepareStatement(selMax);
							ResultSet rs1=p1.executeQuery();
							rs1.next();
							loc1billid=rs1.getInt(1)+1;
							p1=conn.prepareStatement(insertStuff);
							p1.setString(1,loc1CustName);
							p1.setString(2,loc1PhoneNo);
							p1.setInt(4,loc1billid);
							p1.setDate(3,sqlBirthDate);
							p1.executeUpdate();

							
						}
					catch (SQLException ex) 
						{
                        System.out.println("SQLException: " + ex.getMessage());
           				System.out.println("VendorError: " + ex.getErrorCode());
        				} 
                	catch (ClassNotFoundException ex) 
                		{
						ex.printStackTrace();
						} 



									
				//sm2directupdate.this.setVisible(false);
				//sm2directupdate.this.setVisible(true);		
							}


					if (e.getSource()==button2)
						{
							Connection conn=null;
							//on button press2 first thing to do is to find the product price and name from
							//product code
							String locProdcode=tfprodcode.getText();
							int locqty=Integer.parseInt(tfqty.getText());
							try
								{
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/samp_db","root","sonja");
						Statement sqlState = conn.createStatement();
						String selectStuff="insert into cbill values(?,?,?)";
						PreparedStatement preparedStmt = conn.prepareStatement(selectStuff);
						preparedStmt.setInt (1, activBillId);
						preparedStmt.setString(2,locProdcode);
						preparedStmt.setInt(3,locqty);
						preparedStmt.execute();   
						
							
								}
							catch(SQLException e32)
								{   
						System.out.println("here at 226");
						e32.printStackTrace();
								}
							catch(ClassNotFoundException e32)
								{
						e32.printStackTrace();
								}


							//bill starts here

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
				
				try
					{
						Class.forName("com.mysql.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/samp_db","root","sonja");
						Statement sqlState = conn.createStatement();
						String selectStuff="select b.prod pname,b.price pprice,c.qty pnos,c.qty*b.price ptprice from ";
							   selectStuff+=" productid b,cbill c where b.prodcode=c.prodcode and c.billid="+activBillId;
							   w2debug(selectStuff+'\n');
		JOptionPane.showMessageDialog(prep.this,activBillId,"Info",JOptionPane.INFORMATION_MESSAGE);

			
						//PreparedStatement preparedStmt = conn.prepareStatement(selectStuff);
						//preparedStmt.setInt (1, activBillId);
						//preparedStmt.execute();


						ResultSet rows =  sqlState.executeQuery(selectStuff);
						//ResultSet rows=preparedStmt.execute();
						Object[] tempRow;
						int m=1;
						while(rows.next())
							{ w2debug("Entering for the "+Integer.toString(m)+" time");
							  m+=1;
								tempRow= new Object[]{rows.getString(1),rows.getInt(2),rows.getInt(3),rows.getInt(4)};
								dTableModel.addRow(tempRow);

							}
					}
				catch(SQLException e22)
					{   System.out.println("here at 226");
						e22.printStackTrace();
					}
				catch(ClassNotFoundException e22)
					{
						e22.printStackTrace();
					}

				table.setFont(new Font("Serif",Font.PLAIN,26));
				table.setRowHeight(table.getRowHeight()+16);
				table.setAutoCreateRowSorter(true);
				JScrollPane scrollPane = new JScrollPane(table);

				
				addComp(masterpanel,scrollPane,0,3,5,2,GridBagConstraints.WEST,GridBagConstraints.NONE);


						}
					}
				}


					public static java.sql.Date getADate(String sDate)
						{
					SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
					try 	{
						dateBirthDate = dateFormatter.parse(sDate);
						sqlBirthDate = new java.sql.Date(dateBirthDate.getTime());
							} 
					catch (ParseException e1) 
							{
			
						e1.printStackTrace();
							}
		
						return sqlBirthDate;
		
						}
		
	}