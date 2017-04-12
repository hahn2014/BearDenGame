package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.Tile;

public class LevelScreen {
	public static boolean isHover = false;
	private Color hover;
	public static int[] tileSelect = Tile.grass;
	
	public static int dx = 300, dy = 8, sx = 1072, sy = 1050;
	public int viewOffsetX = 0, viewOffsetY = 0;
	
	public void tick() {
		if (isHover) {
			hover = new Color(200, 200, 200);
		} else {
			hover = new Color(150, 150, 150);
		}
		LevelStorage.updateRenderBlocks(viewOffsetX, viewOffsetY);
	}
	
	public void render(Graphics g) {
		g.setColor(hover);
		g.fillRect(300, 8, 772, 1042); 
		
		g.setColor(Color.BLACK);
		g.drawString("Level Editor", 686 - (Methods.getStringWidth("Level Editor", Game.font) / 2), 28);
		
		//draw the actual level area
		g.setColor(Color.BLACK);
		g.fillRect(dx + 8, dy + 26, 756, 940);
		//draw the level storage data
		for (int x = 0; x < LevelStorage.renderBlocks.length; x++) {
			for (int y = 0; y < LevelStorage.renderBlocks[0].length; y++) {
				LevelStorage.renderBlocks[x][y].render(g, x, y, dx + 16, dy + 34);
				if (LevelStorage.renderBlocks[x][y].isHover) {
					g.setColor(Color.RED);
					g.drawRect(dx + 16 + (32 * x), dy + 34 + (32 * y), 32, 32);
				}
			}
		}
		
		//draw the current Tile Select
		g.setColor(new Color(250, 0, 100, 150));
		g.fillRect(dx + 8, sy - 70, 100, 64);
		g.setColor(Color.BLACK);
		g.drawString("Selected Tile", dx + 18, sy - 54);
		g.drawImage(Tile.texture, dx + 42, sy -46, dx + 74,
				sy - 14, tileSelect[0] * Tile.tileSize, tileSelect[1] * Tile.tileSize,
				tileSelect[0] * Tile.tileSize + Tile.tileSize, tileSelect[1] * Tile.tileSize + Tile.tileSize, null);
	}
}