import javax.swing.*;
import java.awt.event.*;

public class tone extends JFrame
	{
		JComboBox favouriteShows;
		JButton button1;
		String infoOnComponent="";

		public static void main(String[] args)
			{
				new tone();
			}
		public tone()
			{	
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("My Fourth Frame");
				JPanel thePanel=new JPanel();

				String[] shows = {"Breaking Bad","Life on Mars","Doctor Who"};
				favouriteShows= new JComboBox(shows);
				favouriteShows.addItem("Pushing Daisies");
				thePanel.add(favouriteShows);

				button1=new JButton("Get Answer");
				ListenForButtons  lForButton =new ListenForButtons();
				button1.addActionListener(lForButton);
				thePanel.add(button1);

				this.add(thePanel);
				this.setVisible(true);

				favouriteShows.insertItemAt("Dexter",1);
				favouriteShows.setMaximumRowCount(3);
				favouriteShows.removeItem("Doctor Who");
				favouriteShows.removeItemAt(1);
			}

		private class ListenForButtons implements ActionListener
			{
				public void actionPerformed(ActionEvent e)
					{
						if (e.getSource()==button1)
							{
								favouriteShows.setEditable(true);
								infoOnComponent+="Item at 0:"+favouriteShows.getItemAt(0)+"\n";
								infoOnComponent+="Num of Shows:"+favouriteShows.getItemCount()+"\n";
								infoOnComponent+="Selected ID:"+favouriteShows.getSelectedIndex()+"\n";
								infoOnComponent+="Selected value:"+favouriteShows.getSelectedItem()+"\n";
								infoOnComponent+="Are Editable:"+favouriteShows.isEditable()+"\n";

								JOptionPane.showMessageDialog(tone.this,infoOnComponent,"Information",JOptionPane.INFORMATION_MESSAGE);
								infoOnComponent="";
							}
					}
			}
	}