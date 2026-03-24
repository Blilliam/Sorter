package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;


public class AppPanel extends JPanel implements Runnable {
	static Toolkit tk = Toolkit.getDefaultToolkit();
	
	public static final int WIDTH = ((int) tk.getScreenSize().getWidth()); // sets width to screen width
	public static final int HEIGHT = ((int)tk.getScreenSize().getHeight()) - 38; // sets height to screen height
	public Dimension d = new Dimension(WIDTH, HEIGHT); //creates dementions with screen dimentions
	
	public Thread t = new Thread(this); 
	
	Table table;
	
	// Constructor
	public AppPanel() {
		setPreferredSize(d); // sets preffered panel size
		setFocusable(true); // alows you to zoom in 
		
		String input  = JOptionPane.showInputDialog("Enter number of Objects");
		int count = Integer.parseInt(input);
		
		
		
		table = new Table(count);
		t.start(); // starts the thread
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g); // just runs everything before

        Graphics2D g2 = (Graphics2D) g; // greates graphics object
        table.draw(g2);
        
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			table.update();
			try {
				Thread.sleep((int)(17 * SortDriver.getDelay())); // 1000/17 = 58.8 so about 60 fps
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}