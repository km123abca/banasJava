import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class JavaLessonTwentyone extends JFrame
	{
	JButton button1;
	JTextField textField1;
	JTextArea textArea1;
	int buttonClicked=0;

	public static void main(String[] args)
		{
		new JavaLessonTwentyone();
		}
	public JavaLessonTwentyone()
		{
	this.setSize(400,400);
	Toolkit tk=Toolkit.getDefaultToolkit();
	Dimension dim=tk.getScreenSize();

	int xpos= (dim.width/2)-(this.getWidth()/2);
	int ypos= (dim.height/2)-(this.getHeight()/2);
	this.setLocation(xpos,ypos);	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("My Second Frame");

	JPanel thePanel= new JPanel();

	button1= new JButton("Click Here");
	ListenForButton lForButton = new ListenForButton();
	button1.addActionListener(lForButton);
	thePanel.add(button1);

	textField1= new JTextField("",15);
	ListenForKeys lForKeys=new ListenForKeys();
	textField1.addKeyListener(lForKeys);
	thePanel.add(textField1);

	textArea1= new JTextArea(15,20);
	textArea1.setText("Tracking Events\n");
	textArea1.setLineWrap(true);
	textArea1.setWrapStyleWord(true);
	JScrollPane scrollbar1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	thePanel.add(scrollbar1);
	//thePanel.add(textArea1);

	ListenForMouse lForMouse= new ListenForMouse();
	thePanel.addMouseListener(lForMouse);



	this.add(thePanel);

	ListenForWindow lForWindow= new ListenForWindow();
	this.addWindowListener(lForWindow);

	this.setVisible(true);




		}
	


	private class ListenForButton implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
				{
				if (e.getSource()==button1)
					{
						buttonClicked++;
						textArea1.append("Button clicked " + buttonClicked + " times\n" );
						
					}
				}
		}

	private class ListenForKeys implements KeyListener
		{
			public void keyPressed(KeyEvent e)
			{
				textArea1.append("Key Hit:"+e.getKeyChar()+"\n");
			}

			public void keyReleased(KeyEvent e)
			{
				
			}

			public void keyTyped(KeyEvent e)
			{
				
			}



		}

	private class ListenForWindow implements WindowListener
		{


		public void windowActivated(WindowEvent e)
			{
				textArea1.append("Window is active\n");
			}

		public void windowClosed(WindowEvent e)
			{
				textArea1.append("Window is active\n");
			}

		public void windowClosing(WindowEvent e)
			{
				textArea1.append("Window is active\n");
			}

		public void windowDeactivated(WindowEvent e)
			{
				textArea1.append("Window is not active\n");
			}
		public void windowDeiconified(WindowEvent e)
			{
				textArea1.append("Window is in normal state\n");
			}
		public void windowIconified(WindowEvent e)
			{
				textArea1.append("Window is minimized\n");
			}

		public void windowOpened(WindowEvent e)
			{
				textArea1.append("Window is Created\n");
			}
		}

	private class ListenForMouse implements MouseListener
		{
			public void mouseClicked(MouseEvent e)
				{
					textArea1.append("Mouse Panel pos:"+e.getX()+" "+e.getY()+"\n");
					textArea1.append("Mouse Screen pos:"+e.getXOnScreen()+" "+e.getYOnScreen()+"\n");
					textArea1.append("Mouse Button:"+e.getButton()+"\n");
					textArea1.append("Mouse click:"+e.getClickCount()+"\n");
				}

			public void mouseEntered(MouseEvent e)
				{
					
				}

			public void mouseExited(MouseEvent e)
				{
					
				}
			public void mousePressed(MouseEvent e)
				{
					
				}
			public void mouseReleased(MouseEvent e)
				{
					
				}
		}

	}