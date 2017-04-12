package com.brycehahn.levelmaker.misc;

import com.brycehahn.levelmaker.misc.Block;
import com.brycehahn.levelmaker.misc.Tile;

public class LevelStorage {
	public static int maxX = 100;
	public static int maxY = 100;
	
	public static Block[][] blocks;
	public static Block[][] renderBlocks;
	
	public LevelStorage() {
		blocks = new Block[maxX][maxY];
		
		if (maxX < 23 || maxY < 29) {
			if (maxX < 23 && maxY >= 29) {
				renderBlocks = new Block[maxX][29];
			} else if (maxY < 29 && maxX >= 23) {
				renderBlocks = new Block[23][maxY];
			} else if (maxX < 23 && maxY < 29) {
				renderBlocks = new Block[maxX][maxY];
			}
			
		} else {
			renderBlocks = new Block[23][29];
		}
		
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				blocks[x][y] = new Block((x * 32), (y * 32), 32, 32, Tile.water);
			}
		}
		updateRenderBlocks(0, 0);
	}
	
	public static void clearLevel() {
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[0].length; y++) {
				blocks[x][y].setID(Tile.water);
			}
		}
	}
	
	public static void setBlock(int indexX, int indexY, int blockX, int blockY, int[] id) {
		blocks[indexX][indexY].setLocation(blockX, blockY);
		blocks[indexX][indexY].setID(id);
	}
	
	public static void updateRenderBlocks(int xOffset, int yOffset) {
		
		if (maxX < 23 || maxY < 29) {
			if (maxX < 23 && maxY >= 29) {
				for (int x = 0; x < maxX; x++) {
					for (int y = 0; y < 29; y++) {
						renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
					}
				}
			} else if (maxY < 29 && maxX >= 23) {
				for (int x = 0; x < 23; x++) {
					for (int y = 0; y < maxY; y++) {
						renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
					}
				}
			} else if (maxX < 23 && maxY < 29) {
				for (int x = 0; x < maxX; x++) {
					for (int y = 0; y < maxY; y++) {
						renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
					}
				}
			}
			
		} else {
			for (int x = 0; x < 23; x++) {
				for (int y = 0; y < 29; y++) {
					renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
				}
			}
		}
	}
}