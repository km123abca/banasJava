import javax.swing.JFrame;
import java.io.*;
import javax.swing.*;

public class Lesson31 extends JFrame
	{
		static String filePath,parentDirectory;
		static File randomDir,randomFile,randomFile2;
		public static void main(String[] args)
			{
				randomDir=new File("files");
				randomDir.mkdir();
				randomFile= new File("random.txt");
				randomFile2=new File("files/random2.txt");
				try
					{
						randomFile.createNewFile();
						randomFile2.createNewFile();
						filePath=randomFile.getCanonicalPath();

					}
				catch(IOException e)
					{
						e.printStackTrace();
					}
				if(randomFile.exists())
					{
						System.out.println("File exists");
						System.out.println("File Readable:"+randomFile.canRead());
						System.out.println("File Writable:"+randomFile.canWrite());
						try{
						System.out.println("File Location"+randomFile.getCanonicalPath());
							}
						catch(IOException e)
							{
						e.printStackTrace();
							}
						System.out.println("File Name:"+randomFile.getName());
						System.out.println("Parent Directory:"+randomFile.getParent());
						parentDirectory= randomFile2.getParent();
						System.out.println("File2 Parent: "+parentDirectory);
						System.out.println("Is it a Directory:"+randomDir.isDirectory());
						String[] filesInDir = randomDir.list();
						System.out.println("Files in Directory\n");
						Boolean a=true;
						for (String fileName:filesInDir)
							{
								a=false;
								System.out.println(fileName+"\n");
							}
						if (a) System.out.println("\nNo files found\n");
						System.out.println("\nIs a File:"+randomFile.isFile());
						System.out.println("Is Hidden "+randomFile.isHidden());
						System.out.println("Last Modified "+randomFile.lastModified());
						System.out.println("File Size:"+randomFile.length());
						randomFile2.renameTo(new File("Files/lmn.txt"));

						new Lesson31();


						//disp();
						//JOptionPane.showMessageDialog(Lesson31.this,outputString,"Info",JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						System.out.println("File doesn't exist");
					}

				if (randomFile.delete())
						{
							System.out.println("File Deleted");
						}
				File[] filesInDir=randomDir.listFiles();
				for (File fname:filesInDir)
					{
						fname.delete();
					}
				if (randomDir.delete())
						{
							System.out.println("Directory Deleted");
						}

			}

		public Lesson31()
			{
				JFileChooser fileChooser= new JFileChooser(randomDir);
				fileChooser.showOpenDialog(this);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		private void disp()
			{
				String outputString="File exists";
			    JOptionPane.showMessageDialog(Lesson31.this,outputString,"Info",JOptionPane.INFORMATION_MESSAGE);
			}
	}