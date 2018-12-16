import javax.swing.*;

import java.awt.event.*;

public class Lesson25 extends JFrame
	{
		JButton button1;
		String infoOnComponent="";
		JList favouriteMovies,favouriteColors;
		DefaultListModel defListModel = new DefaultListModel();
		JScrollPane scroller;

		public static void main(String[] args)
			{
				new Lesson25();
			}

		public Lesson25()
			{
				this.setSize(400,400);
				this.setLocationRelativeTo(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setTitle("My fifth Frame");
				JPanel thePanel=new JPanel();
				button1=new JButton("Get Answer");
				ListenForButton lForButton= new ListenForButton();
				button1.addActionListener(lForButton);
				thePanel.add(button1);

				String[] movies={"Matrix","Minority Report","Big"};
				favouriteMovies=new JList(movies);
				favouriteMovies.setFixedCellHeight(30);
				favouriteMovies.setFixedCellWidth(150);
				favouriteMovies.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

				String[] colors ={"Black","Blue","White","Green","Orange","Gray"};

				for (String color:colors)
					{
				defListModel.addElement(color);
					}
				defListModel.add(2,"Purple");
				favouriteColors= new JList(defListModel);
				favouriteColors.setVisibleRowCount(4);
				scroller = new JScrollPane(favouriteColors,
											JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
											JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
				favouriteColors.setFixedCellHeight(30);
				favouriteColors.setFixedCellWidth(150);

				thePanel.add(favouriteMovies);
				thePanel.add(scroller);


				this.add(thePanel);
				this.setVisible(true); 
			}

			private class ListenForButton implements ActionListener
				{
				public void actionPerformed(ActionEvent e)
					{
					if (e.getSource()==button1)
						{
							if (defListModel.contains("Black") ) infoOnComponent+="Black is here\n";
							if (!defListModel.isEmpty() ) infoOnComponent+="is'nt empty\n";
							infoOnComponent+="Elements in the List"+defListModel.size()+"\n";
							infoOnComponent+="Last Element"+defListModel.lastElement()+"\n";
							infoOnComponent+="First Element"+defListModel.firstElement()+"\n";
							infoOnComponent+="In Index 1 "+defListModel.get(1)+"\n";

							//defListModel.remove(0);
							//defListModel.removeElement("Big");
							Object[] arrayOflist = defListModel.toArray();

							for (Object color:arrayOflist)
									{
									infoOnComponent+=color+"\n";
									}

							JOptionPane.showMessageDialog(Lesson25.this,infoOnComponent,"Information",JOptionPane.INFORMATION_MESSAGE);
							infoOnComponent="";
						}
					}
				}
	}