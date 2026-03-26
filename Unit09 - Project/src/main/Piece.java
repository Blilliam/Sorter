package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Piece {
    private BufferedImage img;
    private int x, y;
    private int targetX;
    private int value;
    private boolean isAccessed;
    private boolean isModified;

    public Piece(BufferedImage img, int x, int y, int value) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.value = value;
        this.targetX = x;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(img, x, y, null);
        
        if (isAccessed) {
        	g2.setColor(Color.cyan);
        	g2.fillOval(x + img.getWidth()/2 - 10, img.getHeight() + 20, 20, 20);
        } else if (isModified) {
        	g2.setColor(Color.red);
        	g2.fillOval(x + img.getWidth()/2 - 10, img.getHeight() + 20, 20, 20);
        }
    }

    public void update() {
        int speed = 4;
        if (x < targetX) 
        	x += Math.min(speed, targetX - x);
        if (x > targetX) 
        	x -= Math.min(speed, x - targetX);
        
        
    }

    public void setTargetX(int tx) {
        targetX = tx;
    }

    public void setX(int x) { // set initial shuffled position
        this.x = x;
    }

    public int getValue() {
        return value;
    }

    public boolean isAtTarget() {
        return x == targetX;
    }
}