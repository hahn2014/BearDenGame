package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.TextField;

public class ExportLevel {
	public static boolean isHover = false;
	private Color box;
	private Color screen;
	
	public static int dx = 290, dy = 140, sx = 790, sy = 940;
	
	public static Button[] buttons;
	public static TextField field;
	
	public boolean active = false;
	
	public ExportLevel() {
		
		buttons = new Button[2];
		buttons[0] = new Button(dx + 15, sy - 45, "Export");
		buttons[1] = new Button(sx - 90, sy - 45, "Cancel");
		
		field = new TextField(dx + ((sx - dx) / 2) - 200, 200, true);

		for (Button b : buttons) {
			b.setWidth(75);
			b.setHeight(30);
		}
	}
	
	public void tick() {
		if (isHover) {
			box = new Color(255, 255, 255, 255);
			screen = new Color(100, 100, 100, 200);
		} else {
			box = new Color(50, 50, 50, 100);
			screen = new Color(232, 232, 232, 100);
		}
		field.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(screen);
		g.fillRect(0, 0, 1080, 1080);
		g.setColor(box);
		g.fillRect(dx, dy, (sx - dx), (sy - dy));
		
		g.setColor(Color.BLACK);
		g.drawString("Export Level To Game Engine", (dx + 250) - (Methods.getStringWidth("Export Level To Game Engine", Game.font) / 2), dy + 16);
		
		field.render(g);
		
		for (Button b : buttons) {
			b.render(g);
		}
	}
}