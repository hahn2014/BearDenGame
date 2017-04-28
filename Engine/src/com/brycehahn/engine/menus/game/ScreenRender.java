package com.brycehahn.engine.menus.game;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.resources.Tile;
import com.brycehahn.engine.resources.World;

public class ScreenRender {
	References r = Game.r;
	
	public static int[] tileSelect = Tile.grass;
	
	public static int dx = 0, dy = 0, sx = 500, sy = 600;
	public static int blockOffsetX = 0, blockOffsetY = 0;
	
	World world;
	StatsRender stats;
	
	public ScreenRender() {
		world = new World(50, 50);
		stats = new StatsRender();
	}
	
	public void tick() {
		stats.tick();
		Game.player.tick();
	}
	
	public void render(Graphics g) {
		
		stats.render(g);
		
		//draw the actual level area
		g.setColor(Color.BLACK);
		g.fillRect(dx, dy, r.PIXEL.width, r.PIXEL.height);
		
		//draw the level storage data
		for (int x = 0; x < World.blocks.length; x++) {
			for (int y = 0; y < World.blocks[0].length; y++) {
				World.blocks[x][y].render(g, x, y, blockOffsetX, blockOffsetY);
			}
		}
		
		Game.player.render(g);

		
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