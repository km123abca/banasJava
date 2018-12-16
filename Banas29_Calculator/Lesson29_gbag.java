import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.*;


public class Lesson29_gbag extends JFrame
	{
		JButton but1,but2,but3,but4,but5,but6,
				but7,but8,but9,but0,butPlus,butMinus,
				clearAll;
		JButton butmul,butdiv,buteq;
		JTextField textResult;
		int opressed=0,a=0,b=0,c,x=0;
		String oper,infoOnComponent;


		public static void main(String[] args)
			{
				new Lesson29_gbag();
			}

		public Lesson29_gbag()
			{
				this.setSize(300,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Calculator");
				JPanel thePanel = new JPanel();
				thePanel.setLayout(new GridBagLayout()); //0-->as many rows,3cols,2 horiz gap,2 vert gap
				GridBagConstraints gridConstraints = new GridBagConstraints();


				gridConstraints.gridx = 1;
				gridConstraints.gridy = 1;
				gridConstraints.gridwidth = 1;
				gridConstraints.gridheight =1;
				gridConstraints.weightx = 50;
				gridConstraints.weighty = 100;
				gridConstraints.insets= new Insets(5,5,5,5);//This is padding
				gridConstraints.anchor= GridBagConstraints.CENTER; //This can be North South West East
				gridConstraints.fill = GridBagConstraints.BOTH;//can be none horizontal vertical

				textResult = new JTextField("0",20);

				Font font = new Font("Helvetica",Font.PLAIN,18);
				textResult.setFont(font);

				clearAll = new JButton("("); thePanel.add(clearAll,gridConstraints);
				gridConstraints.gridwidth = 20;
				gridConstraints.gridx =5;
				thePanel.add(textResult,gridConstraints);

				gridConstraints.gridwidth=1;
				gridConstraints.gridx=1;
				gridConstraints.gridy=2;

				but1 = new JButton("1"); thePanel.add(but1,gridConstraints);
				gridConstraints.gridx=5;
				but2 = new JButton("2"); thePanel.add(but2,gridConstraints);
				gridConstraints.gridx=9;
				but3 = new JButton("3"); thePanel.add(but3,gridConstraints);
				gridConstraints.gridx=1;
				gridConstraints.gridy=3;
				but4 = new JButton("4"); thePanel.add(but4,gridConstraints);
				gridConstraints.gridx=5;
				but5 = new JButton("5"); thePanel.add(but5,gridConstraints);
				gridConstraints.gridx=9;
				but6 = new JButton("6"); thePanel.add(but6,gridConstraints);
				gridConstraints.gridx=1;
				gridConstraints.gridy=4;
				but7 = new JButton("7"); thePanel.add(but7,gridConstraints);
				gridConstraints.gridx=5;
				but8 = new JButton("8"); thePanel.add(but8,gridConstraints);
				gridConstraints.gridx=9;
				but9 = new JButton("9"); thePanel.add(but9,gridConstraints);
				gridConstraints.gridy=5;
				gridConstraints.gridx=1;
				but0 = new JButton("0"); thePanel.add(but0,gridConstraints);
				gridConstraints.gridx=5;
				butPlus= new JButton("+"); thePanel.add(butPlus,gridConstraints);
				gridConstraints.gridx=9;
				butMinus= new JButton("- "); thePanel.add(butMinus,gridConstraints);
				gridConstraints.gridy=6;
				gridConstraints.gridx=1;
				butmul = new JButton("x"); thePanel.add(butmul,gridConstraints);
				gridConstraints.gridx=5;
				butdiv= new JButton("/"); thePanel.add(butdiv,gridConstraints);
				gridConstraints.gridx=9;
				buteq= new JButton("="); thePanel.add(buteq,gridConstraints);

				ListenForButton Lb=new ListenForButton();
				but0.addActionListener(Lb);
				but1.addActionListener(Lb);
				but2.addActionListener(Lb);
				but3.addActionListener(Lb);
				but4.addActionListener(Lb);
				but5.addActionListener(Lb);
				but6.addActionListener(Lb);
				but7.addActionListener(Lb);
				but8.addActionListener(Lb);
				but9.addActionListener(Lb);
				butPlus.addActionListener(Lb);
				butMinus.addActionListener(Lb);
				butmul.addActionListener(Lb);
				butdiv.addActionListener(Lb);
				buteq.addActionListener(Lb);


				this.add(thePanel);
				this.setVisible(true); 

			}

			private class ListenForButton implements ActionListener
				{
				public void actionPerformed(ActionEvent e)
					{
					if (
						(e.getSource()==but0)||(e.getSource()==but1)||(e.getSource()==but2)||(e.getSource()==but3)
						||(e.getSource()==but4)||(e.getSource()==but5)||(e.getSource()==but6)||(e.getSource()==but7)
					   ||(e.getSource()==but8)||(e.getSource()==but9)
					   )
						{   
							infoOnComponent="";

							if (e.getSource()==but0) x=0;
							else if (e.getSource()==but1) x=1;
							else if (e.getSource()==but2) x=2;
							else if (e.getSource()==but3) x=3;
							else if (e.getSource()==but4) x=4;
							else if (e.getSource()==but5) x=5;
							else if (e.getSource()==but6) x=6;
							else if (e.getSource()==but7) x=7;
							else if (e.getSource()==but8) x=8;
							else if (e.getSource()==but9) x=9;
							


							if (opressed==0)
							{a=10*a+x;textResult.setText(String.valueOf(a));}
							else
							{b=10*b+x;textResult.setText(String.valueOf(b));}

						/*
						infoOnComponent=(String)e.getSource();
						JOptionPane.showMessageDialog(Lesson29_gbag.this,
													  infoOnComponent,
													  "Information",
													  JOptionPane.INFORMATION_MESSAGE
													  );*/
						}
					else if (e.getSource()==clearAll)
					    {
					    	a=0;b=0;c=0;opressed=0;textResult.setText("0");
					    	
						infoOnComponent="hey";
						JOptionPane.showMessageDialog(Lesson29_gbag.this,
													  infoOnComponent,
													  "Information",
													  JOptionPane.INFORMATION_MESSAGE
													  );
					    }
					else if (
						    (e.getSource()==butPlus)||(e.getSource()==butMinus)
						    ||(e.getSource()==butmul)||(e.getSource()==butdiv)
						    )
						{   opressed=1;
							if (e.getSource()==butPlus) oper="plus";
							else if (e.getSource()==butMinus) oper="minus";
							else if (e.getSource()==butmul) oper="mult";
							else if (e.getSource()==butdiv) oper="div";
						}
					else if (e.getSource()==buteq)
						{
							if (oper=="plus")  c=a+b;
						else if(oper=="minus") c=a-b;
						else if(oper=="mult")  c=a*b;
						else if(oper=="div")   c=a/b;
						textResult.setText(String.valueOf(c));
						a=0;b=0;c=0;opressed=0;
						} 

					}
				}

	}