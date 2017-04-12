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
	
	
	public void render(Graphics g, int indexX, int indexY, int xx, int yy) {
		if (id != Tile.blank) {
			g.drawImage(Tile.texture, (32 * indexX) + xx, (32 * indexY) + yy, (32 * indexX) + xx + width,
					(32 * indexY) + yy + height, id[0] * Tile.tileSize, id[1] * Tile.tileSize,
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