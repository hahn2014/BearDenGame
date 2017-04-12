package com.brycehahn.levelmaker.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.renders.ExportLevel;
import com.brycehahn.levelmaker.renders.NewLevel;

public class KeyInputListener implements KeyListener {

	@Override
	public void keyReleased(KeyEvent event) {
		int key = event.getKeyCode();
		
		if (Game.game.render.export.active) {
			if (ExportLevel.field.isSelected) { //do typing
				if (Character.isLetterOrDigit(key) || key == KeyEvent.VK_SPACE) {
					ExportLevel.field.setText(ExportLevel.field.getText() + (char)(key));
				}
				if (key == KeyEvent.VK_BACK_SPACE) {
					if (ExportLevel.field.getText().length() >= 1) {
						ExportLevel.field.setText(ExportLevel.field.getText().substring(0, ExportLevel.field.getText().length() - 1));
					}
				}
			} 
		}
		if (Game.game.render.New.active) {
			if (NewLevel.widthField.isSelected) {
				if (Character.isDigit(key)) {
					NewLevel.widthField.setText(NewLevel.widthField.getText() + (char)(key));
				}
				if (key == KeyEvent.VK_BACK_SPACE) {
					if (NewLevel.widthField.getText().length() >= 1) {
						NewLevel.widthField.setText(NewLevel.widthField.getText().substring(0, NewLevel.widthField.getText().length() - 1));
					}
				}
			} else if (NewLevel.heightField.isSelected) {
				if (Character.isDigit(key)) {
					NewLevel.heightField.setText(NewLevel.heightField.getText() + (char)(key));
				}
				if (key == KeyEvent.VK_BACK_SPACE) {
					if (NewLevel.heightField.getText().length() >= 1) {
						NewLevel.heightField.setText(NewLevel.heightField.getText().substring(0, NewLevel.heightField.getText().length() - 1));
					}
				}
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent event) {
		int key = event.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			if (Game.game.render.level.viewOffsetY - 1 >= 0) {
				Game.game.render.level.viewOffsetY -= 1;
			}
		} else if (key == KeyEvent.VK_S) {
			if (LevelStorage.maxY > 29) {
				if ((29 + Game.game.render.level.viewOffsetY) + 1 < LevelStorage.blocks[0].length - 1) {
					Game.game.render.level.viewOffsetY += 1;
				}
			}
		} else if (key == KeyEvent.VK_D) {
			if (LevelStorage.maxX > 23) {
				if ((23 + Game.game.render.level.viewOffsetX) + 1 < LevelStorage.blocks.length - 1) {
					Game.game.render.level.viewOffsetX += 1;
				}
			}
		} else if (key == KeyEvent.VK_A) {
			if (Game.game.render.level.viewOffsetX - 1 >= 0) {
				Game.game.render.level.viewOffsetX -= 1;
			}
		}
	}
}