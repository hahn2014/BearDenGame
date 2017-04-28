package com.brycehahn.levelmaker.misc;

import com.brycehahn.levelmaker.misc.Block;
import com.brycehahn.levelmaker.misc.Tile;

public class LevelStorage {
	public static final int maxRenderX = 23, maxRenderY = 29;
	public static int maxX = 100, curMaxRenderX = 23;
	public static int maxY = 100, curMaxRenderY = 29;
	
	public static Block[][] blocks;
	public static Block[][] renderBlocks;
	
	public LevelStorage() {
		blocks = new Block[maxX][maxY];
		
		setupRenderBlocks(0, 0, curMaxRenderX, curMaxRenderY);
		
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				blocks[x][y] = new Block((x * 32), (y * 32), 32, 32, Tile.water);
			}
		}
		clearLevel();
	//	clearRenderBlocks();
	}
	
	public static void setupRenderBlocks(int offsetX, int offsetY, int maxx, int maxy) {
		curMaxRenderX = maxx;
		curMaxRenderY = maxy;
		
		if (maxX < curMaxRenderX || maxY < curMaxRenderY) {
			if (maxX < curMaxRenderX && maxY >= curMaxRenderY) {
				renderBlocks = new Block[maxX][curMaxRenderY];
			} else if (maxY < curMaxRenderY && maxX >= curMaxRenderX) {
				renderBlocks = new Block[curMaxRenderX][maxY];
			} else if (maxX < curMaxRenderX && maxY < curMaxRenderY) {
				renderBlocks = new Block[maxX][maxY];
			}
		} else {
			renderBlocks = new Block[curMaxRenderX][curMaxRenderY];
		}

		updateRenderBlocks(offsetX, offsetY);
	}
	
	public static void clearLevel() {
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[0].length; y++) {
				if ((y == 0 || y == maxY - 1) || (x == 0 || x == maxX - 1)) {
					blocks[x][y].setID(Tile.wallCollision);
				} else {
					blocks[x][y].setID(Tile.water);
				}
				blocks[x][y].isHover = false;
			}
		}
	}
	public static void clearRenderBlocks() {
		for (int x = 0; x < renderBlocks.length; x++) {
			for (int y = 0; y < renderBlocks[0].length; y++) {
				renderBlocks[x][y].isHover = false;
			}
		}
	}
	
	public static void setBlock(int indexX, int indexY, int blockX, int blockY, int[] id) {
		blocks[indexX][indexY].setLocation(blockX, blockY);
		blocks[indexX][indexY].setID(id);
	}
	
	public static void updateRenderBlocks(int xOffset, int yOffset) {
		
		if (maxX < curMaxRenderX || maxY < curMaxRenderY) {
			if (maxX < curMaxRenderX && maxY >= curMaxRenderY) {
				for (int x = 0; x < maxX; x++) {
					for (int y = 0; y < curMaxRenderY; y++) {
						if (blocks[x + xOffset][y + yOffset] != null) {
							renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
						}
					}
				}
			} else if (maxY < curMaxRenderY && maxX >= curMaxRenderX) {
				for (int x = 0; x < curMaxRenderX; x++) {
					for (int y = 0; y < maxY; y++) {
						if (blocks[x + xOffset][y + yOffset] != null) {
							renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
						}
					}
				}
			} else if (maxX < curMaxRenderX && maxY < curMaxRenderY) {
				for (int x = 0; x < maxX; x++) {
					for (int y = 0; y < maxY; y++) {
						if (blocks[x + xOffset][y + yOffset] != null) {
							renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
						}
					}
				}
			}
		} else {
			for (int x = 0; x < curMaxRenderX; x++) {
				for (int y = 0; y < curMaxRenderY; y++) {
					if (blocks[x + xOffset][y + yOffset] != null) {
						renderBlocks[x][y] = blocks[x + xOffset][y + yOffset];
					}
				}
			}
		}
	}
}