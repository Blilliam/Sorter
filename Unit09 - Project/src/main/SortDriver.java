package main;

import java.awt.BorderLayout;
import java.security.PublicKey;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class SortDriver {
	private static JSlider slider;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Window");
		AppPanel app = new AppPanel();
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		slider  = new JSlider(SwingConstants.VERTICAL, 0, 100, 50);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		
		frame.add(slider, BorderLayout.EAST);
		
		frame.add(app);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static int getDelay() {
		if (slider == null) {
			return 1;
		}
		System.out.println(slider.getValue());
		return slider.getValue();
	}

}