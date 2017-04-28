package com.brycehahn.levelmaker.misc;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {
	private static final long serialVersionUID = -8298367192871334446L;

	private int[] id;
	public boolean isHover = false;
	
	public Block(Rectangle size, int[] id) {
		this.id = id;
		setBounds(size);
	}
	public Block(int x, int y, int width, int height, int[] id) {
		this.id = id;
		setBounds(x, y, width, height);
	}
	
	
	public void render(Graphics g, int indexX, int indexY, int xx, int yy, double scale) {
		if (id != Tile.blank) {
			g.drawImage(Tile.texture, ((int)(32 * scale) * indexX) + xx, ((int)(32 * scale) * indexY) + yy, ((int)(32 * scale) * indexX) + xx + (int)(width * scale),
					((int)(32 * scale) * indexY) + yy + (int)(height * scale), id[0] * Tile.tileSize, id[1] * Tile.tileSize,
					id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
	}
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	public int[] getID() {
		return id;
	}
	public String getIDforSave() {
		return "[" + id[0] + "," + id[1] + "]";
	}
	
	public void setID(int[] id) {
		this.id = id;
	}
}