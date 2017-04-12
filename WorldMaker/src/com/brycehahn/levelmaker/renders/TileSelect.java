package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.Tile;

public class TileSelect {
	public int[] id;
	public String name = "";
	public int x, y, width, height;
	public boolean isHover = false;
	public boolean isSelect = false;
	Color hover, select = new Color(250, 0, 100);
	
	public TileSelect(int[] id, String name, int x, int y, int width, int height) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public TileSelect(int[] id, String name, int x, int y, int width, int height, boolean selected) {
		this.id = id;
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSelect = true;
	}
	
	public void tick() {
		if (isHover) {
			hover = new Color(250, 250, 250);
		} else {
			hover = new Color(200, 200, 200);
		}
	}
	
	public void render(Graphics g) {
		g.setColor((isSelect) ? select : hover);
		g.fillRect(x, y, width, height);
		
		g.drawImage(Tile.texture, x + 4, y + 4, x + 38,
				y + 38, id[0] * Tile.tileSize, id[1] * Tile.tileSize,
				id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		
		g.setColor(Color.BLACK);
		g.drawString(name, 60, y + (height / 2) + (Methods.getStringHeight(name, Game.font) / 2));
	}
}