package com.brycehahn.engine.menus;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.maths.Methods;
import com.brycehahn.engine.menus.ui.Button;
import com.brycehahn.engine.resources.Tile;

public class MainMenuRender {
	
	private int x = 0, y = 80, ySpace = 70, widthtimes = 0, heighttimes = 0;
	public static int selected = 0, min = 0, max = 3;
	
	public Button[] btns = new Button[4];
	
	private References r;

	public MainMenuRender() {
		r = Game.r;
		
		x = (r.SIZE.width / 2) - (r.lrgButtonWidth / 2);
		
		widthtimes = (r.SIZE.width / Tile.tileSize) + 1;
		heighttimes = (r.SIZE.height / Tile.tileSize) + 1;
		
		defineButtons();
	}
	
	private void defineButtons() {
		btns[0] = new Button(x, y + (ySpace * 0), 200, 50, "New Save");
		btns[1] = new Button(x, y + (ySpace * 1), 200, 50, "Load Save");
		btns[2] = new Button(x, y + (ySpace * 2), 200, 50, "Settings");
		btns[3] = new Button(x, y + (ySpace * 3), 200, 50, "Exit Game");
	}
	
	public void tick() {
		//button hover checking
//		for (int i = 0; i < btns.length; i++) {
//			if (selected == i) {
//				btns[i].setHover(true);
//			} else {
//				btns[i].setHover(false);
//			}
//		}
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
		g.drawString(r.NAME, (r.SIZE.width / 2) - (Methods.getStringWidth(r.NAME, r.fontLarge) / 2), Methods.getStringHeight(r.NAME, r.fontLarge));
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
