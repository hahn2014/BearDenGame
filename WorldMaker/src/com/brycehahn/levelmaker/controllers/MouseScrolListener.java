package com.brycehahn.levelmaker.controllers;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.renders.LevelScreen;

public class MouseScrolListener implements MouseWheelListener {
	public static int selected = 0;
	private boolean capped = false;
	public static boolean allowScroll = false;

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int speed = e.getWheelRotation();
		if (allowScroll) {
			if (speed < 0) { //move up
				if (LevelScreen.isHover) {  // increase scale
					if (Game.game.render.level.scale < 1) {
						if (Game.game.render.level.scale * 2 <= 1) {
							Game.game.render.level.scale *= 2;
							capped = false;
						}
					} else {
						if (Game.game.render.level.scale + 1 <= 3) {
							Game.game.render.level.scale++;
							capped = false;
						}
					}
					if (Game.game.render.level.scale == 3) {
						capped = true;
					}
					LevelStorage.setupRenderBlocks(Game.game.render.level.viewOffsetX, Game.game.render.level.viewOffsetY,
							(int)(LevelStorage.maxRenderX / Game.game.render.level.scale), (int)(LevelStorage.maxRenderY / Game.game.render.level.scale));
					if (capped == false)
						LevelScreen.scaling = true;
				}
			} else if (speed > 0) { //move down
				if (LevelScreen.isHover) {  // decrease scale
					if (Game.game.render.level.scale <= 1) {
						if (Game.game.render.level.scale / 2 >= 0.25) {
							Game.game.render.level.scale /= 2;
							capped = false;
						}
					} else {
						if (Game.game.render.level.scale - 1 >= 1) {
							Game.game.render.level.scale--;
							capped = false;
						}
					}
					if (Game.game.render.level.scale == 0.25) {
						capped = true;
					}
					LevelStorage.setupRenderBlocks(Game.game.render.level.viewOffsetX, Game.game.render.level.viewOffsetY,
							(int)(LevelStorage.maxRenderX / Game.game.render.level.scale), (int)(LevelStorage.maxRenderY / Game.game.render.level.scale));
					if (capped == false)
						LevelScreen.scaling = true;
				}
			}
		}
	}
}