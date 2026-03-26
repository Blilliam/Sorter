package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Table {
	Piece[] list;
	int startX;
	int y;
	int imageY;
	int pieceWidth;
	boolean started = false; // wait for start
	BufferedImage fullSprite;

	int i; // sort indices
	int j;
	boolean sorting = true;

	String sortCase;

	public Table(int count, String Sortcase, String imageType, String order) {
		imageY = 100;
		try {
			switch (imageType) {

			case "Mountain":
				fullSprite = ImageIO.read(getClass().getResource("Mountain.jpg"));
				break;

			case "Banana":
				fullSprite = ImageIO.read(getClass().getResource("Banana projectile.jpeg"));
				break;

//			case "Insertion Sort":
//				stepInsertionSort();
//				break;

		}

			// scale image to fit panel
			double scaleX = (AppPanel.HEIGHT - imageY)/ (double) fullSprite.getWidth();
			double scaleY = AppPanel.WIDTH / (double) fullSprite.getHeight();
			double scale = Math.min(scaleX, scaleY);

			int newWidth = (int) (fullSprite.getWidth() * scale);
			int newHeight = (int) (fullSprite.getHeight() * scale);

			pieceWidth = newWidth / count;
			list = new Piece[count];

			this.sortCase = Sortcase;

			// create slices
			for (int k = 0; k < count; k++) {
				BufferedImage slice = fullSprite.getSubimage(k * (fullSprite.getWidth() / count), 0,
						fullSprite.getWidth() / count, fullSprite.getHeight());

				// scale slice
				BufferedImage scaledSlice = new BufferedImage(pieceWidth, newHeight, slice.getType());
				scaledSlice.getGraphics().drawImage(slice, 0, 0, pieceWidth, newHeight, null);

				list[k] = new Piece(scaledSlice, 0, y, k);
			}

			// shuffle pieces in array
			shuffleArray();

			// center horizontally
			int totalWidth = pieceWidth * count;
			startX = (AppPanel.WIDTH - totalWidth) / 2;

			// assign initial shuffled positions (x = targetX, so no jump)
			for (int k = 0; k < list.length; k++) {
				int shuffledX = startX + k * pieceWidth;
				list[k].setX(shuffledX);
				list[k].setTargetX(shuffledX);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void shuffleArray() {
		Random rand = new Random();
		for (int i = 0; i < list.length; i++) {
			int j = rand.nextInt(list.length);
			Piece temp = list[i];
			list[i] = list[j];
			list[j] = temp;
		}
	}

	public void stepBubbleSort() {
		if (!started || !sorting)
			return;

		for (Piece p : list) {
			if (!p.isAtTarget())
				return; // wait until all pieces finish moving
		}

		if (i < list.length) {
			if (j < list.length - i - 1) {
				if (list[j].getValue() > list[j + 1].getValue()) {
					Piece temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;

					for (int k = 0; k < list.length; k++) {
						list[k].setTargetX(startX + k * pieceWidth);
					}
				}
				j++;
			} else {
				j = 0;
				i++;
			}
		} else {
			sorting = false;
		}
	}

	public void stepInsertionSort() {
		if (!started || !sorting)
			return;

		// wait until all pieces finished moving
		for (Piece p : list) {
			if (!p.isAtTarget())
				return;
		}

		if (i < list.length) {
			if (j >= 0 && list[j].getValue() > list[j + 1].getValue()) {
				// swap list[j] and list[j + 1]
				Piece temp = list[j];
				list[j] = list[j + 1];
				list[j + 1] = temp;

				// update target positions
				for (int k = 0; k < list.length; k++) {
					list[k].setTargetX(startX + k * pieceWidth);
				}

				j--; // move left in insertion sort
			} else {
				// move to next i
				i++;
				j = i - 1; // start comparing with left neighbors
			}
		} else {
			sorting = false;
		}
	}

	public void stepSelectionSort() {
		if (!started || !sorting)
			return;

		// wait until all pieces finished moving
		for (Piece p : list) {
			if (!p.isAtTarget())
				return;
		}

		if (i < list.length - 1) {
			// find min index in the unsorted portion
			int minIndex = i;
			for (int k = i + 1; k < list.length; k++) {
				if (list[k].getValue() < list[minIndex].getValue()) {
					minIndex = k;
				}
			}

			// swap if needed
			if (minIndex != i) {
				Piece temp = list[i];
				list[i] = list[minIndex];
				list[minIndex] = temp;

				// update target positions
				for (int k = 0; k < list.length; k++) {
					list[k].setTargetX(startX + k * pieceWidth);
				}
			}

			i++; // move to next index
		} else {
			sorting = false;
		}
	}
	public void update() {
		switch (sortCase) {

			case "Bubble Sort":
				stepBubbleSort();
				break;

			case "Selection Sort":
				stepSelectionSort();
				break;

			case "Insertion Sort":
				stepInsertionSort();
				break;

		}
		for (Piece p : list) {
			p.update();
		}
	}

	public void draw(Graphics2D g2) {
		for (Piece p : list) {
			p.draw(g2);
		}
	}

	public void swap() {

	}
}