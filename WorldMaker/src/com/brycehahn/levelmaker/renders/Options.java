package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;
import com.brycehahn.levelmaker.misc.Methods;

public class Options {
	public static boolean isHover = false;
	private Color hover;
	
	public static int dx = 8, dy = 816, sx = 292, sy = 1050;
	
	int spacer = 50;

	public static Button[] btns;
	
	public Options() {
		btns = new Button[3];
		
		btns[0] = new Button(dx + 8, dy + spacer, "Export Level To Engine (save)");
		btns[1] = new Button(dx + 8, dy + (spacer * 2), "Load Level From Engine (import)");
		btns[2] = new Button(dx + 8, dy + (spacer * 3), "New Level");
		
		for (Button b : btns) {
			b.setWidth(266);
			b.setHeight(40);
		}
	}
	
	public void tick() {
		if (isHover) {
			hover = new Color(150, 150, 150);
		} else {
			hover = new Color(100, 100, 100);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(hover);
		g.fillRect(8, 816, 284, 234);
		
		g.setColor(Color.WHITE);
		g.drawString("Level Options", 150 - (Methods.getStringWidth("Level Options", Game.font) / 2), 836);
		
		for (Button b : btns) {
			b.render(g);
		}
	}
}