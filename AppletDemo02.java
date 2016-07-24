package effective_java;

// javac -d class_path AppletDemo02.java
// java -cp class_path effective_java.AppletDemo02

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class DrawOval extends JPanel {
	
	private int d = 10;

	public void paint(Graphics g) {
		 super.paint(g);
	   g.drawOval(10,10,d,d);
  }
  //OR: 
  //public void paintComponent(Graphics g) {
  //	super.paintComponent(g);
  //	g.fillOval(10,10,d, d);
  //}

  public void setWidth(int w) {
  	d = (w>=0) ? w : 10;
  	repaint();
  }

  public Dimension getPerferredSize() {
  	return new Dimension(200, 200);
  }

  public Dimension getMinimumSize() {
  	return getPerferredSize();
  }

}

class TheWindow extends JFrame {
	private JSlider  slider;
	private DrawOval panel;

	public TheWindow() {
		super("Title for JFrame");
		//add panel into JFrame
		panel = new DrawOval();
		panel.setBackground(Color.ORANGE);
		//add slider into JFrame
		slider = new JSlider(SwingConstants.HORIZONTAL, 0, 200, 10);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		//add listener to slider
		slider.addChangeListener(
			new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int tick = slider.getValue();
					panel.setWidth(tick);
				}
			}
		);

		add(slider, BorderLayout.SOUTH);
		add(panel, BorderLayout.CENTER);
	}
}


public class AppletDemo02 {
	public static void main(String[] args) {
		TheWindow w = new TheWindow();
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(230,280);
		w.setVisible(true);
	}
}