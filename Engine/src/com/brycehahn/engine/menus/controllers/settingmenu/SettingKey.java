package com.brycehahn.engine.menus.controllers.settingmenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.brycehahn.engine.io.Logger;
import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.menus.MainMenuRender;

public class SettingKey implements KeyListener {
	public static int selected = 0;
	private int min = 0;
	private int max = 6;
	private References r = Game.r;


	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		switch (key) {
			case KeyEvent.VK_UP:
				if (selected - 1 >= min) {
					selected--;
					MainMenuRender.select = selected;
				} else if (selected - 1 < min) {
					selected = max;
					MainMenuRender.select = selected;
				} else {
					Logger.error("Something went wrong in up");
				}
			break;
			case KeyEvent.VK_DOWN:
				if (selected + 1 <= max) {
					selected++;
					MainMenuRender.select = selected;
				} else if (selected + 1 > max) {
					selected = min;
					MainMenuRender.select = selected;
				} else {
					Logger.error("Something went wrong in down");
				}
			break;
			case KeyEvent.VK_ENTER:
				if (selected == 0) {
				} else if (selected == 6) {
					Logger.info("Going back");
					r.MENU = 0;
				} else {
					Logger.error("We could not selected the button " + selected + ", because it is out of the range of [" + min + " - " + max + "]");
				}
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
}