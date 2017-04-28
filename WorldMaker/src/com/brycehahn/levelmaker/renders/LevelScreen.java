package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.Tile;

public class LevelScreen {
	public static boolean isHover = false, scaling = false;
	private Color hover;
	public static int[] tileSelect = Tile.grass;
	
	public static int dx = 300, dy = 8, sx = 1072, sy = 1050, scaleDelay = 0;
	public int viewOffsetX = 0, viewOffsetY = 0;
	public double scale = 1;
	
	public void tick() {
		if (isHover) {
			hover = new Color(200, 200, 200);
		} else {
			hover = new Color(150, 150, 150);
		}
		
		if (scaling) {
			System.out.println("scaling - " + scaleDelay);
			if (scaleDelay < 10) {
				scaleDelay += 1;
			} else {
				scaleDelay = 0;
				scaling = false;
			}
		}
		LevelStorage.updateRenderBlocks(viewOffsetX, viewOffsetY);
	}
	
	public void render(Graphics g) {
		g.setColor(hover);
		g.fillRect(300, 8, 772, 1042); 
		
		g.setColor(Color.BLACK);
		g.setFont(Game.fontLarge);
		g.drawString("Level Editor", 686 - (Methods.getStringWidth("Level Editor", Game.fontLarge) / 2), 28);
		
		//draw the actual level area
		g.setColor(Color.BLACK);
		g.fillRect(dx + 8, dy + 26, 756, 940);
		//draw the level storage data
		for (int x = 0; x < LevelStorage.renderBlocks.length; x++) {
			for (int y = 0; y < LevelStorage.renderBlocks[0].length; y++) {
				if (scaling == false) {
					LevelStorage.renderBlocks[x][y].render(g, x, y, dx + 16, dy + 34, scale);
					if (LevelStorage.renderBlocks[x][y].isHover) {
						g.setColor(Color.RED);
						g.drawRect(dx + 16 + (int)((scale * 32) * x), dy + 34 + (int)((scale * 32) * y), (int)(32 * scale), (int)(32 * scale));
					}
				}
			}
		}
		
		//draw the current Tile Select
		g.setColor(new Color(250, 0, 100, 150));
		g.setFont(Game.font);
		g.fillRect(dx + 8, sy - 70, 100, 64);
		g.setColor(Color.BLACK);
		g.drawString("Selected Tile", dx + 18, sy - 54);
		g.drawImage(Tile.texture, dx + 42, sy -46, dx + 74,
				sy - 14, tileSelect[0] * Tile.tileSize, tileSelect[1] * Tile.tileSize,
				tileSelect[0] * Tile.tileSize + Tile.tileSize, tileSelect[1] * Tile.tileSize + Tile.tileSize, null);
		
		//draw the current scale
		g.setColor(new Color(250, 0, 100, 150));
		g.fillRect(dx + 128, sy - 70, 100, 64);
		g.setColor(Color.BLACK);
		g.drawString("View Scale", dx + 146, sy - 54);
		g.setFont(Game.fontLarge);
		g.drawString(scale + "x", dx + (178 - Methods.getStringWidth(scale + "x", Game.fontLarge) / 2), sy - 24);
	}
}