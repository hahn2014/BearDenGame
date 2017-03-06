package com.brycehahn.engine.menus.controllers.mainmenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.brycehahn.engine.main.Game;
import com.brycehahn.engine.maths.Methods;

public class MenuMouseMove implements MouseMotionListener {
	Methods m = new Methods();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		for (int i = 0; i < Game.game.mainmenu.btns.length; i++) {
			Game.game.mainmenu.btns[i].setHover(m.isMouseIn(Game.game.mainmenu.btns[i].getX(), Game.game.mainmenu.btns[i].getY(),
					x, y, Game.game.mainmenu.btns[i].getWidth(), Game.game.mainmenu.btns[i].getHeight(), 0));
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
}