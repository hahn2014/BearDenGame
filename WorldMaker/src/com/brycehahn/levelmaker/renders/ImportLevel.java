package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.SaveLoad;

public class ImportLevel {
	public static boolean isHover = false;
	private Color box;
	private Color screen;
	
	public static int dx = 290, dy = 140, sx = 790, sy = 940;
	
	public static Button[] buttons;
	public static LevelSelect[] levels;
	
	public boolean active = false;
	
	public static int selected = 0, top = 0, bottom = 6;
	
	public ImportLevel() {
		SaveLoad.updateWorldList();
		levels = new LevelSelect[SaveLoad.worlds.length];
		buttons = new Button[2];
		
		getLevelData();
		
		buttons[0] = new Button(dx + 15, sy - 45, "Import");
		buttons[1] = new Button(sx - 90, sy - 45, "Cancel");

		for (Button b : buttons) {
			b.setWidth(75);
			b.setHeight(30);
		}
	}
	
	public static String getFileName() {
		return levels[selected].filename;
	}
	
	private void getLevelData() {
		int startX = dx + 15, startY = dy + 40, spacer = 95;
		
		if (levels.length >= 1) {
			for (int i = 0; i < levels.length; i++) {
				levels[i] = new LevelSelect(i, SaveLoad.worlds[i], startX, startY + (spacer * i), 470, 80);
			}
			levels[0].isSelect = true;
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
		for (LevelSelect l : levels) {
			l.tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(screen);
		g.fillRect(0, 0, 1080, 1080);
		g.setColor(box);
		g.fillRect(dx, dy, (sx - dx), (sy - dy));
		
		g.setColor(Color.BLACK);
		g.drawString("Import Level From Game Engine", (dx + 250) - (Methods.getStringWidth("Import Level From Game Engine", Game.font) / 2), dy + 16);
		
		
		for (Button b : buttons) {
			b.render(g);
		}
		
		if (levels.length >= 1) {
			for (int i = 0; i < 7; i++) { // we can only render 7 at a time
				levels[i].render(g);
			}
		} else {
			g.setColor(Color.RED);
			g.drawString("There are no available levels to import!", dx + ((sx - dx) / 2
					- (Methods.getStringWidth("There are no available levels to import!", Game.font) / 2)), 300);
		}
	}
}