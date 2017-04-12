package com.brycehahn.levelmaker.misc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.brycehahn.levelmaker.Game;

public class TextField {
	int x = 0, y = 0, width = 400, height = 40;
	String text = "";
	public Boolean isHover = false, isSelected = false;
	Color hover, placer, select = new Color(0, 0, 0), error = new Color(255, 0, 0);
	
	private double r = 0.0, g = 0.0, b = 0.0;
	private Font font = Game.fontLarge;
	private boolean extension = false, hasError = false;
	
	public TextField(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public TextField(int x, int y, boolean extension) {
		this.x = x;
		this.y = y;
		this.extension = extension;
	}
	
	public TextField(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public TextField(int x, int y, int width, int height, Font font) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.font = font;
	}
	
	public void tick() {
		if (isHover) {
			hover = new Color(50, 50, 50);
		} else {
			hover = new Color(100, 100, 100);
		}
		if (isSelected) {
			//fade in and out placer
			if (r < 255.0) r += 1.5;
			else r = 0;
			
			if (g < 255.0) g += 1.5;
			else g = 0;
			
			if (b < 255.0) b += 1.5;
			else b = 0;
			
			placer = new Color((int)r, (int)g, (int)b);
		}
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, width, height);
		
		g.setColor((hasError) ? error : ((isSelected) ? select : hover));
		g.fillRect(x + 4, y + 4, width - 8, height - 8);
		
		g.setColor(Color.WHITE);
		g.drawString(text.toLowerCase(), x + 8, y + (height / 2) + (Methods.getStringHeight(text.toLowerCase(), font) / 2));
		if (extension) {
			g.setColor(new Color(200, 200, 200, 200));
			g.drawString(".wld", (x + width - 8) - (Methods.getStringWidth(".wld", font)), y + (height / 2) + (Methods.getStringHeight(text.toLowerCase(), font) / 2));
		}
		g.setColor(placer);
		g.drawString("|", x + (Methods.getStringWidth(text.toLowerCase(), font) + 12), y + (height / 2) + (Methods.getStringHeight(text.toLowerCase(), font) / 2));
		
	}

	public void setError(boolean hasError) {
		this.hasError = hasError;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIsHover() {
		return isHover;
	}
}