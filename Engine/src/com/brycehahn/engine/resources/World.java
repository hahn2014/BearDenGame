package com.brycehahn.engine.resources;

public class World {
	public static Block[][] blocks;
	public static int maxX = 100, maxY = 100;
	
	public World(int width, int height) {
		maxX = width; maxY = height;
		
		blocks = new Block[maxX][maxY];
		
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				blocks[x][y] = new Block((x * 32), (y * 32), 32, 32, Tile.water);
			}
		}
		clearLevel();
	}
	
	public static void clearLevel() {
		for (int x = 0; x < blocks.length; x++) {
			for (int y = 0; y < blocks[0].length; y++) {
				if ((y == 0 || y == maxY - 1) || (x == 0 || x == maxX - 1)) {
					blocks[x][y].setID(Tile.wallCollision);
				} else {
					blocks[x][y].setID(Tile.water);
				}
			}
		}
	}
	
	public static void setBlock(int indexX, int indexY, int blockX, int blockY, int[] id) {
		blocks[indexX][indexY].setLocation(blockX, blockY);
		blocks[indexX][indexY].setID(id);
	}
}