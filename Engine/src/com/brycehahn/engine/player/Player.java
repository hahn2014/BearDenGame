package com.brycehahn.engine.player;

import java.awt.Graphics;

import com.brycehahn.engine.resources.Tile;

public class Player {
	public int x, y;
	
	public int speed = 10;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		g.drawImage(Tile.texture, x * 32, y * 32, (x * 32) + 32, (y * 32) + 32, Tile.player[0] * Tile.tileSize, Tile.player[1] * Tile.tileSize,
				Tile.player[0] * Tile.tileSize + Tile.tileSize, Tile.player[1] * Tile.tileSize + Tile.tileSize, null);
	}
}