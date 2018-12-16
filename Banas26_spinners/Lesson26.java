import javax.swing.*;
import java.awt.event.*;//Hey Kitchu, this * thingy was not importing changeevent
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;

public class Lesson26 extends JFrame
	{
		JButton button1;
		JSpinner spinner1,spinner2,spinner3,spinner4;
		String outputString="";

		public static void main(String[] args)
			{
				new Lesson26();
			}
		public Lesson26()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("My Fifth Frame");
				JPanel thePanel= new JPanel();

				button1= new JButton("Get Answer");
				ListenForButton lForButton= new ListenForButton();
				button1.addActionListener(lForButton);
				thePanel.add(button1);

				spinner1= new JSpinner();
				thePanel.add(spinner1);

				spinner2=new JSpinner(new SpinnerNumberModel(1,1,100,2));
				thePanel.add(spinner2);

				String[] weekDays= {"Mon","Tue","Wed","Thu","Fri"};
				spinner3= new JSpinner(new SpinnerListModel(weekDays));
				Dimension d= spinner3.getPreferredSize();
				d.width=80;
				spinner3.setPreferredSize(d);

				Date todaysDate=new Date();
				spinner4= new JSpinner(new SpinnerDateModel(todaysDate,null,null,Calendar.DAY_OF_MONTH));
				JSpinner.DateEditor dateEditor=new JSpinner.DateEditor(spinner4,"dd/mm/yyyy");
				spinner4.setEditor(dateEditor);

				thePanel.add(spinner4);


				thePanel.add(spinner3);

				ListenForSpinner lForSpinner = new ListenForSpinner();
				spinner4.addChangeListener(lForSpinner);

				this.add(thePanel);
				this.setVisible(true);
			}

		private class ListenForButton implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
					{
						if (e.getSource()==button1)
						{
							outputString+="Spinner 1 Value:"+spinner1.getValue()+"\n";
							outputString+="Spinner 2 Value:"+spinner2.getValue()+"\n";
							outputString+="Spinner 3 Value:"+spinner3.getValue()+"\n";
							outputString+="Spinner 4 Value:"+spinner4.getValue()+"\n";

							JOptionPane.showMessageDialog(Lesson26.this,outputString,"Info",JOptionPane.INFORMATION_MESSAGE);
							outputString="";
						}
					}
			}

			

		private class ListenForSpinner implements ChangeListener
			{
				public void stateChanged(ChangeEvent e)
					{
					 //if (e.getSource()==spinner4)
					  {
					  	JOptionPane.showMessageDialog(Lesson26.this,"Hey","Info",JOptionPane.INFORMATION_MESSAGE);
					  }	
					}
			}

			
	}