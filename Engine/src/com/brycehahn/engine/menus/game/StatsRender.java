package com.brycehahn.engine.menus.game;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.maths.Methods;

public class StatsRender {
	int dx = 4, dy = 4, sx = dx + 50, sy = dy + 100;
	References r = Game.r;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setFont(r.font2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(dx, dy, sx, sy);
		g.setColor(Color.BLACK);
		g.fillRect(dx + 2, dy + 2, sx - 4, sy - 4);
		
		g.setColor(Color.WHITE);
		g.drawString("Player Stats", (dx + 4) + ((sx - dx) / 2) - (Methods.getStringWidth("Player Stats", r.font2) / 2), dy + 16);
	}
}