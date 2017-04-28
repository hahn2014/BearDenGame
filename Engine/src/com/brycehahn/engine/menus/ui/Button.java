package com.brycehahn.engine.menus.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.maths.Methods;

public class Button {
	
	private int x, y, width = 200, height = 20;
	private String text = "";
	private boolean isHover = false;
	private Color foreground = Color.WHITE, foregroundHover = Color.WHITE, backcolor = Color.BLACK, backcolorHover = Color.LIGHT_GRAY;
	
	/*CONSTRUCTORS*/
	
	public Button(int x, int y, String text) {
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public Button(int x, int y, int width, int height, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
	}
	
	public Button(int x, int y, String text, Color foreground) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.foreground = foreground;
	}
	
	public Button(int x, int y, String text, Color foreground, Color background) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.foreground = foreground;
		this.backcolor = background;
	}
	
	public Button(int x, int y, String text, Color foreground, Color foregroundHover, Color background, Color backgroundHover) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.foreground = foreground;
		this.backcolor = background;
	}
	
	/*TICKING AND RENDERING*/
	
	public void render(Graphics g) {
		g.setColor(backcolor);
		g.fillRect(x, y, width, height);

		g.setColor(!isHover ? backcolor : backcolorHover);
		g.fillRect(x + 4, y + 4, width - 8, height - 8);
		
		g.setColor(!isHover ? foreground : foregroundHover);
		g.drawString(text, (x + (width / 2)) 
				- (Methods.getStringWidth(text, Game.r.font1) / 2),
				(y + (height / 2) + (Methods.getStringHeight(text, Game.r.font1) / 2) - 3));
	}
	
	
	/*GETTERS AND SETTERS*/

	public Color getForegroundColor() {
		return foreground;
	}
	public void setForegroundColor(Color c) {
		foreground = c;
	}
	public Color getBackgroundColor() {
		return backcolor;
	}
	public void setBackgroundColor(Color c) {
		backcolor = c;
	}
	public Boolean isHover() {
		return isHover;
	}
	public void setHover(Boolean hove) {
		isHover = hove;
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
}