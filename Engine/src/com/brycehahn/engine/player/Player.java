package com.brycehahn.engine.player;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.menus.game.ScreenRender;
import com.brycehahn.engine.resources.World;

public class Player {
	public int x, y;
	public int xMove = 0; //-1 = left, 1 = right
	public int yMove = 0; //-1 =  up, 1 = down
	
	public int speed = 4;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if (xMove == -1) { // left
			x -= speed;
		} else if (xMove == 1) { //right
			if (((x - 32) + speed) <= Game.r.PIXEL.width / 2) {
				x += speed;
			} else {
				if (ScreenRender.blockOffsetX - speed >= -((World.blocks.length * 32) / Game.r.pixelSize)) {
					ScreenRender.blockOffsetX -= speed;
					System.out.println("X: " + x + "; offset: " + ScreenRender.blockOffsetX);
				} else {
					if (x + speed < 520) {
						x += speed;
					}
				}
			}
		}
		if (yMove == -1) { //up
			y -= speed;
		} else if (yMove == 1) { //down
			y += speed;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 32, 64);
		
		//g.drawImage(Tile.texture, x * 32, y * 32, (x * 32) + 32, (y * 32) + 32, Tile.player[0] * Tile.tileSize, Tile.player[1] * Tile.tileSize,
			//	Tile.player[0] * Tile.tileSize + Tile.tileSize, Tile.player[1] * Tile.tileSize + Tile.tileSize, null);
	}
}