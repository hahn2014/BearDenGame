package com.brycehahn.engine.menus.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.brycehahn.engine.io.Logger;
import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.menus.NewWorldRender;
import com.brycehahn.engine.menus.MainMenuRender;
import com.brycehahn.engine.menus.SettingsRender;

public class KeyInputListener implements KeyListener {
	private References r = Game.r;


	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		switch (r.MENU) {
			/*
			 * MAIN MENU CONTROLLS
			 */
			case 0:
				switch (key) {
					case KeyEvent.VK_UP:
						if (MainMenuRender.selected - 1 >= MainMenuRender.min) {
							MainMenuRender.selected--;
						} else if (MainMenuRender.selected - 1 < MainMenuRender.min) {
							MainMenuRender.selected = MainMenuRender.max;
						} else {
							Logger.error("Something went wrong in up");
						}
					break;
					case KeyEvent.VK_DOWN:
						if (MainMenuRender.selected + 1 <= MainMenuRender.max) {
							MainMenuRender.selected++;
						} else if (MainMenuRender.selected + 1 > MainMenuRender.max) {
							MainMenuRender.selected = MainMenuRender.min;
						} else {
							Logger.error("Something went wrong in down");
						}
					break;
					case KeyEvent.VK_ENTER:
						if (MainMenuRender.selected == 0) {
							//new game
							r.MENU = 2;
							r.MENUNAME = "NEW GAME MENU";
						} else if (MainMenuRender.selected == 1) {
							//settings
							r.MENU = 1;
							r.MENUNAME = "SETTINGS MENU";
						} else if (MainMenuRender.selected == 2) {
							r.isRunning = false;
							System.exit(0);
						} else {
							Logger.error("We could not selected the button " + MainMenuRender.selected + ", because it is out of the range of [" + MainMenuRender.min + " - " + MainMenuRender.max + "]");
						}
					break;
				}
			break;
			
			/*
			 * SETTINGS MENU CONTROLLS
			 */
			case 1:
				switch (key) {
					case KeyEvent.VK_UP:
						if (SettingsRender.selected - 1 >= SettingsRender.min) {
							SettingsRender.selected--;
						} else if (SettingsRender.selected - 1 < SettingsRender.min) {
							SettingsRender.selected = SettingsRender.max;
						} else {
							Logger.error("Something went wrong in up");
						}
					break;
					case KeyEvent.VK_DOWN:
						if (SettingsRender.selected + 1 <= SettingsRender.max) {
							SettingsRender.selected++;
						} else if (SettingsRender.selected + 1 > SettingsRender.max) {
							SettingsRender.selected = SettingsRender.min;
						} else {
							Logger.error("Something went wrong in down");
						}
					break;
					case KeyEvent.VK_ENTER:
						if (SettingsRender.selected == 0) {
							
						} else if (SettingsRender.selected == 1) {
							
						} else if (SettingsRender.selected == 2) {
							
						} else if (SettingsRender.selected == 3) {
							
						} else if (SettingsRender.selected == 4) {
							
						} else if (SettingsRender.selected == 5) {
							r.MENU = 0; //back to main meun
						}
					break;
				}
			break;
			
			/*
			 * NEW WORLD MENU
			 */
			case 2:
				switch (key) {
					case KeyEvent.VK_UP:
						if (NewWorldRender.selected == 0 || NewWorldRender.selected == 1) {
							NewWorldRender.selected = -1;
						} else {
							NewWorldRender.selected = 0;
						}
					break;
					case KeyEvent.VK_DOWN:
						if (NewWorldRender.selected == 0 || NewWorldRender.selected == 1) {
							NewWorldRender.selected = -1;
						} else {
							NewWorldRender.selected = 0;
						}
					break;
					case KeyEvent.VK_LEFT:
						if (NewWorldRender.selected == 0) {
							NewWorldRender.selected = 1;
						} else {
							NewWorldRender.selected = 0;
						}
					break;
					case KeyEvent.VK_RIGHT:
						if (NewWorldRender.selected == 0) {
							NewWorldRender.selected = 1;
						} else {
							NewWorldRender.selected = 0;
						}
					break;
					case KeyEvent.VK_ENTER:
						if (NewWorldRender.selected == -1) {
							//go into text box
						} else if (NewWorldRender.selected == 0) {
							//go back
							r.MENU = 0;
							r.MENUNAME = "MAIN MENU";
						} else if (NewWorldRender.selected == 1) {
							//create the new world
							r.MENU = 5;
							r.MENUNAME = "IN GAME";
						}
					break;
				}
			break;
			
			/*
			 * 
			 *	IN GAME
			 * 
			 */
			case 5:
				switch (key) {
					case KeyEvent.VK_W:
						if (Game.player.yMove != 1) {
							Game.player.yMove = 0;
						}
					break;
					case KeyEvent.VK_D:
						if (Game.player.xMove != -1) {
							Game.player.xMove = 0;
						}
					break;
					case KeyEvent.VK_S:
						if (Game.player.yMove != -1) {
							Game.player.yMove = 0;
						}
					break;
					case KeyEvent.VK_A:
						if (Game.player.xMove != 1) {
							Game.player.xMove = 0;
						}
					break;
				}
			break;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		if (r.MENU == 5) { //in a game
			switch (key) {
				case KeyEvent.VK_W:
					Game.player.yMove = -1;
				break;
				case KeyEvent.VK_D:
					Game.player.xMove = 1;
				break;
				case KeyEvent.VK_S:
					Game.player.yMove = 1;
				break;
				case KeyEvent.VK_A:
					Game.player.xMove = -1;
				break;
			}
		}
	}
}