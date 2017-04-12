package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;

public class Render {
	
	private int x = 0, y = 80, ySpace = 25;
	public static int selected = 0, min = 0, max = 2;
	
	public static Color[] b = new Color[3];
	
	String[] buttons = new String[3];
	
	public Button[] btns = new Button[3];
	
	
	
	public LevelScreen level = new LevelScreen();
	public TileSelector selector = new TileSelector();
	public Options options = new Options();
	public ExportLevel export = new ExportLevel();
	public ImportLevel Import = new ImportLevel();
	public NewLevel New = new NewLevel();

	public Render() {
		
		x = (1080 / 2) - 50;
		
		defineButtons();
	}
	
	private void defineButtons() {
		btns[0] = new Button(x, y + (ySpace * 0), "New Game");
		btns[1] = new Button(x, y + (ySpace * 1), "Settings");
		btns[2] = new Button(x, y + (ySpace * 2), "Exit Game");
	}
	
	public void tick() {
		level.tick();
		selector.tick();
		options.tick();
		export.tick();
		Import.tick();
		New.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1080, 1080);
		
		//render the level
		level.render(g);
		
		//render the tile selector
		selector.render(g);
		
		//render level options
		options.render(g);

		//if active, render export world	(This will be rendered last, because we need the render to be called on top)
		if (export.active) {
			export.render(g);
		}
		
		//if  active, render load world		(This will be rendered last, because we need the render to be called on top)
		if (Import.active) {
			Import.render(g);
		}
		
		//if  active, render new world		(This will be rendered last, because we need the render to be called on top)
		if (New.active) {
			New.render(g);
		}
		
		if (Game.dev) { //test render
			g.setColor(Color.RED);
			
			g.drawRect(TileSelector.dx, TileSelector.dy, TileSelector.sx - TileSelector.dx, TileSelector.sy - TileSelector.dy);
			g.drawRect(Options.dx, Options.dy, Options.sx - Options.dx, Options.sy - Options.dy);
			g.drawRect(LevelScreen.dx, LevelScreen.dy, LevelScreen.sx - LevelScreen.dx, LevelScreen.sy - LevelScreen.dy);
			g.drawRect(LevelScreen.dx + 8, LevelScreen.dy + 26, 756, 940);
		}
		g.setColor(Color.WHITE);
		g.drawString("WorldMaker Pre-Alpha Build 1.00", 4, 1070);
		//copyright
		g.drawString("Copyright Bryce Hahn. Do not distribute!", 714, 1070);
	}
}
