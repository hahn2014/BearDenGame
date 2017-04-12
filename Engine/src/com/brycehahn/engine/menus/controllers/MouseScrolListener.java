package com.brycehahn.engine.menus.controllers;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.brycehahn.engine.io.Logger;

public class MouseScrolListener implements MouseWheelListener {
	public static int selected = 0;
	private int min = 0;
	private int max = 2;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getWheelRotation() < 0) { //Wheel scrolled down
			if (selected - 1 >= min) {
				selected--;
			} else if (selected - 1 < min) {
				selected = max;
			} else {
				Logger.error("There was an error going up!");
			}
		} else { //wheel scrolled up
			if (selected + 1 <= max) {
				selected++;
			} else if (selected + 1 > max) {
				selected = min;
			} else {
				Logger.error("There was an error going down!");
			}
		}
	}
}