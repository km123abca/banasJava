import javax.swing.*;
import java.awt.event.*;//Hey Kitchu, this * thingy was not importing changeevent
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.SpinnerDateModel;
import java.util.Calendar;
import java.util.Enumeration;

import javax.swing.tree.*;

public class Lesson27 extends JFrame
	{
		JButton button1;
		JSpinner spinner1,spinner2,spinner3,spinner4;
		String outputString="";
		JTree theTree;

		DefaultMutableTreeNode documents,work,games,emails;
 
 		DefaultMutableTreeNode fileSystem= new DefaultMutableTreeNode("C Drive"); 
		public static void main(String[] args)
			{
				new Lesson27();
			}
		public Lesson27()
			{
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("My Sixth Frame");
		JPanel thePanel= new JPanel();

		button1=new JButton("Get Answer");
		ListenForButton lForButton= new ListenForButton();
		button1.addActionListener(lForButton);
		thePanel.add(button1);

		theTree =new JTree(fileSystem);
		theTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		theTree.setVisibleRowCount(8);
		documents =  addAFile("Docs",fileSystem);

		addAFile("Taxes.exl",documents);
		addAFile("story.txt",documents);
		emails=addAFile("Emails",documents);
		addAFile("Schedule.txt",documents);

		addAFile("CallBob.txt",emails);

		JScrollPane scrollBox=new JScrollPane(theTree);
		Dimension d=scrollBox.getPreferredSize();

		d.width=200;
		scrollBox.setPreferredSize(d);
		thePanel.add(scrollBox);

		this.add(thePanel);
		this.setVisible(true);

			}

		private class ListenForButton implements ActionListener
			{
			public void actionPerformed(ActionEvent e)
				{
					if (e.getSource()==button1)
					{
						outputString="";
						Object treeObject = theTree.getLastSelectedPathComponent();
						DefaultMutableTreeNode theFile=(DefaultMutableTreeNode) treeObject;
						String treeNode= (String) theFile.getUserObject();
						outputString += "The Selected Node:"+treeNode+"\n";
						outputString += "Number of Children:"+theFile.getChildCount()+"\n";
						outputString += "Number of Siblings:"+theFile.getSiblingCount()+"\n";
						outputString += "Parent of the Node:"+theFile.getParent()+"\n";
						outputString += "Get NextNode:"+theFile.getNextNode()+"\n";
						outputString += "Get Previous Node:"+theFile.getPreviousNode()+"\n";

						outputString += "\nChildren of Node\n";

						for(Enumeration enumValue=theFile.children();enumValue.hasMoreElements();)
								{
									outputString+=enumValue.nextElement()+"\n";
								}
						outputString+="\nPath from Root\n";
						TreeNode[] pathNodes= theFile.getPath();
						for (TreeNode indivNodes:pathNodes)
							{
							outputString+=indivNodes+"\n";
							}
						JOptionPane.showMessageDialog(Lesson27.this,outputString,"Info",JOptionPane.INFORMATION_MESSAGE);

					}
				}	
			}



			

		


		private DefaultMutableTreeNode addAFile(String fileName,DefaultMutableTreeNode parentFolder)
			{
				DefaultMutableTreeNode newFile =new DefaultMutableTreeNode(fileName);
				parentFolder.add(newFile);
				return newFile;
			}			

	}
