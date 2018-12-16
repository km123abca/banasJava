import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class sm extends JFrame
	{

		JButton button1;
		public static void main(String[] args)
			{
				new sm();
			}

		public sm()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Welcome");

				JPanel thePanel= new JPanel();
				thePanel.setLayout(new GridBagLayout());
				button1=new JButton("Press here");
				customListener lfb=new customListener();
				button1.addActionListener(lfb);
				addComp(thePanel,button1,0,0,1,2,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);

				this.add(thePanel);
				this.pack();
				this.setVisible(true);
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

		private class customListener implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
					{
						if (e.getSource()==button1)
						{
							JOptionPane.showMessageDialog(sm.this,"Button Pressed","Info",JOptionPane.INFORMATION_MESSAGE);
							smchild insta=new smchild();
							insta.setVisible(true);
							//this.setVisible(false);
						}
					}
			}



			private class smchild extends JFrame
				{
					public smchild()
						{
							this.setSize(400,400);
							this.setLocationRelativeTo(null);
							//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							this.setTitle("Child");

							JPanel thePanel= new JPanel();
							//thePanel.setLayout(new GridBagLayout());
							button1=new JButton("Press here");
							thePanel.add(button1);
							//customListener lfb=new customListener();
							//button1.addActionListener(lfb);
							//addComp(thePanel,button1,0,0,1,2,GridBagConstraints.NORTHWEST,GridBagConstraints.NONE);

							this.add(thePanel);
							//this.pack();
							//this.setVisible(true);
						}
				}

	}