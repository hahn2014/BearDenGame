package com.brycehahn.levelmaker.renders;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.text.SimpleDateFormat;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Methods;

public class LevelSelect {
	public int id;
	public String filename = "";
	public int x, y, width, height;
	public boolean isHover = false;
	public boolean isSelect = false;
	Color hover, select = new Color(66, 244, 167);
	LevelInfo data = new LevelInfo();
	
	public LevelSelect(int id, String filename, int x, int y, int width, int height) {
		this.id = id;
		this.filename = filename;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		data.setName(filename);
		getData();
	}
	
	public LevelSelect(int id, String filename, int x, int y, int width, int height, boolean selected) {
		this.id = id;
		this.filename = filename;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isSelect = true;
		data.setName(filename);
		getData();
	}
	
	public void tick() {
		if (isHover) {
			hover = new Color(200, 200, 200);
		} else {
			hover = new Color(100, 100, 100);
		}
	}
	
	private void getData() {
		//last save time
		File file = new File(filename);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd hh:mm a");
		data.setSaveTime(sdf.format(file.lastModified()));
		
		data.setSize(file.length());
	}
	
	public void render(Graphics g) {
		g.setColor((isSelect) ? select : hover);
		g.fillRect(x, y, width, height);
		
		//World Name
		g.setColor(Color.BLACK);
		g.drawString(filename, x + 15, y + (height / 2) + (Methods.getStringHeight(filename, Game.font) / 2));
		
		//World Save Time
		g.drawString(data.getSaveTime(), (x + width - 16) - Methods.getStringWidth(data.getSaveTime(), Game.font), y + 16);
		
		//World Size
		g.drawString(data.getSize(), (x + width - 16) - Methods.getStringWidth(data.getSaveTime(), Game.font), y + height - 8);
	}
}