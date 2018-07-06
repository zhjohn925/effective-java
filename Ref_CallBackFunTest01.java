package effective_java;

//Example in Core Jave Volume I (Listing 6.3)

// javac -d class_path Ref_CallBackFunTest01.java
// java -cp class_path effective_java.Ref_CallBackFunTest01

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
//to resolve conflict with java.util.Timer
//by explicitly specifying Timer class in swing
import javax.swing.Timer;   

public class Ref_CallBackFunTest01 
{
   public static void main(String[] args)
   {
      ActionListener listener = new TimePrinter();
      
      //construct a timer that calls the listener once every 10 seconds
      //so the callback function is called every 10 seconds
      Timer t = new Timer(10000, listener);
      t.start();
      
      JOptionPane.showMessageDialog(null, "Quit program?");
      System.exit(0);
   } 
   


}


//define the callback function (actionPerformed) 
//thru ActionListener interface 
class TimePrinter implements ActionListener {
   
   //callback function
   public void actionPerformed(ActionEvent e) 
   {
      System.out.println("At the tone, the time is " + (new Date()));
      Toolkit.getDefaultToolkit().beep();
   }
}