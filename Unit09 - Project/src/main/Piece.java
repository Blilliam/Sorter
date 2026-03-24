package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Piece {
	private int x, y, width, height;
	
	public Piece(int x, int y) {
		this.x = x;
		this.y = y;
		width = 100;
		height = 100;
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.fillRect(x, y, width, height);
	}
	
	public void update() {
		x++;
	}
}
