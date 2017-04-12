package com.brycehahn.engine.menus;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.maths.Methods;
import com.brycehahn.engine.menus.ui.Button;
import com.brycehahn.engine.menus.ui.TextBox;
import com.brycehahn.engine.resources.Tile;

public class NewWorldRender {
	private int x = 0, y = 80, widthtimes = 0, heighttimes = 0;
	public static int selected = 0;
	
	public static Color[] b = new Color[2];
	
	String[] buttons = new String[2];
	
	public Button[] btns = new Button[2];
	TextBox box;
	
	private References r;
	private Methods m;

	public NewWorldRender() {
		r = Game.r;
		m = new Methods();
		
		x = (r.PIXEL.width / 2) - (r.lrgButtonWidth / 2);
		
			widthtimes = (r.PIXEL.width / Tile.tileSize) + 1;
			heighttimes = (r.PIXEL.height / Tile.tileSize) + 1;
		
		defineButtons();
	}
	
	private void defineButtons() {
		btns[0] = new Button(x, y, 100, 20, "Go Back");
		btns[1] = new Button(x + 120, y, 100, 20, "New Game");
		box = new TextBox(x, y);
	}
	
	public void tick() {
		//button hover checking
		for (int i = 0; i < btns.length; i++) {
			if (selected == i) {
				btns[i].setHover(true);
			} else {
				btns[i].setHover(false);
				box.setHover(false);
			}
		}
		if (selected == -1) {
			box.setHover(true);
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
			g.fillRect(0, 0, r.PIXEL.width, r.PIXEL.height);
		}
		g.setColor(Color.WHITE);
		//title
		g.drawString("New World", (r.PIXEL.width / 2) - (m.getStringWidth("New World", r.fontLarge) / 2), m.getStringHeight("New World", r.fontLarge));
//			g.drawImage(Tile.empty, (r.PIXEL.width / 2) - (Tile.logo.getWidth() / 2), 5, 279, 50, null);
		g.setFont(r.font3);
		//version
		g.drawString(r.BUILD + " " + r.VERSION, 4, r.PIXEL.height - 10 - (m.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		//copyright
		g.drawString("Copyright Bryce Hahn. Do not distribute!", (r.PIXEL.width - 6) - (m.getStringWidth("Copyright Bryce Hahn. Do not distribute!", r.font3)),
				r.PIXEL.height - 10 - (m.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		g.setFont(r.font1);
		
		box.render(g);
		
		//button rendering
		for (int i = 0; i < btns.length; i++) {
			btns[i].render(g);
		}
	}
}