import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Lesson28_3 extends JFrame
	{
		JButton button1,button2,button3,button4,button5,button6;

		public static void main(String[] args)
			{
				new Lesson28_3();
			}

		public Lesson28_3()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("My 7th Frame");

				Box theBox = Box.createHorizontalBox();

				button1=new JButton("Button1");	button2=new JButton("Button2");	
				button3=new JButton("Button3");	button4=new JButton("Button4");
				button5=new JButton("Button5");	
/*
				theBox.add(button1); theBox.add(Box.createHorizontalStrut(4));
				theBox.add(button2); theBox.add(Box.createHorizontalStrut(4));
				theBox.add(button3); theBox.add(Box.createHorizontalGlue());
				theBox.add(button4);
*/
				theBox.add(button1); theBox.add(Box.createRigidArea(new Dimension(30,40)));
				theBox.add(button2); theBox.add(Box.createRigidArea(new Dimension(30,40)));
				theBox.add(button3); theBox.add(Box.createRigidArea(new Dimension(30,40)));
				theBox.add(button4);	
				

				this.add(theBox);
				this.setVisible(true); 

			}
	}