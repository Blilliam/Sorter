package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.lang.invoke.CallSite;

public class Table{
	Piece[] list;
	
	public Table(int length) {
		list = new Piece[length];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new Piece(i * 100 + 100, 200);
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.blue);
		g2.fillRect(100, 100, 100, 100);
		
		for (int i = 0; i < list.length; i ++) {
			list[i].draw(g2);
		}
	}
	
	public void update() {
		for (int i = 0; i < list.length; i ++) {
			list[i].update();
		}
	}
}
