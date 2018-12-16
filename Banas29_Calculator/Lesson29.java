import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;


public class Lesson29 extends JFrame
	{
		JButton but1,but2,but3,but4,but5,but6,
				but7,but8,but9,but0,butPlus,butMinus,
				clearAll;
		JTextField textResult;

		public static void main(String[] args)
			{
				new Lesson29();
			}

		public Lesson29()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("Calculator");
				JPanel thePanel = new JPanel();
				thePanel.setLayout(new GridLayout(0,3,2,2)); //0-->as many rows,3cols,2 horiz gap,2 vert gap

				but1 = new JButton("1"); thePanel.add(but1);
				but2 = new JButton("2"); thePanel.add(but2);
				but3 = new JButton("3"); thePanel.add(but3);
				but4 = new JButton("4"); thePanel.add(but4);
				but5 = new JButton("5"); thePanel.add(but5);
				but6 = new JButton("6"); thePanel.add(but6);
				but7 = new JButton("7"); thePanel.add(but7);
				but8 = new JButton("8"); thePanel.add(but8);
				but9 = new JButton("9"); thePanel.add(but9);
				but0 = new JButton("0"); thePanel.add(but0);
				butPlus= new JButton("+"); thePanel.add(butPlus);
				butMinus= new JButton("- "); thePanel.add(butMinus);



				this.add(thePanel);
				this.setVisible(true); 

			}
	}