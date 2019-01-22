package com.brycehahn.engine.menus.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.main.References;
import com.brycehahn.engine.maths.Methods;
import com.brycehahn.engine.menus.MainMenuRender;
import com.brycehahn.engine.menus.ui.Button;

public class MouseMoveListener implements MouseMotionListener {
	References r = Game.r;
	Button refB;
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX(), mouseY = e.getY();
		
		switch (r.MENU) {
			case 0: //MAIN MENU
				for (int i = 0; i < Game.game.mainmenu.btns.length; i++) {
					refB = Game.game.mainmenu.btns[i];
					
					if (Methods.isMouseIn(mouseX, mouseY, refB.getX(), refB.getY(), refB.getWidth(), refB.getHeight(), 5)) {
						Game.game.mainmenu.btns[i].setHover(true);
						MainMenuRender.selected = i;
					} else {
						Game.game.mainmenu.btns[i].setHover(false);
					}
				}
			break;
			case 1: //SETTINGS
				
			break;
			case 2: //NEW GAME
				
			break;
			case 3: //LOAD SAVE
				
			break;
			case 4: //PAUSE MENU
				
			break;
			case 5: //IN GAME
				
			break;
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
}