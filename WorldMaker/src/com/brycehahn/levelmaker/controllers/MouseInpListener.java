package com.brycehahn.levelmaker.controllers;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import com.brycehahn.levelmaker.Game;
import com.brycehahn.levelmaker.misc.LevelStorage;
import com.brycehahn.levelmaker.misc.Methods;
import com.brycehahn.levelmaker.misc.SaveLoad;
import com.brycehahn.levelmaker.misc.Tile;
import com.brycehahn.levelmaker.renders.ExportLevel;
import com.brycehahn.levelmaker.renders.ImportLevel;
import com.brycehahn.levelmaker.renders.LevelScreen;
import com.brycehahn.levelmaker.renders.LevelSelect;
import com.brycehahn.levelmaker.renders.NewLevel;
import com.brycehahn.levelmaker.renders.Options;
import com.brycehahn.levelmaker.renders.TileSelect;
import com.brycehahn.levelmaker.renders.TileSelector;

public class MouseInpListener implements MouseInputListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if (Game.game.render.export.active == false && Game.game.render.Import.active == false && Game.game.render.New.active == false) {
			if (Methods.isMouseIn(e.getX(), e.getY(), TileSelector.dx, TileSelector.dy, TileSelector.sx, TileSelector.sy)) {  //WITHIN SELECTOR 
				for (TileSelect t : TileSelector.tiles) {
					if (Methods.isMouseIn(e.getX(), e.getY(), t.x, t.y, (t.x + t.width), (t.y + t.height))) {
						if (t.isSelect == false) {
							t.isSelect = true;
							LevelScreen.tileSelect = t.id;
						} else {
							t.isSelect = false;
							LevelScreen.tileSelect = Tile.blank;
						}
					} else {
						t.isSelect = false;
					}
				}
				
			} else if (Methods.isMouseIn(e.getX(), e.getY(), Options.dx, Options.dy, Options.sx, Options.sy)) {  //WITHIN OPTIONS
				for (int i = 0; i < Options.btns.length; i++) {
					if (Methods.isMouseIn(e.getX(), e.getY(), Options.btns[i].getX(), Options.btns[i].getY(),
							(Options.btns[i].getX() + Options.btns[i].getWidth()), (Options.btns[i].getY() + Options.btns[i].getHeight()))) {
						if (i == 0) { //export level to game engine
							Game.game.render.export.active = true;
							Game.game.render.Import.active = false;
							Game.game.render.New.active = false;
						} else if (i == 1) { //import level
							Game.game.render.Import.active = true;
							Game.game.render.export.active = false;
							Game.game.render.New.active = false;
						} else if (i == 2) { //create new level
							Game.game.render.New.active = true;
							Game.game.render.Import.active = false;
							Game.game.render.export.active = false;
						}
					}
				}
				
			} else if (Methods.isMouseIn(e.getX(), e.getY(), LevelScreen.dx, LevelScreen.dy, LevelScreen.sx, LevelScreen.sy)) {  //WITHIN LEVEL EDITOR
				for (int x = 0; x < LevelStorage.renderBlocks.length; x++) {
					for (int y = 0; y < LevelStorage.renderBlocks[0].length; y++) {
						if (LevelStorage.renderBlocks[x][y].isHover) {
							if (e.getClickCount() == MouseEvent.BUTTON1) {
								LevelStorage.blocks[x + Game.game.render.level.viewOffsetX][y + Game.game.render.level.viewOffsetY].setID(LevelScreen.tileSelect);
							} else if (e.getClickCount() == MouseEvent.BUTTON3) {
								LevelStorage.blocks[x + Game.game.render.level.viewOffsetX][y + Game.game.render.level.viewOffsetY].setID(Tile.blank);
							}
						}
					}
				}
			}
		} else {
			if (Game.game.render.export.active) {
				//if  we are hovered over the box, make rest of screen dark and bring box to focus
				if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.dx + 8, ExportLevel.dy + 8, ExportLevel.sx, ExportLevel.sy)) {
					//we are within the box
					//check for button hovers now
					
					for (int i = 0; i < ExportLevel.buttons.length; i++) {
						if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.buttons[i].getX(), ExportLevel.buttons[i].getY(),
								ExportLevel.buttons[i].getX() + ExportLevel.buttons[i].getWidth(),
								ExportLevel.buttons[i].getY() + ExportLevel.buttons[i].getHeight())) {
							if (i == 0) { //export
								SaveLoad.Save(ExportLevel.field.getText());
								Game.game.render.export.active = false;
							} else if (i == 1) { //cancel
								Game.game.render.export.active = false;
							}
						}
					}
					//check for texfield hover
					if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.field.getX(), ExportLevel.field.getY(),
							ExportLevel.field.getX() + ExportLevel.field.getWidth(), ExportLevel.field.getY() + ExportLevel.field.getHeight())) {
						ExportLevel.field.isSelected = true;
					} else {
						ExportLevel.field.isSelected = false;
					}
				} else { //we are hovering outside the box, un-activate the export screen
					Game.game.render.export.active = false;
				}
			} else if (Game.game.render.Import.active) {
				//if  we are hovered over the box, make rest of screen dark and bring box to focus
				if (Methods.isMouseIn(e.getX(), e.getY(), ExportLevel.dx + 8, ExportLevel.dy + 8, ExportLevel.sx, ExportLevel.sy)) {
					//we are within the box
					//check for button hovers now
					for (int i = 0; i < ImportLevel.buttons.length; i++) {
						if (Methods.isMouseIn(e.getX(), e.getY(), ImportLevel.buttons[i].getX(), ImportLevel.buttons[i].getY(),
								ImportLevel.buttons[i].getX() + ImportLevel.buttons[i].getWidth(),
								ImportLevel.buttons[i].getY() + ImportLevel.buttons[i].getHeight())) {
							if (i == 0) { //import
								SaveLoad.Load(ImportLevel.getFileName());
								Game.game.render.Import.active = false;
							} else if (i == 1) { //cancel
								Game.game.render.Import.active = false;
							}
						}
					}
					
					//check for level hovers
					for (LevelSelect l : ImportLevel.levels) {
						if (Methods.isMouseIn(e.getX(), e.getY(), l.x, l.y, (l.x + l.width), (l.y + l.height))) {
							if (l.isSelect == false) {
								l.isSelect = true;
								ImportLevel.selected = l.id;
							} else {
								l.isSelect = false;
								ImportLevel.selected = 0;
							}
						} else {
							l.isSelect = false;
						}
					}
					
				} else { //we are hovering outside the box, un-activate the export screen
					Game.game.render.Import.active = false;
				}
			} else if (Game.game.render.New.active) {
				if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.dx, NewLevel.dy, NewLevel.dx + NewLevel.sx, NewLevel.dy + NewLevel.sy)) {
					for (int i = 0; i < NewLevel.buttons.length; i++) {
						if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.buttons[i].getX(), NewLevel.buttons[i].getY(),
								NewLevel.buttons[i].getX() + NewLevel.buttons[i].getWidth(),
								NewLevel.buttons[i].getY() + NewLevel.buttons[i].getHeight())) {
							if (i == 0) { //build
								LevelStorage.maxX = Integer.parseInt(NewLevel.widthField.getText());
								LevelStorage.maxY = Integer.parseInt(NewLevel.heightField.getText());
								
								if ((LevelStorage.maxX <= 1000 && LevelStorage.maxX >= 10)
										&& (LevelStorage.maxY <= 1000 && LevelStorage.maxY >= 10)) {
									Game.level = new LevelStorage();
									
									Game.game.render.New.active = false;
								} else {
									if (LevelStorage.maxX > 1000 || LevelStorage.maxX < 10) {
										Game.game.render.New.wError = true;
									}
									if (LevelStorage.maxY > 1000 || LevelStorage.maxY < 10) {
										Game.game.render.New.hError = true;
									}
								}
							} else if (i == 1) { //cancel
								Game.game.render.New.active = false;
							}
						}
					}
					
					
					//check for width field hover
					if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.widthField.getX(), NewLevel.widthField.getY(),
							NewLevel.widthField.getX() + NewLevel.widthField.getWidth(), NewLevel.widthField.getY() + NewLevel.widthField.getHeight())) {
						NewLevel.widthField.isSelected = true;
						Game.game.render.New.wError = false;
					} else {
						NewLevel.widthField.isSelected = false;
					}
					//check for width field hover
					if (Methods.isMouseIn(e.getX(), e.getY(), NewLevel.heightField.getX(), NewLevel.heightField.getY(),
							NewLevel.heightField.getX() + NewLevel.heightField.getWidth(), NewLevel.heightField.getY() + NewLevel.heightField.getHeight())) {
						NewLevel.heightField.isSelected = true;
						Game.game.render.New.hError = false;
					} else {
						NewLevel.heightField.isSelected = false;
					}
				}
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}