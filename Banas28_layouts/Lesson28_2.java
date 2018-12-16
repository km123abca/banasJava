import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Lesson28_2 extends JFrame
	{
		JButton button1,button2,button3,button4,button5,button6;

		public static void main(String[] args)
			{
				new Lesson28_2();
			}

		public Lesson28_2()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("My 7th Frame");

				JPanel thePanel= new JPanel();
				JPanel thePanel2=new JPanel();
				//thePanel.setLayout(new FlowLayout(FlowLayout.LEFT,30,60)); //allignment,horispace,vertspace

				thePanel.setLayout(new BorderLayout());

				button1= new JButton("button1");button2= new JButton("button2");
				button3= new JButton("button3");button4= new JButton("button4");
				button5= new JButton("button5");button6= new JButton("button6");

				//thePanel.add(button1);thePanel.add(button2);thePanel.add(button3);
				//thePanel.add(button4);thePanel.add(button5);thePanel.add(button6);

				thePanel2.add(button1);
				thePanel2.add(button2);

				thePanel.add(thePanel2,BorderLayout.NORTH);				
				

				this.add(thePanel);
				this.setVisible(true); 

			}
	}