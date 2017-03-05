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
	private Methods m = new Methods();
	
	/*CONSTRUCTORS*/
	
	public Button(int x, int y, String text) {
		this.x = x;
		this.y = y;
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
		g.setColor(!isHover ? backcolor : backcolorHover);
		g.fillRect(x, y, width, height);
		
		g.setColor(!isHover ? foreground : foregroundHover);
		g.drawString(text, (Game.r.PIXEL.width / 2) 
				- (m.getStringWidth(text, Game.r.font1) / 2),
				(y + (Game.r.ButtonHeight + 1) - (m.getStringHeight(text, Game.r.font1) / 2)));
	}
	
	public void tick() {
		
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
}