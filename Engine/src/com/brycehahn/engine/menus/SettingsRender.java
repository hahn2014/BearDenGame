package com.brycehahn.engine.menus;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.maths.Methods;
import com.brycehahn.engine.menus.ui.Button;
import com.brycehahn.engine.resources.Tile;

public class SettingsRender {
	
	private int x = 0, y = 50, ySpace = 25, widthtimes = 0, heighttimes = 0;
	public static int selected = 0, min = 0, max = 5;
	
	public static Color[] b = new Color[6];
	
	String[] buttons = new String[6];
	
	public Button[] btns = new Button[6];
	
	private References r;

	public SettingsRender() {
		r = Game.r;
		
		x = (r.SIZE.width / 2) - (r.lrgButtonWidth / 2);
		
		widthtimes = (r.SIZE.width / Tile.tileSize) + 1;
		heighttimes = (r.SIZE.height / Tile.tileSize) + 1;
		
		defineButtons();
	}
	
	private void defineButtons() {
		btns[0] = new Button(x, y + (ySpace * 0), "1");
		btns[1] = new Button(x, y + (ySpace * 1), "2");
		btns[2] = new Button(x, y + (ySpace * 2), "3");
		btns[3] = new Button(x, y + (ySpace * 3), "4");
		btns[4] = new Button(x, y + (ySpace * 4), "5");
		btns[5] = new Button(x, y + (ySpace * 5), "Go Back");
	}
	
	
	public void tick() {
		//button hover checking
		for (int i = 0; i < btns.length; i++) {
			if (selected == i) {
				btns[i].setHover(true);
			} else {
				btns[i].setHover(false);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setFont(r.fontLarge);
		g.setColor(r.emptyBG);
		try {
			int[] id = Tile.path;
			for (int x = 0; x < widthtimes; x++) {
				for (int y = 0; y < heighttimes; y++) {
					g.drawImage(Tile.texture, x * 32, y * 32, (x * 32) + 32, (y * 32) + 32, id[0] * Tile.tileSize, id[1] * Tile.tileSize,
							id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
				}
			}
		} catch (Exception e) {
//			CrashDumping.DumpCrash(e);
			g.fillRect(0, 0, r.SIZE.width, r.SIZE.height);
		}
		g.setColor(Color.WHITE);
		//title
		g.drawString("Settings", (r.SIZE.width / 2) - (Methods.getStringWidth("Settings", r.fontLarge) / 2), Methods.getStringHeight("Settings", r.fontLarge));
//		g.drawImage(Tile.empty, (r.SIZE.width / 2) - (Tile.logo.getWidth() / 2), 5, 279, 50, null);
		g.setFont(r.font3);
		//version
		g.drawString(r.BUILD + " " + r.VERSION, 4, r.SIZE.height - 10 - (Methods.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		//copyright
		g.drawString("Copyright Bryce Hahn. Do not distribute!", (r.SIZE.width - 6) - (Methods.getStringWidth("Copyright Bryce Hahn. Do not distribute!", r.font3)),
				r.SIZE.height - 10 - (Methods.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		g.setFont(r.font1);
		
		//button rendering
		for (int i = 0; i < btns.length; i++) {
			btns[i].render(g);
		}
	}
}