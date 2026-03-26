package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AppPanel extends JPanel implements Runnable {
    static Toolkit tk = Toolkit.getDefaultToolkit();

    public static final int WIDTH = (int) tk.getScreenSize().getWidth();
    public static final int HEIGHT = (int) tk.getScreenSize().getHeight() - 38;
    public Dimension d = new Dimension(WIDTH, HEIGHT);

    public Thread t = new Thread(this);
    Table table;

    public AppPanel() {
        setPreferredSize(d);
        setFocusable(true);

        // Ask user for number of pieces
        String input = JOptionPane.showInputDialog("Enter number of Pieces");
        int count = Integer.parseInt(input);
        
        String[] sortOptions = {"Bubble Sort", "Selection Sort", "Insertion Sort"};

        String sortType = (String) JOptionPane.showInputDialog(
                null,
                "Select a sort to visualize:",
                "Sort Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sortOptions,
                sortOptions[0]
        );
        
        String[] imageOptions = {"Banana", "Mountain"};

        String imageType = (String) JOptionPane.showInputDialog(
                null,
                "Select a image to be sorted:",
                "Image Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                imageOptions,
                imageOptions[0]
        );
        
        String[] generateOptions = {"Banana"};//, "Selection Sort", "Insertion Sort"};

        String generateType = (String) JOptionPane.showInputDialog(
                null,
                "Select a image to be sorted:",
                "Image Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                generateOptions,
                generateOptions[0]
        );
        
        
        // Create table
        table = new Table(count, sortType, imageType, generateType);
        
        table.started = true;

        t.start(); // start animation thread
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        table.draw(g2);
    }

    @Override
    public void run() {
        while (true) {
            table.update();
            repaint();
            try {
                Thread.sleep(SortDriver.getDelay()); // slider time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}