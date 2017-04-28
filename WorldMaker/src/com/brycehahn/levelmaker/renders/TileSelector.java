package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.Tile;

public class TileSelector {
	public static boolean isHover = false;
	private Color hover;
	
	public static int dx = 8, dy = 8, sx = 292, sy = 808;
	
	public static TileSelect[] tiles;
	
	public TileSelector() {
		int startX = dx + 8, startY = 38, endX = 268, endY = 800, spacer = 44;
		
		tiles = new TileSelect[6];
		
		tiles[0] = new TileSelect(Tile.grass, 	"Grass", startX, startY, endX, 40, true);
		tiles[1] = new TileSelect(Tile.path, 	"Path", startX, startY + spacer, endX, 40);
		tiles[2] = new TileSelect(Tile.water, 	"Water", startX, startY + (spacer * 2), endX, 40);
		tiles[3] = new TileSelect(Tile.wood, 	"Wood", startX, startY + (spacer * 3), endX, 40);
		
		
		
		
		
		
		
		tiles[4] = new TileSelect(Tile.wallCollision, 	"Wall Collision", startX, endY - (spacer * 2), endX, 40);
		tiles[5] = new TileSelect(Tile.deathCollision, 	"Death Collision", startX, endY - (spacer), endX, 40);
	}
	
	public void tick() {
		if (isHover) {
			hover = new Color(100, 100, 100);
		} else {
			hover = new Color(50, 50, 50);
		}
		for (TileSelect t : tiles) {
			t.tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(hover);
		g.setFont(Game.fontLarge);
		g.fillRect(8, 8, 284, 800);
		
		g.setColor(Color.WHITE);
		g.drawString("Tile Selector", 150 - (Methods.getStringWidth("Tile Selector", Game.fontLarge) / 2), 28);
		
		g.setColor(new Color(hover.getRed() + 50, hover.getGreen() + 50, hover.getBlue() + 50));
		g.fillRect(12, 700, 276, 100);
		
		//render all the tile selectors
		for (TileSelect t : tiles) {
			t.render(g);
		}
	}
}