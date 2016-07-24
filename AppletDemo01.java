package effective_java;

// javac -d class_path AppletDemo01.java

import java.awt.*;
import javax.swing.*;

public class AppletDemo01 extends JApplet {
	public void paint(Graphics g) {
		super.paint(g);
		g.drawString("Hello the world", 10, 10);
	}
}