import java.util.Hashtable;
/*import javax.*;*/
 import javax.swing.event.ChangeListener;
 import javax.swing.event.ChangeEvent;
 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/* TO DO LIST:
1. Clean up data.
2. Add a plot for tension values over time.
3. Add commments.
*/

public class Slider extends Frame implements ActionListener
{
  private long startTime;
  private long endTime;
  private long currentTime;
  JButton button1;
  JButton button2;
  File f1 = new File("TensionData.txt");
  FileOutputStream fileOut = new FileOutputStream("TensionData.txt");
  PrintStream printer = new PrintStream(fileOut);
  ObjectOutputStream thingOut = new ObjectOutputStream(fileOut);

  public Slider() throws IOException
  {
    JFrame frame = new JFrame("Tension Scale");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container content = frame.getContentPane();
    frame.setSize(500, 500);
    frame.setVisible(true);
    JSlider slider = new JSlider(0,100);
    slider.setMinorTickSpacing(1);
    slider.setMajorTickSpacing(10);
    slider.setPaintTicks(true);
    slider.setSnapToTicks(true);
    slider.setPaintLabels(true);
    slider.setValue(0);
    content.add(slider, BorderLayout.CENTER);
    Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		position.put(0, new JLabel("0"));
		position.put(10, new JLabel("10"));
		position.put(20, new JLabel("20"));
		position.put(30, new JLabel("30"));
		position.put(40, new JLabel("40"));
		position.put(50, new JLabel("50"));
		position.put(60, new JLabel("60"));
		position.put(70, new JLabel("70"));
		position.put(80, new JLabel("80"));
		position.put(90, new JLabel("90"));
		position.put(100, new JLabel("100"));
    slider.setLabelTable(position);
    slider.addChangeListener(new ChangeListener()
    {
			public void stateChanged(ChangeEvent e)
      {
        currentTime = System.currentTimeMillis();
        //try
        //{
        String newline = System.getProperty("line.separator");
        printer.println((currentTime - startTime) + "ms:" + ((JSlider)e.getSource()).getValue() + ",");
        System.out.println("Value: " + ((JSlider)e.getSource()).getValue());
        System.out.println("Time :" + (currentTime - startTime)/1000);
        //}
        //catch (IOException i)
        //{
          //System.out.println("Error!");
        //}
			}
		});
    button1 = new JButton("Click to start recording");
    button2 = new JButton("Click to stop recording");
    button1.setVisible(true);
    button1.setSize(200, 200);
    button2.setVisible(true);
    button2.setSize(200, 200);
    content.add(button1, BorderLayout.NORTH);
    content.add(button2, BorderLayout.SOUTH);
    button1.addActionListener(this);
    button2.addActionListener(this);
    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent ev)
      {
        System.exit(0);
      }
    }
    );//Exits the program if the window is closed
  }
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource()==button1)//If an action is from button1
    {
      System.out.println("The timer will start now.");
      startTime = System.currentTimeMillis();//Saves the startTime
    }
    else
    {
      try
      {
        thingOut.close();
      }
      catch (IOException g)
      {
        System.out.println("IOException error");
      }
      System.out.println("The timer will stop now.");
      endTime = System.currentTimeMillis();
      //System.out.println("End time: " + endTime);
      System.out.println("The recording was " +(endTime - startTime)/1000 + "s.");
      //Prints out the time between clicks of button1 & button2
    }
  }

  public static void main(String [] args) throws IOException
  {
    Slider demo = new Slider();//Creates the slider
  }
}
