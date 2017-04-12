package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.TextField;

public class NewLevel {
	public static boolean isHover = false;
	private Color box;
	private Color screen;
	private String errorMsg = "";
	
	public static int dx = 290, dy = 340, sx = 790, sy = 740;
	
	public static Button[] buttons;
	public static TextField widthField;
	public static TextField heightField;
	
	public boolean active = true, wError = false, hError = false;
	
	public NewLevel() {
		
		buttons = new Button[2];
		buttons[0] = new Button(dx + 15, sy - 45, "Build");
		buttons[1] = new Button(sx - 90, sy - 45, "Cancel");
		
		widthField = new TextField(dx + 22, dy + 50, 200, 40, Game.fontLarge);
		widthField.setText("100");
		heightField = new TextField(sx - 208, dy + 50, 200, 40, Game.fontLarge);
		heightField.setText("100");

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
		
		if (wError) {
			widthField.setError(true);
			if (LevelStorage.maxX > 1000) {
				if (!errorMsg.contains("X must be smaller than 1000")) {
					errorMsg += " X must be smaller than 1000.";
				}
			} else if (LevelStorage.maxX < 10) {
				if (!errorMsg.contains("X must be larger than 10")) {
					errorMsg += " X must be larger than 10.";
				}
			}
		} else {
			widthField.setError(false);
			errorMsg = "";
		}
		
		if (hError) {
			heightField.setError(true);
			if (LevelStorage.maxY > 1000) {
				if (!errorMsg.contains("Y must be smaller than 1000")) {
					errorMsg += " Y must be smaller than 1000.";
				}
			} else if (LevelStorage.maxY < 10) {
				if (!errorMsg.contains("Y must be larger than 10")) {
					errorMsg += " Y must be larger than 10.";
				}
			}
		} else {
			heightField.setError(false);
			errorMsg = "";
		}
		
		widthField.tick();
		heightField.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(screen);
		g.fillRect(0, 0, 1080, 1080);
		g.setColor(box);
		g.fillRect(dx, dy, (sx - dx), (sy - dy));
		
		g.setColor(Color.BLACK);
		g.drawString("Create A New World", (dx + 250) - (Methods.getStringWidth("Create A New World", Game.font) / 2), dy + 16);
		
		g.setFont(Game.fontLarge);
		g.drawString("X:", dx + 2, (dy + 87) - (Methods.getStringHeight("X:", Game.fontLarge) / 2));
		g.drawString("Y:", sx - 228, (dy + 87) - (Methods.getStringHeight("X:", Game.fontLarge) / 2));
		
		widthField.render(g);
		heightField.render(g);
		
		g.setColor(Color.RED);
		g.setFont(Game.font);
		g.drawString(errorMsg, dx + 16, dy + 300);
		
		for (Button b : buttons) {
			b.render(g);
		}
	}
}