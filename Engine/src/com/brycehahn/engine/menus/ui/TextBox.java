package com.brycehahn.engine.menus.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.maths.Methods;

public class TextBox {
	String text = "";
	int width = 100, height = 20, x, y;

	private boolean isHover = false;
	private Color foreground = Color.WHITE, backcolor = Color.BLACK, backcolorHover = Color.LIGHT_GRAY;
	private Methods m = new Methods();
	
	public TextBox(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public TextBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void render(Graphics g) {
		g.setColor(!isHover ? backcolor : backcolorHover);
		g.fillRect(x, y, width, height);

		g.setColor(backcolor);
		g.fillRect(x + 4, y + 4, width - 8, height - 8);
		
		g.setColor(foreground);
		g.drawString(text, (x + (width / 2)) 
				- (m.getStringWidth(text, Game.r.font1) / 2),
				(y + (height / 2) + (m.getStringHeight(text, Game.r.font1) / 2) - 3));
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
	public int getHiehgt() {
		return height;
	}
	public boolean getHover() {
		return isHover;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setHover(boolean isHover) {
		this.isHover = isHover;
	}
}