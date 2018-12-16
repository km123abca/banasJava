import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class puzbox extends JFrame
		{
			static JLabel linput1,linput2,loutput1,loutput2;
			static JTextField tfinput1,tfinput2,tfoutput1,tfoutput2;
			static JPanel fullpanel,fullpanel2;
			static Box inpBox1,inpBox2,outBox1,outBox2;
			static JButton button1;

			public static void main(String[] args)
					{
				new puzbox();

					}

			public puzbox()
					{
					this.setLocationRelativeTo(null);
					this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					this.setTitle("Puzzles");
					this.setSize(500,500);

					fullpanel=new JPanel();
					fullpanel.setLayout(new GridBagLayout());
					inpBox1 = Box.createHorizontalBox();
					inpBox2 = Box.createHorizontalBox();
					outBox1 = Box.createHorizontalBox();

					
					linput1=createlabel("input1:",inpBox1);
					tfinput1=createtf(15,inpBox1);
					linput2=createlabel("input2:",inpBox2);
					tfinput2=createtf(15,inpBox2);

					button1=new JButton("Compute");
					button1.addActionListener(
						new ActionListener()
								{
									public void actionPerformed(ActionEvent e)
										{
											calc();
										}
								}
											 );


					loutput1=createlabel("output1:",outBox1);
					tfoutput1=createtf(15,outBox1);
					loutput2=createlabel("output2:",outBox1);
					tfoutput2=createtf(15,outBox1);


					inpBox1.setBorder(BorderFactory.createTitledBorder("input"));
					inpBox2.setBorder(BorderFactory.createTitledBorder("input"));
					outBox1.setBorder(BorderFactory.createTitledBorder("output"));
					addComp(fullpanel,inpBox1,0,0,2,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
					addComp(fullpanel,inpBox2,0,1,2,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
					addComp(fullpanel,button1,0,2,2,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
					addComp(fullpanel,outBox1,0,3,2,1,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);
					this.add(fullpanel);
					this.setVisible(true);
					}


					private void calc()
						{
							announce("Hey");
							String intex=tfinput1.getText();
							for (String a:intex.)
						}

					private void announce(String stuff)
						{
					JOptionPane.showMessageDialog(puzbox.this,stuff,"Info",JOptionPane.INFORMATION_MESSAGE);
						}

					private static JLabel createlabel(String nm,Box dest)
							{
								JLabel templab=new JLabel(nm);
								dest.add(templab);
								return templab;
								
								
							}
					private static JTextField createtf(int siz,Box dest)
							{
								JTextField templab=new JTextField(siz);
								dest.add(templab);
								return templab;
								
								
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
		}