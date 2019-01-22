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

	public NewWorldRender() {
		r = Game.r;
		
		x = (r.SIZE.width / 2) - (r.lrgButtonWidth / 2);
		
			widthtimes = (r.SIZE.width / Tile.tileSize) + 1;
			heighttimes = (r.SIZE.height / Tile.tileSize) + 1;
		
		defineButtons();
	}
	
	private void defineButtons() {
		btns[0] = new Button(x, y, 100, 40, "Go Back");
		btns[1] = new Button(x + 120, y, 100, 40, "New Game");
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
			g.fillRect(0, 0, r.SIZE.width, r.SIZE.height);
		}
		g.setColor(Color.WHITE);
		//title
		g.drawString("New Game Save", (r.SIZE.width / 2) - (Methods.getStringWidth("New Game Save", r.fontLarge) / 2), Methods.getStringHeight("New Game Save", r.fontLarge));
		g.setFont(r.font3);
		//version
		g.drawString(r.BUILD + " " + r.VERSION, 4, r.SIZE.height - 10 - (Methods.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		//copyright
		g.drawString("Copyright Bryce Hahn. Do not distribute!", (r.SIZE.width - 6) - (Methods.getStringWidth("Copyright Bryce Hahn. Do not distribute!", r.font3)),
				r.SIZE.height - 10 - (Methods.getStringHeight(r.BUILD + " " + r.VERSION, r.font3) / 2));
		g.setFont(r.font1);
		box.render(g);
		
		//button rendering
		for (int i = 0; i < btns.length; i++) {
			btns[i].render(g);
		}
	}
}