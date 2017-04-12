package com.brycehahn.levelmaker.controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.Button;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.renders.ExportLevel;
import com.brycehahn.levelmaker.renders.ImportLevel;
import com.brycehahn.levelmaker.renders.LevelScreen;
import com.brycehahn.levelmaker.renders.LevelSelect;
import com.brycehahn.levelmaker.renders.NewLevel;
import com.brycehahn.levelmaker.renders.Options;
import com.brycehahn.levelmaker.renders.TileSelect;
import com.brycehahn.levelmaker.renders.TileSelector;

public class MouseMoveListener implements MouseMotionListener {
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (Game.game.render.export.active == false && Game.game.render.Import.active == false && Game.game.render.New.active == false) {
			if (Methods.isMouseIn(e.getX(), e.getY(), TileSelector.dx, TileSelector.dy, TileSelector.sx, TileSelector.sy)) {  //SELECTOR HOVER
				TileSelector.isHover = true;
				Options.isHover = false;
				LevelScreen.isHover = false;
				
				for (TileSelect t : TileSelector.tiles) {
					if (Methods.isMouseIn(e.getX(), e.getY(), t.x, t.y, (t.x + t.width), (t.y + t.height))) {
						t.isHover = true;
					} else {
						t.isHover = false;
					}
				}
				
			} else if (Methods.isMouseIn(e.getX(), e.getY(), Options.dx, Options.dy, Options.sx, Options.sy)) {  //OPTIONS HOVER
				TileSelector.isHover = false;
				Options.isHover = true;
				LevelScreen.isHover = false;
				
				for (Button b : Options.btns) {
					if (Methods.isMouseIn(e.getX(), e.getY(), b.getX(), b.getY(), (b.getX() + b.getWidth()), (b.getY() + b.getHeight()))) {
						b.setHover(true);
					} else {
						b.setHover(false);
					}
				}
				
			} else if (Methods.isMouseIn(e.getX(), e.getY(), LevelScreen.dx, LevelScreen.dy, LevelScreen.sx, LevelScreen.sy)) {  //LEVEL HOVER
				TileSelector.isHover = false;
				Options.isHover = false;
				LevelScreen.isHover = true;
				
				
				if (Methods.isMouseIn(e.getX(), e.getY(), LevelScreen.dx + 16, LevelScreen.dy + 34, 756, 940)) { //WITHIN THE ACTUAL EDITOR
					for (int x = 0; x < LevelStorage.renderBlocks.length; x++) {
						for (int y = 0; y < LevelStorage.renderBlocks[0].length; y++) {
							if (Methods.isMouseIn(e.getX(), e.getY(), LevelScreen.dx + 16 + (32 * x), LevelScreen.dy + 34 + (32 * y),
									LevelScreen.dx + 15 + (32 * (x + 1)), LevelScreen.dy + 33 + (32 * (y + 1)))) {
								LevelStorage.renderBlocks[x][y].isHover = true;
							} else {
								LevelStorage.renderBlocks[x][y].isHover = false;
							}
						}
					}
				}
			} else {
				TileSelector.isHover = false;
				Options.isHover = false;
				LevelScreen.isHover = false;
			}
		}
		
		//Check for load and export hovers
		else  {
			if (Game.game.render.export.active) {
				//if  we are hovered over the box, make rest of screen dark and bring box to focus
				if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.dx + 8, ExportLevel.dy + 8, ExportLevel.sx, ExportLevel.sy)) {
					ExportLevel.isHover = true;
					//check for button hovers now
					for (Button b : ExportLevel.buttons) {
						if (Methods.isMouseIn(e.getX(), e.getY(), b.getX(), b.getY(), b.getX() + b.getWidth(), b.getY() + b.getHeight())) {
							b.setHover(true);
						} else {
							b.setHover(false);
						}
					}
					//check for texfield hover
					if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.field.getX(), ExportLevel.field.getY(),
							ExportLevel.field.getX() + ExportLevel.field.getWidth(), ExportLevel.field.getY() + ExportLevel.field.getHeight())) {
						ExportLevel.field.isHover = true;
					} else {
						ExportLevel.field.isHover = false;
					}
				} else { //if we are hovered over rest of screen, loose box focus and bring brightness of screen up
					ExportLevel.isHover = false;
				}
			} else if (Game.game.render.Import.active) {
				//if  we are hovered over the box, make rest of screen dark and bring box to focus
				if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.dx + 8, ExportLevel.dy + 8, ExportLevel.sx, ExportLevel.sy)) {
					ImportLevel.isHover = true;
					//check for button hovers now
					for (Button b : ImportLevel.buttons) {
						if (Methods.isMouseIn(e.getX(), e.getY(), b.getX(), b.getY(), b.getX() + b.getWidth(), b.getY() + b.getHeight())) {
							b.setHover(true);
						} else {
							b.setHover(false);
						}
					}
					
					//check for level hovers
					for (LevelSelect l : ImportLevel.levels) {
						if (Methods.isMouseIn(e.getX(), e.getY(), l.x, l.y, (l.x + l.width), (l.y + l.height))) {
							l.isHover = true;
						} else {
							l.isHover = false;
						}
					}
				} else { //if we are hovered over rest of screen, loose box focus and bring brightness of screen up
					ImportLevel.isHover = false;
				}
			} else if (Game.game.render.New.active) {
				if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.dx, NewLevel.dy, NewLevel.dx + NewLevel.sx, NewLevel.dy + NewLevel.sy)) {
					NewLevel.isHover = true;
					
					for (Button b : NewLevel.buttons) {
						if (Methods.isMouseIn(e.getX(), e.getY(), b.getX(), b.getY(), b.getX() + b.getWidth(), b.getY() + b.getHeight())) {
							b.setHover(true);
						} else {
							b.setHover(false);
						}
					}
					
					//check for texfield hover
					if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.widthField.getX(), NewLevel.widthField.getY(),
							NewLevel.widthField.getX() + NewLevel.widthField.getWidth(), NewLevel.widthField.getY() + NewLevel.widthField.getHeight())) {
						NewLevel.widthField.isHover = true;
					} else {
						NewLevel.widthField.isHover = false;
					}
					//check for texfield hover
					if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.heightField.getX(), NewLevel.heightField.getY(),
							NewLevel.heightField.getX() + NewLevel.heightField.getWidth(), NewLevel.heightField.getY() + NewLevel.heightField.getHeight())) {
						NewLevel.heightField.isHover = true;
					} else {
						NewLevel.heightField.isHover = false;
					}
				} else {
					NewLevel.isHover = false;
				}
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
}