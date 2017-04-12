package com.brycehahn.engine.resources;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.brycehahn.engine.main.Game;

public class Block extends Rectangle {
	private static final long serialVersionUID = -8298367192871334446L;

	private int[] id;
	
	public Block(Rectangle size, int[] id) {
		this.id = id;
		setBounds(size);
	}
	public Block(int x, int y, int width, int height, int[] id) {
		this.id = id;
		setBounds(x, y, width, height);
	}
	
	
	public void render(Graphics g) {
		if (id != Tile.blank) {
			g.drawImage(Tile.texture, x - (int)Game.player.x, y - (int)Game.player.y, x + width - (int)Game.player.x,
					y + height - (int)Game.player.y, id[0] * Tile.tileSize, id[1] * Tile.tileSize,
					id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		} else {
			g.drawRect(x - (int)Game.player.x, y - (int)Game.player.y, width, height);
		}
	}
	
	
	/*
	 * GETTERS AND SETTERS
	 */
	public int[] getID() {
		return id;
	}
	public void setID(int[] id) {
		this.id = id;
	}
}